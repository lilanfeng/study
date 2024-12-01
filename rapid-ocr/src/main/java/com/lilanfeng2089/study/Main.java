package com.lilanfeng2089.study;

import com.benjaminwan.ocrlibrary.OcrResult;
import io.github.mymonstercat.Model;
import io.github.mymonstercat.ocr.InferenceEngine;

/**
 * @program: com.lilanfeng2089.study
 * @description: ${DESCRIPTION}
 * @author: lilf@bwoil.com
 * @create: 2024-03-12 11:09
 **/
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        InferenceEngine engine = InferenceEngine.getInstance(Model.ONNX_PPOCR_V3);
        OcrResult ocrResult = engine.runOcr("/images/test.png");
        System.out.println(ocrResult.getStrRes());
    }
}