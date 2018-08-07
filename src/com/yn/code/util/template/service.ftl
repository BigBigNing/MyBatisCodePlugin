package ${serviceGenerateInfo.basePackage};
import ${serviceGenerateInfo.modelPackage}.${serviceGenerateInfo.modelNameUpperCamel};

/**
* ${serviceGenerateInfo.modelNameUpperCamel}Service
*
* @author : ${serviceGenerateInfo.author}
* @date: ${serviceGenerateInfo.date}
**/
public interface ${serviceGenerateInfo.modelNameUpperCamel}Service {

    /**
     * 新增
     * @param ${serviceGenerateInfo.modelNameLowerCamel}
     * @return String
     */
    String save(${serviceGenerateInfo.modelNameUpperCamel} ${serviceGenerateInfo.modelNameLowerCamel});

    /**
     * 删除
     * @param id
     * @return String
     */
    String deleteById(Integer id);

    /**
     * 修改
     * @param ${serviceGenerateInfo.modelNameLowerCamel}
     * @return String
     */
    String update(${serviceGenerateInfo.modelNameUpperCamel} ${serviceGenerateInfo.modelNameLowerCamel});

    /**
     * 查询
     * @param id
     * @return ${serviceGenerateInfo.modelNameUpperCamel}
     */
    ${serviceGenerateInfo.modelNameUpperCamel} findById(Integer id);

}
