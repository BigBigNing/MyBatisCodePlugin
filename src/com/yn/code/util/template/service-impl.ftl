package ${basePackage}.service.impl.${sign};

import ${basePackage}.dao.mapper.${sign}.${modelNameUpperCamel}Mapper;
import ${basePackage}.model.${sign}.${modelNameUpperCamel};
import ${basePackage}.service.${sign}.${modelNameUpperCamel}Service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
* ${modelNameUpperCamel}ServiceImpl
*
* @author : ${author}
* @date: ${date}
**/
@Service
public class ${modelNameUpperCamel}ServiceImpl implements ${modelNameUpperCamel}Service {

    @Autowired
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

    @Override
    public String save(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Mapper.insertSelective(${modelNameLowerCamel});
        return "新增成功";
    }

    @Override
    public String deleteById(Integer id) {
        return null;
    }

    @Override
    public String update(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Mapper.updateByPrimaryKeySelective(${modelNameLowerCamel});
        return "修改成功";
    }

    @Override
    public ${modelNameUpperCamel} findById(Integer id) {
        return ${modelNameLowerCamel}Mapper.selectByPrimaryKey(id);
    }

}
