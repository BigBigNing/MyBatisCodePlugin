package ${serviceGenerateInfo.basePackage}.impl;

import ${serviceGenerateInfo.mapperPackage}.${serviceGenerateInfo.modelNameUpperCamel}Mapper;
import ${serviceGenerateInfo.modelPackage}.${serviceGenerateInfo.modelNameUpperCamel};
import ${serviceGenerateInfo.servicePackage}.${serviceGenerateInfo.modelNameUpperCamel}Service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
* ${serviceGenerateInfo.modelNameUpperCamel}ServiceImpl
*
* @author : ${serviceGenerateInfo.author}
* @date: ${serviceGenerateInfo.date}
**/
@Service
public class ${serviceGenerateInfo.modelNameUpperCamel}ServiceImpl implements ${serviceGenerateInfo.modelNameUpperCamel}Service {

    @Autowired
    private ${serviceGenerateInfo.modelNameUpperCamel}Mapper ${serviceGenerateInfo.modelNameLowerCamel}Mapper;

    @Override
    public String save(${serviceGenerateInfo.modelNameUpperCamel} ${serviceGenerateInfo.modelNameLowerCamel}) {
        ${serviceGenerateInfo.modelNameLowerCamel}Mapper.insertSelective(${serviceGenerateInfo.modelNameLowerCamel});
        return "新增成功";
    }

    @Override
    public String deleteById(Integer id) {
        return null;
    }

    @Override
    public String update(${serviceGenerateInfo.modelNameUpperCamel} ${serviceGenerateInfo.modelNameLowerCamel}) {
        ${serviceGenerateInfo.modelNameLowerCamel}Mapper.updateByPrimaryKeySelective(${serviceGenerateInfo.modelNameLowerCamel});
        return "修改成功";
    }

    @Override
    public ${serviceGenerateInfo.modelNameUpperCamel} findById(Integer id) {
        return ${serviceGenerateInfo.modelNameLowerCamel}Mapper.selectByPrimaryKey(id);
    }

}
