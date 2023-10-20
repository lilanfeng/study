package com.lilanfeng2089.study.ip2region.util;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
class Ip2RegionSearchUtilTest {


    /**
     * 1.0.0.0|1.0.0.255|澳大利亚|0|0|0|0
     * 1.0.1.0|1.0.3.255|中国|0|福建省|福州市|电信
     * 1.0.4.0|1.0.7.255|澳大利亚|0|维多利亚|墨尔本|0
     * 1.0.8.0|1.0.15.255|中国|0|广东省|广州市|电信
     * 1.0.16.0|1.0.31.255|日本|0|0|0|0
     * 1.0.32.0|1.0.63.255|中国|0|广东省|广州市|电信
     * 1.0.64.0|1.0.79.255|日本|0|广岛县|0|0
     * 1.0.80.0|1.0.127.255|日本|0|冈山县|0|0
     * 1.0.128.0|1.0.128.255|泰国|0|清莱府|0|TOT
     * 1.0.129.0|1.0.132.191|泰国|0|曼谷|曼谷|TOT
     * 1.0.132.192|1.0.132.255|泰国|0|Nakhon-Ratchasima|0|TOT
     * 1.0.133.0|1.0.133.255|泰国|0|素攀武里府|0|TOT
     * 1.0.134.0|1.0.134.255|泰国|0|曼谷|曼谷|TOT
     * 1.0.135.0|1.0.135.127|泰国|0|华富里府|0|TOT
     * 1.0.135.128|1.0.135.255|泰国|0|素攀武里府|0|TOT
     * 1.0.136.0|1.0.136.255|泰国|0|龙仔厝府|0|TOT
     * 1.0.137.0|1.0.137.255|泰国|0|大城府|0|TOT
     * 1.0.138.0|1.0.143.255|泰国|0|曼谷|曼谷|TOT
     * 1.0.144.0|1.0.159.255|泰国|0|春蓬府|0|TOT
     * 1.0.160.0|1.0.162.255|泰国|0|洛坤府|0|TOT
     * 1.0.163.0|1.0.163.255|泰国|0|春蓬府|0|TOT
     * 1.0.164.0|1.0.164.63|泰国|0|0|0|TOT
     * 1.0.164.64|1.0.164.127|泰国|0|普吉府|0|TOT
     * 1.0.164.128|1.0.170.255|泰国|0|0|0|TOT
     * 1.0.171.0|1.0.175.255|泰国|0|攀牙府|0|TOT
     * @throws IOException
     */

    @org.junit.jupiter.api.Test
    void searchRegionByIp() throws IOException {
        String region = Ip2RegionSearchUtil.searchRegionByIp("1.0.8.0");
        System.out.printf("region:%s",region);
    }
}