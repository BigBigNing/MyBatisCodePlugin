package ${controllerGenerateInfo.basePackage};
import ${controllerGenerateInfo.modelPackage}.${controllerGenerateInfo.modelNameUpperCamel};
import ${controllerGenerateInfo.servicePackage}.${controllerGenerateInfo.modelNameUpperCamel}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

/**
* ${controllerGenerateInfo.modelNameUpperCamel}Controller
*
* @author : ${controllerGenerateInfo.author}
* @date: ${controllerGenerateInfo.date}
**/
@RestController
@RequestMapping("/${controllerGenerateInfo.baseRequestMapping}/")
public class ${controllerGenerateInfo.modelNameUpperCamel}Controller {

    @Autowired
    private ${controllerGenerateInfo.modelNameUpperCamel}Service ${controllerGenerateInfo.modelNameLowerCamel}Service;

    @RequestMapping("add")
    @ApiOperation("新增")
    public String add(@ModelAttribute ${controllerGenerateInfo.modelNameUpperCamel} ${controllerGenerateInfo.modelNameLowerCamel}) {
        return ${controllerGenerateInfo.modelNameLowerCamel}Service.save(${controllerGenerateInfo.modelNameLowerCamel});
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation("删除")
    public String delete(@PathVariable("id") Integer id) {
        return ${controllerGenerateInfo.modelNameLowerCamel}Service.deleteById(id);
    }

    @PostMapping("update")
    @ApiOperation("修改")
    public String update(@ModelAttribute ${controllerGenerateInfo.modelNameUpperCamel} ${controllerGenerateInfo.modelNameLowerCamel}) {
        return ${controllerGenerateInfo.modelNameLowerCamel}Service.update(${controllerGenerateInfo.modelNameLowerCamel});
    }

    @GetMapping("detail/{id}")
    @ApiOperation("查询详情")
    public ${controllerGenerateInfo.modelNameUpperCamel} detail(@PathVariable("id") Integer id) {
        return ${controllerGenerateInfo.modelNameLowerCamel}Service.findById(id);
    }
}
