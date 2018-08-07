package com.yn.code.generate;

import com.yn.code.model.ConfigModel;
import com.yn.code.model.TableColumn;
import com.yn.code.model.TableInfo;
import com.yn.code.util.CommonUtil;
import com.yn.code.util.MyException;
import com.yn.code.util.TableUtil;
/**
 * 这里是类描述
 *
 * @author : yangning
 * @date: 2018-6-11
 **/
public class CodeGenerate {
    public void generate(ConfigModel configModel) throws MyException{
        TableUtil tableUtil = new TableUtil(configModel.getJdbcHost(),
                configModel.getJdbcDatabase(),
                configModel.getJdbcUserName(),
                configModel.getJdbcPassword());
        TableInfo tableInfo = tableUtil.getTableInfo(configModel.getTableName());
        if(tableInfo == null){
            throw new MyException("Table is not exist!");
        }
        if(tableInfo.getTableColumns() == null){
            throw new MyException("Table column is empty!");
        }
        String priKey = null;
        for(TableColumn tableColumn:tableInfo.getTableColumns()){
            if(tableColumn.isPrimaryKey()){
                priKey = tableColumn.getColumnName();
            }
        }
        if(CommonUtil.isNullOrEmpty(priKey)){
            new ModelGenerate(configModel,tableInfo).generate();
            throw new MyException("Table has not primaryKey. Model generated!");
        }

        if(configModel.getGenerateModel()){
            new ModelGenerate(configModel,tableInfo).generate();
        }
        if(configModel.getGenerateMapper()){
            new MapperGenerate(configModel,tableInfo).generate();
        }
        if(configModel.getGenerateControllerService()){
            new ControllerGenerate(configModel,tableInfo).generate();
            new ServiceGenerate(configModel,tableInfo).generate();
        }
    }
}
