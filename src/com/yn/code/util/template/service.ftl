package ${serviceGenerateInfo.basePackage};
import ${serviceGenerateInfo.modelPackage}.${serviceGenerateInfo.modelNameUpperCamel};
import com.maidao.commons.model.base.dto.Resp;
import java.util.List;

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
    Resp${"<"}String${">"} insert(${serviceGenerateInfo.modelNameUpperCamel} ${serviceGenerateInfo.modelNameLowerCamel});

    /**
     * 修改
     * @param ${serviceGenerateInfo.modelNameLowerCamel}
     * @return String
     */
    Resp${"<"}String${">"} update(${serviceGenerateInfo.modelNameUpperCamel} ${serviceGenerateInfo.modelNameLowerCamel});

    /**
     * 查询
     * @param id
     * @return ${serviceGenerateInfo.modelNameUpperCamel}
     */
    Resp${"<"}${serviceGenerateInfo.modelNameUpperCamel}${">"} detail(Integer id);

    /**
    * 查询列表
    * @return ${serviceGenerateInfo.modelNameUpperCamel}
    */
    Resp${"<List<"}${serviceGenerateInfo.modelNameUpperCamel}${">>"} list();
}
