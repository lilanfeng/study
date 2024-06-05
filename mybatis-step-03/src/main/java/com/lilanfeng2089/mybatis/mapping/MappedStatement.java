package com.lilanfeng2089.mybatis.mapping;

import com.lilanfeng2089.mybatis.session.Configuration;

import java.util.Map;

/**
 * @program: com.lilanfeng2089.mybatis.mapping
 * @description:
 * @author: lilf@bwoil.com
 * @create: 2024-06-03 17:13
 **/
public class MappedStatement {

    private Configuration configuration;

    private String id;

    private SqlCommandType sqlCommandType;

    private String parameterType;

    private String resultType;

    private String sql;

    private Map<Integer,String> parameterMap;

    MappedStatement(){

    }

    /**
     * 建造者
     */
    public static class Builder {
        private MappedStatement mappedStatement = new MappedStatement();
        public Builder(Configuration configuration, String id, SqlCommandType sqlCommandType, String parameterType, String resultType, String sql, Map<Integer, String> parameterMap) {
            mappedStatement.configuration = configuration;
            mappedStatement.id = id;
            mappedStatement.sqlCommandType = sqlCommandType;
            mappedStatement.parameterType = parameterType;
            mappedStatement.resultType = resultType;
            mappedStatement.sql = sql;
            mappedStatement.parameterMap = parameterMap;
        }

        public MappedStatement build() {
            assert mappedStatement.configuration != null;
            assert mappedStatement.id != null;
            return mappedStatement;
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration config) {
        this.configuration = config;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(SqlCommandType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getResultType() {
        return resultType;
    }


    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }


    public void setSql(String sql) {
        this.sql = sql;
    }

    public Map<Integer, String> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<Integer, String> parameterMap) {
        this.parameterMap = parameterMap;
    }


}
