package com.yn.code.generate;

import com.yn.code.model.*;
import com.yn.code.util.CommonUtil;
import com.yn.code.util.DataTypeEnum;
import com.yn.code.util.FreeMarkUtil;
import com.yn.code.util.MyException;

import java.text.SimpleDateFormat;
import java.util.*;
/**
 * 这里是类描述
 *
 * @author : yangning
 * @date: 2018-6-11
 **/
public class ModelGenerate {
    private ConfigModel configModel;
    private TableInfo tableInfo;

    public ModelGenerate(ConfigModel configModel, TableInfo tableInfo) {
        this.configModel = configModel;
        this.tableInfo = tableInfo;
    }

    public void generate() throws MyException{
        ModelGenerateInfo modelGenerateInfo = new ModelGenerateInfo();
        modelGenerateInfo.setAuthor(configModel.getAuthor());
        modelGenerateInfo.setBasePackage(CommonUtil.getPackageNameByPath(configModel.getModelPath()));
        modelGenerateInfo.setDate(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(new Date()));
        modelGenerateInfo.setModelNameUpperCamel(CommonUtil.getNameUpperCamel(tableInfo.getTableName()));
        modelGenerateInfo.setTableComment(tableInfo.getTableComment());
        modelGenerateInfo.setTableName(tableInfo.getTableName());
        List<ModelGenerateColumnInfo> modelGenerateColumnInfos = new ArrayList<>();
        List<String> importList = new ArrayList<>();
        for (TableColumn tableColumn : tableInfo.getTableColumns()) {
            ModelGenerateColumnInfo modelGenerateColumnInfo = new ModelGenerateColumnInfo();
            String javaTypeName = DataTypeEnum.getJavaTypeNameByDataType(tableColumn.getDataType());
            modelGenerateColumnInfo.setColumnComment(tableColumn.getColumnComment());
            modelGenerateColumnInfo.setColumnJavaTypeName(javaTypeName);
            modelGenerateColumnInfo.setColumnCamelName(CommonUtil.getNameLowerCamel(tableColumn.getColumnName()));
            modelGenerateColumnInfos.add(modelGenerateColumnInfo);
            String columnJavaTypeName = DataTypeEnum.getJavaTypeByDataType(tableColumn.getDataType());
            if (CommonUtil.isNeedImport(javaTypeName) && !importList.contains(columnJavaTypeName)) {
                importList.add(columnJavaTypeName);
            }
        }
        modelGenerateInfo.setColumnList(modelGenerateColumnInfos);
        modelGenerateInfo.setImportList(importList);
        Map<String, Object> root = new HashMap<>(1);
        root.put("modelGenerateInfo", modelGenerateInfo);
        String fileName = CommonUtil.getNameUpperCamel(configModel.getTableName()) + ".java";
        FreeMarkUtil.generateFile(root, "model.ftl", configModel.getModelPath(), fileName);
    }
}
