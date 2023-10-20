package com.lilanfeng2089.study.io.randomaccess;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class RandomAccessMain {

    public static String output = "";

    public static void foo(int i) {
        try {
            if (i == 1) {
                throw new Exception();
            }
        } catch (Exception e) {
            output += "2";
            return;
        } finally {
            output += "3";
        }
    }


        public static void main(String args[]){

            try{
                PrintWriter out = new PrintWriter(new FileOutputStream("d:/abc.txt"));
                String name = "chen";
                out.print(name);
            }catch(Exception e){
                System.out.println("文件没有发现");
            }

            foo(0);
        foo(1);
            System.out.println(output);

        RandomAccessMain r = new RandomAccessMain();


    }



    public static void main1(String[] args) {

        //String filePathName = "/Users/lilanfeng/Desktop/bigFile.txt";

        Map<String,String> inputFileMap = new HashMap<>();
        inputFileMap.put("/Users/lilanfeng/Desktop/bigFile.txt","662MB");
        inputFileMap.put("/Users/lilanfeng/Desktop/bigFile500Mb.txt","500MB");
        inputFileMap.put("/Users/lilanfeng/Desktop/bigFile100Mb.txt","100MB");
        inputFileMap.put("/Users/lilanfeng/Desktop/bigFile50Mb.txt","50MB");
        inputFileMap.put("/Users/lilanfeng/Desktop/bigFile10Mb.txt","10MB");

        for(Map.Entry<String,String> entry :inputFileMap.entrySet()){
            String filePathName = entry.getKey();
            String fileSize = entry.getValue();
            randomAccessFileRead(filePathName,fileSize);
            bufferedReaderRead(filePathName,fileSize);
            bufferedRandomAccessFileRead(filePathName,fileSize);
            memoryMapRandomAccessFileRead(filePathName,fileSize);
        }

    }

    /**
     * 	RandomAccessFile	BufferedReader
     * 第一次	9580ms	63ms
     * 第二次	9379ms	77ms
     * 第三次	9340ms	66ms
     * 不测不知道，一测吓一跳，使用缓冲区性能提升了100多倍！
     * @param filePathName
     */
    public static void randomAccessFileRead(String filePathName,String fileSize) {
        try (RandomAccessFile raf = new RandomAccessFile(filePathName,"r")){

            int count = 0;
            long beginning = System.currentTimeMillis();
            while (raf.readLine() !=null){
                count++;
            }
            long end = System.currentTimeMillis();
            System.out.println("RandomAccessFile,fileSize:"+fileSize +",line count:" + count + ", cost:"+(end - beginning) + " ms");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void bufferedReaderRead(String filePathName,String fileSize){
        try (BufferedReader br = new BufferedReader(new FileReader(filePathName))){
            int count = 0;
            long beginning = System.currentTimeMillis();
            while (br.readLine() !=null){
                count++;
            }
            long end = System.currentTimeMillis();
            System.out.println("BufferedReader,fileSize:"+fileSize +",line count:" + count + ", cost:"+(end - beginning) + " ms");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void bufferedRandomAccessFileRead(String filePathName,String fileSize){
        try (BufferedRandomAccessFileReader br = new BufferedRandomAccessFileReader(filePathName)){
            int count = 0;
            long beginning = System.currentTimeMillis();
            String temp = br.readLine();
            while (temp !=null){
                temp = br.readLine();
                count++;
            }
            long end = System.currentTimeMillis();
            System.out.println("BufferedRandomAccessFileReader,fileSize:"+fileSize +",line count:" + count + ", cost:"+(end - beginning) + " ms");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void memoryMapRandomAccessFileRead(String filePathName,String fileSize){
        try (MemoryMapRandomAccessFileReader br = new MemoryMapRandomAccessFileReader(filePathName)){
            int count = 0;
            long beginning = System.currentTimeMillis();
            String temp = br.readLine();
            while (temp !=null){

                temp = br.readLine();
                count++;
            }
            long end = System.currentTimeMillis();
            System.out.println("MemoryMapRandomAccessFileReader,fileSize:"+fileSize +",line count:" + count + ", cost:"+(end - beginning) + " ms");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
