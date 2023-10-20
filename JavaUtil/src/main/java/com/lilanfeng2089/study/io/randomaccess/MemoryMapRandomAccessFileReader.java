package com.lilanfeng2089.study.io.randomaccess;


import sun.misc.Cleaner;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class MemoryMapRandomAccessFileReader extends BaseReader implements Closeable {

    private int position;

    private int limit = -1;

    private MappedByteBuffer mappedByteBuffer;

    private FileChannel channel;

    private RandomAccessFile randomAccessFile;

    public MemoryMapRandomAccessFileReader(String pathName){
        init(new File(pathName),DEFAULT_CHARSET);
    }

    public MemoryMapRandomAccessFileReader(String pathName,String charsetName){
        init(new File(pathName),charsetName);
    }

    public MemoryMapRandomAccessFileReader(File file){
        init(file,DEFAULT_CHARSET);
    }

    public MemoryMapRandomAccessFileReader(File file,String pathName){
        init(file,DEFAULT_CHARSET);
    }


    private void init(File file,String charsetName){
        Charset.forName(charsetName);
        this.charsetName = charsetName;
        try {
            randomAccessFile = new RandomAccessFile(file,"r");
            channel = randomAccessFile.getChannel();
            mappedByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY,randomAccessFile.getFilePointer(),channel.size());
            limit = mappedByteBuffer.limit();

        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    long getFilePointer() {
        try {
            //一直是0，说明采用内存映射时候这个方法不起作用
            return  randomAccessFile.getFilePointer();
        } catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    void seek(long pos) {
        try {
            //采用内存映射时候这个方法不起作用
            randomAccessFile.seek(pos);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    String readLine() {
        if(position >= limit){
            return null;
        }
        byte[] arr = new byte[336];
        int arrPos = 0;
        while (true){
            byte b = mappedByteBuffer.get(position++);
            boolean isLast = position > limit;
            switch (b){
                case 10:
                    return decode(arr,arrPos,isLast);
                case 13:
                    if(position < limit){
                        judgeMacOrWindows();
                    }
                    return decode(arr,arrPos,isLast);
                default:
                    if(arrPos >= arr.length){
                        arr = grow(arr);
                    }
                    arr[arrPos++] = b;
                    if(position >= limit){
                        return decode(arr,arrPos,isLast);
                    }
            }
        }
    }

    private void judgeMacOrWindows() {
        byte b1 = mappedByteBuffer.get(position++);
        // Mac line separator
        if (b1 != 10) {
            position--;
        }
    }
    @Override
    public void close() throws IOException {
        if(randomAccessFile != null){
            randomAccessFile.close();
        }
        if(channel != null){
            channel.close();
        }

        if(mappedByteBuffer != null){
            mappedByteBuffer.clear();
        }
        clean();

    }

    @SuppressWarnings({"unchecked","rawtypes"})
    private void clean(){
        AccessController.doPrivileged((PrivilegedAction) () -> {
            try {
                Method getCleanerMethod = mappedByteBuffer.getClass().getMethod("cleaner");
                getCleanerMethod.setAccessible(true);
                Cleaner cleaner =(Cleaner) getCleanerMethod.invoke(mappedByteBuffer, new Object[0]);
                cleaner.clean();
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }

}
