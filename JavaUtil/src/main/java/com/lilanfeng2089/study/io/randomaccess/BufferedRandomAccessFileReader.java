package com.lilanfeng2089.study.io.randomaccess;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;

import static io.netty.handler.codec.http.HttpConstants.DEFAULT_CHARSET;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class BufferedRandomAccessFileReader extends BaseReader  implements Closeable {

    public static final int DEFAULT_BUFFER_CAPACITY = 8192;
    private byte[] buffer;
    private int position;
    private  int limit = -1;

    private RandomAccessFile randomAccessFile;

    public BufferedRandomAccessFileReader(String pathName){
        init(new File(pathName),DEFAULT_BUFFER_CAPACITY,DEFAULT_CHARSET);
    }

    public BufferedRandomAccessFileReader(String pathName,String charsetName){
        init(new File(pathName),DEFAULT_BUFFER_CAPACITY,charsetName);
    }

    public BufferedRandomAccessFileReader(String pathName,int bufferCapacity){
        init(new File(pathName),bufferCapacity,DEFAULT_CHARSET.toString());
    }

    public BufferedRandomAccessFileReader(String pathName,int bufferCapacity,String charsetName){
        init(new File(pathName),bufferCapacity,charsetName);
    }

    private void init(File file,int bufferCapacity,String charsetName){
        if(bufferCapacity < 1){
            throw new IllegalArgumentException("bufferCapacity is small");
        }
        Charset.forName(charsetName);
        try {
            randomAccessFile = new RandomAccessFile(file,"r");
            //limit = (int)file.length();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        buffer = new byte[bufferCapacity];
        this.charsetName  = charsetName;
    }

    @Override
    public long getFilePointer(){
        try {
            return randomAccessFile.getFilePointer();
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void seek(long pos){
        try {
            randomAccessFile.seek(pos);
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public String readLine(){
        try {
            if(position > limit){
                if(!readMore()){
                    return null;
                }
            }
            byte[] arr = new byte[512];
            int arrPos = 0;
            while (position <= limit){
                byte b = buffer[position++];
                boolean isLast = position > limit;
                switch (b){
                    case 10:
                        //unix or linux line separator
                        return decode(arr,arrPos,isLast);
                    case 13:
                        if(position > limit){
                            if(readMore()){
                                judgeMaxOrWindows();
                            }
                        }else {
                            judgeMaxOrWindows();
                        }
                        return decode(arr,arrPos,isLast);
                    default:
                        if(arrPos >= arr.length){
                            arr = grow(arr);
                        }
                        arr[arrPos++] =b ;
                        if(position > limit){
                            if(!readMore()){
                                return decode(arr,arrPos,isLast);
                            }
                        }
                }
            }



        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    private void judgeMaxOrWindows(){
        byte b1 = buffer[position++];
        if(b1 != 10){
            position--;
        }
    }

    private boolean readMore() throws IOException {

        limit = randomAccessFile.read(buffer) -1;
        position = 0;
        return limit >= 0;
    }


    @Override
    public void close() throws IOException {

    }
}
