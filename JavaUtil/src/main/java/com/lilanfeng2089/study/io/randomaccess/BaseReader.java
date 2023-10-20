package com.lilanfeng2089.study.io.randomaccess;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public abstract class  BaseReader {
    public static final String DEFAULT_CHARSET = Charset.defaultCharset().name();

    protected  String charsetName;

    abstract long getFilePointer();

    abstract void seek(long pos);

    abstract String readLine();

    /**
     * 字节数组扩容
     * @param arr
     * @return
     */
    public byte[] grow(byte[] arr){
        int len = arr.length;
        int half = len >> 1;
        int growSize = Math.max(half,1);
        byte[] arrNew = new byte[len + growSize];
        System.arraycopy(arr,0,arrNew,0,len);
        return arrNew;
    }

    public String decode(byte[] arr,int arrPos,boolean isLast){
        if(arrPos == 0 && isLast) {
            return null;
        }

        try {
            return new String(arr,0,arrPos,charsetName);
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

}
