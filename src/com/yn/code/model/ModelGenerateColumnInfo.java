package com.yn.code.model;

/**
 * 这里是类描述
 *
 * @author : yangning
 * @date: 2018-6-11
 **/

public class ModelGenerateColumnInfo {
    private String columnComment;
    private String columnJavaTypeName;
    private String columnCamelName;

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnJavaTypeName() {
        return columnJavaTypeName;
    }

    public void setColumnJavaTypeName(String columnJavaTypeName) {
        this.columnJavaTypeName = columnJavaTypeName;
    }

    public String getColumnCamelName() {
        return columnCamelName;
    }

    public void setColumnCamelName(String columnCamelName) {
        this.columnCamelName = columnCamelName;
    }
}
