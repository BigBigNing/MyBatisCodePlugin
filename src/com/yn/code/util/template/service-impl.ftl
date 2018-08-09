package ${serviceGenerateInfo.basePackage}.impl;

import ${serviceGenerateInfo.mapperPackage}.${serviceGenerateInfo.modelNameUpperCamel}Mapper;
import ${serviceGenerateInfo.modelPackage}.${serviceGenerateInfo.modelNameUpperCamel};
import ${serviceGenerateInfo.servicePackage}.${serviceGenerateInfo.modelNameUpperCamel}Service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.maidao.commons.model.base.dto.Resp;
import java.util.List;


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
    public Resp${"<"}String${">"} insert(${serviceGenerateInfo.modelNameUpperCamel} ${serviceGenerateInfo.modelNameLowerCamel}) {
        ${serviceGenerateInfo.modelNameLowerCamel}Mapper.insert(${serviceGenerateInfo.modelNameLowerCamel});
        return Resp.success("新增成功");
    }

    @Override
    public Resp${"<"}String${">"} update(${serviceGenerateInfo.modelNameUpperCamel} ${serviceGenerateInfo.modelNameLowerCamel}) {
        ${serviceGenerateInfo.modelNameLowerCamel}Mapper.update(${serviceGenerateInfo.modelNameLowerCamel});
        return Resp.success("修改成功");
    }

    @Override
    public Resp${"<"}${serviceGenerateInfo.modelNameUpperCamel}${">"} detail(Integer id) {
        return Resp.success(${serviceGenerateInfo.modelNameLowerCamel}Mapper.queryByID(id));
    }

    @Override
    public Resp${"<List<"}${serviceGenerateInfo.modelNameUpperCamel}${">>"} list() {
        return Resp.success(${serviceGenerateInfo.modelNameLowerCamel}Mapper.queryList());
    }
}
