package com.lilanfeng2089.mybatis.builder.xml;

import com.lilanfeng2089.mybatis.builder.BaseBuilder;
import com.lilanfeng2089.mybatis.io.Resources;
import com.lilanfeng2089.mybatis.mapping.MappedStatement;
import com.lilanfeng2089.mybatis.mapping.SqlCommandType;
import com.lilanfeng2089.mybatis.session.Configuration;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;


import org.dom4j.Element;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: com.lilanfeng2089.mybatis.builder.xml
 * @description:
 * @author: lilf@bwoil.com
 * @create: 2024-06-03 17:12
 **/
public class XMLConfigBuilder extends BaseBuilder {

    private Element root;

    public XMLConfigBuilder(Reader reader) {
        // 1 ,调用父类初始化Configuration
        super(new Configuration());

        //2,dom4j解析xml
        SAXReader saxReader = new SAXReader();
        try {
            org.dom4j.Document document = saxReader.read(new InputSource(reader));
            root = document.getRootElement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析配置；类型别名、插件、对象工厂、对象包装工厂、设置、环境、类型转换、映射器
     * @return
     */
    public Configuration parse() {
        try {
            // 解析配置
            mappersElement(root.element("mappers"));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing SQL Mapper Configuration. Cause: "+e,e);
        }
        return configuration;
    }

    private void mappersElement(Element mappers) throws Exception {
        List<Element> mappersList =  mappers.elements("mapper");
        if (mappersList == null) {
            return;
        }
        for (Element mapper : mappersList) {
            String resource = mapper.attributeValue("resource");
            Reader reader = Resources.getResourceAsReader(resource);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new InputSource(reader));
            Element root = document.getRootElement();
            //命名空间
            String namespace = root.attributeValue("namespace");

            //SELECT
            List<Element> selectNodes = root.elements("select");
            for (Element node : selectNodes) {
                String id = node.attributeValue("id");
                String parameterType = node.attributeValue("parameterType");
                String resultType = node.attributeValue("resultType");
                String sql = node.getTextTrim();

                //？匹配
                Map<Integer,String> parameter = new HashMap<>();
                Pattern pattern = Pattern.compile("(#\\{(.*?)})");
                Matcher matcher = pattern.matcher(sql);
                for (int i = 0; matcher.find(); i++) {
                    String g1 = matcher.group(1);
                    String g2 = matcher.group(2);
                    parameter.put(i,g2);
                    sql = sql.replace(g1,"?");
                }

                String msId = namespace + "." + id;
                String nodeName = node.getName();
                SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase());

                MappedStatement mappedStatement = new MappedStatement.Builder(configuration,msId,sqlCommandType,parameterType,resultType,sql,parameter).build();
                //添加解析SQL
                configuration.addMappedStatement(mappedStatement);
            }

            //注册Mapper映射器
            configuration.addMapper(Resources.classForName(namespace));

        }


    }

}
