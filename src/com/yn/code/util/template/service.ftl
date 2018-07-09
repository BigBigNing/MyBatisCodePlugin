package ${basePackage}.service.${sign};
import ${basePackage}.model.${sign}.${modelNameUpperCamel};

/**
* ${modelNameUpperCamel}Service
*
* @author : ${author}
* @date: ${date}
**/
public interface ${modelNameUpperCamel}Service {

    /**
     * 新增
     * @param ${modelNameLowerCamel}
     * @return String
     */
    String save(${modelNameUpperCamel} ${modelNameLowerCamel});

    /**
     * 删除
     * @param id
     * @return String
     */
    String deleteById(Integer id);

    /**
     * 修改
     * @param ${modelNameLowerCamel}
     * @return String
     */
    String update(${modelNameUpperCamel} ${modelNameLowerCamel});

    /**
     * 查询
     * @param id
     * @return ${modelNameUpperCamel}
     */
    ${modelNameUpperCamel} findById(Integer id);

}
