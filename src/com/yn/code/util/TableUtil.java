package com.yn.code.util;

import com.intellij.openapi.diagnostic.Logger;
import com.yn.code.model.TableColumn;
import com.yn.code.model.TableInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableUtil {
    private String host;
    private String database;
    private String user;
    private String pwd;
    private static final Logger LOGGER = Logger.getInstance(TableUtil.class);

    public TableUtil(String host,String database, String user, String pwd) {
        this.host = host;
        this.user = user;
        this.pwd = pwd;
        this.database = database;
    }

    public TableUtil(String host, String user, String pwd) {
        this.host = host;
        this.user = user;
        this.pwd = pwd;
    }

    public TableInfo getTableInfo(String tableName) throws MyException{
        JDBCUtil jdbcUtil = new JDBCUtil(host,database,user,pwd);
        StringBuilder sql = new StringBuilder()
                .append("SHOW TABLE STATUS WHERE NAME = '").append(tableName).append("'");
        String tableComment = "";
        try {
            ResultSet resultSet = jdbcUtil.getResultSet(sql.toString());
            while (resultSet.next()) {
                tableComment = resultSet.getString("Comment");
            }
            jdbcUtil.jdbcClose();
        } catch (MyException e) {
            LOGGER.info(e);
            throw new MyException(e.getMeg());
        } catch (SQLException e){
            LOGGER.info(e);
            throw new MyException("Table name is wrong!");
        }
        if(CommonUtil.isNullOrEmpty(tableComment)){
            throw new MyException("Table comment is empty!");
        }
        TableInfo tableBase = new TableInfo();
        tableBase.setTableName(tableName);
        tableBase.setTableComment(tableComment);
        tableBase.setTableColumns(getTableColumns(tableName));
        return tableBase;
    }

    public List<TableColumn> getTableColumns(String tableName) throws MyException{
        JDBCUtil jdbcUtil = new JDBCUtil(host,database,user,pwd);
        StringBuilder sql = new StringBuilder()
                .append("SELECT column_name,column_comment,data_type,column_key FROM information_schema.columns")
                .append(" WHERE table_schema = '").append(database)
                .append("' AND table_name = '").append(tableName).append("'");
        List<TableColumn> tableColumns = new ArrayList<>();
        try {
            ResultSet resultSet = jdbcUtil.getResultSet(sql.toString());
            while (resultSet.next()) {
                TableColumn tableColumn = new TableColumn();
                tableColumn.setColumnName(resultSet.getString("column_name"));
                tableColumn.setColumnComment(resultSet.getString("column_comment"));
                tableColumn.setDataType(resultSet.getString("data_type"));
                if("PRI".equals(resultSet.getString("column_key"))){
                    tableColumn.setPrimaryKey(true);
                }else {
                    tableColumn.setPrimaryKey(false);
                }
                tableColumns.add(tableColumn);
            }
            jdbcUtil.jdbcClose();
        } catch (MyException e) {
            LOGGER.info(e);
            throw new MyException(e.getMeg());
        } catch (SQLException e){
            LOGGER.info(e);
            throw new MyException("Table name is wrong!");
        }
        return tableColumns;
    }

    public List<String> getAllDatabase() throws MyException{
        JDBCUtil jdbcUtil = new JDBCUtil(host,user,pwd);
        StringBuilder sql = new StringBuilder().append("SHOW DATABASES");
        List<String> databases = new ArrayList<>();
        try {
            ResultSet resultSet = jdbcUtil.getResultSet(sql.toString());
            while (resultSet.next()) {
                String database = resultSet.getString("Database");
                databases.add(database);
            }
            jdbcUtil.jdbcClose();
        } catch (MyException e) {
            LOGGER.info(e);
            throw new MyException(e.getMeg());
        } catch (SQLException e){
            LOGGER.info(e);
            throw new MyException(e.getMessage());
        }
        return databases;
    }

}
