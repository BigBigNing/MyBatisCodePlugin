package ${basePackage}.controller.${sign};
import ${basePackage}.model.${sign}.${modelNameUpperCamel};
import ${basePackage}.service.${sign}.${modelNameUpperCamel}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

/**
* ${modelNameUpperCamel}Controller
*
* @author : ${author}
* @date: ${date}
**/
@RestController
@RequestMapping("/${baseRequestMapping}/")
public class ${modelNameUpperCamel}Controller {

    @Autowired
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @RequestMapping("add")
    @ApiOperation("新增")
    public String add(@ModelAttribute ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        return ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation("删除")
    public String delete(@PathVariable("id") Integer id) {
        return ${modelNameLowerCamel}Service.deleteById(id);
    }

    @PostMapping("update")
    @ApiOperation("修改")
    public String update(@ModelAttribute ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        return ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
    }

    @GetMapping("detail/{id}")
    @ApiOperation("查询详情")
    public ${modelNameUpperCamel} detail(@PathVariable("id") Integer id) {
        return ${modelNameLowerCamel}Service.findById(id);
    }
}
