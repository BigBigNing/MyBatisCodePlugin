package ${mapperGenerateInfo.basePackage};

import ${mapperGenerateInfo.modelPath}.${mapperGenerateInfo.modelNameUpperCamel};
import java.util.List;

/**
 * ${mapperGenerateInfo.tableComment}
 * 表：  ${mapperGenerateInfo.tableName}
 * @author  ${mapperGenerateInfo.author}
 * @date ${mapperGenerateInfo.date}
 */
public interface ${mapperGenerateInfo.modelNameUpperCamel}Mapper {

    /**
     * 全字段新增
     * @param ${mapperGenerateInfo.modelNameLowerCamel}
     * @return 新增条数
     */
    int insert(${mapperGenerateInfo.modelNameUpperCamel} ${mapperGenerateInfo.modelNameLowerCamel});

    /**
     * 根据主键动态修改
     * @param ${mapperGenerateInfo.modelNameLowerCamel}
     * @return 修改条数
     */
    int update(${mapperGenerateInfo.modelNameUpperCamel} ${mapperGenerateInfo.modelNameLowerCamel});

    /**
    * 根据主键查询
    * @param id
    * @return ${mapperGenerateInfo.modelNameUpperCamel}
    */
    ${mapperGenerateInfo.modelNameUpperCamel} queryByID(${mapperGenerateInfo.primaryKeyJavaTypeName} ${mapperGenerateInfo.primaryKey});

    /**
    * 查询列表
    * @return ${mapperGenerateInfo.modelNameUpperCamel}
    */
    List<${mapperGenerateInfo.modelNameUpperCamel}> queryList();
}