package com.yn.code.model;

/**
 * 这里是类描述
 *
 * @author : yangning
 * @date: 2018-6-11
 **/
public class ConfigModel {
    private String jdbcHost;
    private String jdbcDatabase;
    private String jdbcUserName;
    private String jdbcPassword;

    private String tableName;

    private String author;
    private String modelPath;
    private String mapperJavaPath;
    private String mapperXmlPath;

    public String getJdbcHost() {
        return jdbcHost;
    }

    public void setJdbcHost(String jdbcHost) {
        this.jdbcHost = jdbcHost;
    }

    public String getJdbcDatabase() {
        return jdbcDatabase;
    }

    public void setJdbcDatabase(String jdbcDatabase) {
        this.jdbcDatabase = jdbcDatabase;
    }

    public String getJdbcUserName() {
        return jdbcUserName;
    }

    public void setJdbcUserName(String jdbcUserName) {
        this.jdbcUserName = jdbcUserName;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

    public String getMapperJavaPath() {
        return mapperJavaPath;
    }

    public void setMapperJavaPath(String mapperJavaPath) {
        this.mapperJavaPath = mapperJavaPath;
    }

    public String getMapperXmlPath() {
        return mapperXmlPath;
    }

    public void setMapperXmlPath(String mapperXmlPath) {
        this.mapperXmlPath = mapperXmlPath;
    }
}
