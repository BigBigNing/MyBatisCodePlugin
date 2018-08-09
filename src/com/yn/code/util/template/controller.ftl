package ${controllerGenerateInfo.basePackage};
import ${controllerGenerateInfo.modelPackage}.${controllerGenerateInfo.modelNameUpperCamel};
import ${controllerGenerateInfo.servicePackage}.${controllerGenerateInfo.modelNameUpperCamel}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import com.maidao.commons.model.base.dto.Resp;
import java.util.List;

/**
* ${controllerGenerateInfo.modelNameUpperCamel}Controller
*
* @author : ${controllerGenerateInfo.author}
* @date: ${controllerGenerateInfo.date}
**/
@RestController
@RequestMapping("${controllerGenerateInfo.baseRequestMapping}")
public class ${controllerGenerateInfo.modelNameUpperCamel}Controller {

    @Autowired
    private ${controllerGenerateInfo.modelNameUpperCamel}Service ${controllerGenerateInfo.modelNameLowerCamel}Service;

    @PostMapping
    @ApiOperation("新增")
    public Resp${"<"}String${">"} insert(@ModelAttribute ${controllerGenerateInfo.modelNameUpperCamel} ${controllerGenerateInfo.modelNameLowerCamel}) {
        return ${controllerGenerateInfo.modelNameLowerCamel}Service.insert(${controllerGenerateInfo.modelNameLowerCamel});
    }

    @PutMapping
    @ApiOperation("修改")
    public Resp${"<"}String${">"} update(@ModelAttribute ${controllerGenerateInfo.modelNameUpperCamel} ${controllerGenerateInfo.modelNameLowerCamel}) {
        return ${controllerGenerateInfo.modelNameLowerCamel}Service.update(${controllerGenerateInfo.modelNameLowerCamel});
    }

    @GetMapping("{id}")
    @ApiOperation("查询详情")
    public Resp${"<"}${controllerGenerateInfo.modelNameUpperCamel}${">"} detail(@PathVariable("id") Integer id) {
        return ${controllerGenerateInfo.modelNameLowerCamel}Service.detail(id);
    }

    @GetMapping
    @ApiOperation("查询列表")
    public Resp${"<List<"}${controllerGenerateInfo.modelNameUpperCamel}${">>"} list() {
        return ${controllerGenerateInfo.modelNameLowerCamel}Service.list();
    }
}
