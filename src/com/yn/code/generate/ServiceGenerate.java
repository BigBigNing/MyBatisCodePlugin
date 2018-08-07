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
 * @date: 2018-8-6
 **/
public class ServiceGenerate {
    private ConfigModel configModel;
    private TableInfo tableInfo;

    public ServiceGenerate(ConfigModel configModel, TableInfo tableInfo) {
        this.configModel = configModel;
        this.tableInfo = tableInfo;
    }

    public void generate() throws MyException{
        ServiceGenerateInfo serviceGenerateInfo = new ServiceGenerateInfo();
        serviceGenerateInfo.setAuthor(configModel.getAuthor());
        serviceGenerateInfo.setBasePackage(CommonUtil.getPackageNameByPath(configModel.getServicePath()));
        serviceGenerateInfo.setModelPackage(CommonUtil.getPackageNameByPath(configModel.getModelPath()));
        serviceGenerateInfo.setMapperPackage(CommonUtil.getPackageNameByPath(configModel.getMapperJavaPath()));
        serviceGenerateInfo.setServicePackage(CommonUtil.getPackageNameByPath(configModel.getServicePath()));
        serviceGenerateInfo.setDate(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(new Date()));
        serviceGenerateInfo.setModelNameUpperCamel(CommonUtil.getNameUpperCamel(tableInfo.getTableName()));
        serviceGenerateInfo.setModelNameLowerCamel(CommonUtil.getNameLowerCamel(tableInfo.getTableName()));
        serviceGenerateInfo.setTableComment(tableInfo.getTableComment());
        serviceGenerateInfo.setTableName(tableInfo.getTableName());

        Map<String, Object> root = new HashMap<>(1);
        root.put("serviceGenerateInfo", serviceGenerateInfo);
        String serviceName = CommonUtil.getNameUpperCamel(configModel.getTableName()) + "Service.java";
        String serviceImplName = CommonUtil.getNameUpperCamel(configModel.getTableName()) + "ServiceImpl.java";
        FreeMarkUtil.generateFile(root, "service.ftl", configModel.getServicePath(), serviceName);
        FreeMarkUtil.generateFile(root, "service-impl.ftl", configModel.getServicePath() + "/impl", serviceImplName);
    }
}
