# idea代码生成插件使用说明
> 遵循MyBatis规范，有图形用户界面，基于窗口(项目)级别，提供参数记忆、路径选择界面、自动匹配路径和包，简化了操作流程，方便随时打开使用，符合大部分公司开发规范，controller、service、model、dao生成项目可选

## 使用方法
### 下载插件
[点此下载MyBatisCodePlugin最新插件](https://oss.mbyundian.com/maidao/plugin/MyBatisCodePlugin1.0.zip) 不要解压直接安装
### 安装插件
File -- Settings -- Plugins -- install plugin from disk -- 选中下载的MyBatisCodePluginLatest.zip文件点ok -- ok -- Restart<br>
<image width="1385" heigh="810" src="https://oss.mbyundian.com/maidao/plugin/install.gif"/>
### 运行插件
idea窗口右下角打开插件<br>
<image width="573" heigh="575" src="https:/oss.mbyundian.com/maidao/plugin/open.png"/>

### 使用插件
#### 步骤一（可省略）
填入基本信息并选择Database（有全局参数记忆功能，填一次后面使用就不用填了）<br>
<image width="573" heigh="575" src="https://oss.mbyundian.com/maidao/plugin/1.png"/><br>
#### 步骤二
填入要生成的TableName，自动匹配模块名（项目级别参数记忆）<br>
<image width="533" heigh="77" src="https://oss.mbyundian.com/maidao/plugin/2.gif"/><br>
#### 步骤三（可省略）
选择项目所在文件夹（ProjectPath）精确到com文件夹就行了，自动匹配项目已有的controller、service、model、mapper（dao）目录，也可手选或填写 （项目级别参数记忆）<br>
<image width="1385" heigh="811" src="https://oss.mbyundian.com/maidao/plugin/3.gif"/><br>
#### 步骤四
选择好要生成的内容，点击"Go"按钮生成，若失败则弹消息提示框提示失败信息<br>
<image width="1385" heigh="807" src="https://oss.mbyundian.com/maidao/plugin/4.gif"/><br>
#### 附加项
选中"全部生成到ProjectPath"，则生成的文件全部保存在ProjectPath路径文件夹（需要选择或填写更加精确的ProjectPath路径）<br>
<image width="1385" heigh="807" src="https://oss.mbyundian.com/maidao/plugin/5.gif"/><br>

### 提示
生成的文件会被强制放到相应文件夹中，有重名则会被替换，生成后Synchronize项目

## 常见问题
- 提示文件生成成功但idea不显示文件
```text
问题解释：idea不会实时刷新用户文件，需要用户手动刷新
解决方法：选中项目--鼠标右键--Synchronize所选项目或文件夹即可
```
- 填了ProjectPath无法匹配到正确的其它模块路径
```text
问题解释：说明ProjectPath没选对或者ProjectPath目录下所有文件夹中不包含controller、service、model、dao文件夹
解决方法：重新选择正确的ProjectPath，controller、service、model、dao也可以手动填写或者选择，若填写的文件本身不存在也会自动生成
```

## 生成代码示例
### Model（使用lombok）
```java
package com.maidao.center.product.web.seller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.util.Date;

/**
 * 商家开票信息表
 * 表：  seller_invoice_info
 * @author  yn
 * @date 2018/08/23 05:10:13
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("商家开票信息表")
public class SellerInvoiceInfo {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "商家id")
    private Integer sellerId;

    @ApiModelProperty(value = "商家名称")
    private String sellerName;

    @ApiModelProperty(value = "开票单位名称")
    private String companyName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
```

### Mapper
```java
package com.maidao.center.product.web.seller;

import com.maidao.center.product.web.seller.SellerInvoiceInfo;
import java.util.List;

/**
 * 商家开票信息表
 * 表：  seller_invoice_info
 * @author  yn
 * @date 2018/08/23 05:10:14
 */
public interface SellerInvoiceInfoMapper {

    /**
     * 全字段新增
     * @param sellerInvoiceInfo
     * @return 新增条数
     */
    int insert(SellerInvoiceInfo sellerInvoiceInfo);

    /**
     * 根据主键动态修改
     * @param sellerInvoiceInfo
     * @return 修改条数
     */
    int update(SellerInvoiceInfo sellerInvoiceInfo);

    /**
    * 根据主键查询
    * @param id
    * @return SellerInvoiceInfo
    */
    SellerInvoiceInfo queryByID(Integer id);

    /**
    * 查询列表
    * @return SellerInvoiceInfo
    */
    List<SellerInvoiceInfo> queryList();
}
```

### MapperXml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.maidao.center.product.web.seller.SellerInvoiceInfoMapper">
    <insert id="insert" parameterType="com.maidao.center.product.web.seller.SellerInvoiceInfo" keyProperty="id" useGeneratedKeys="true">
        INSERT INTO seller_invoice_info
            (seller_id,seller_name,company_name,tax_number,
            address,tel,bank,bank_number,receive_tel,
            verify_user_name,create_time,update_time)
        VALUES (
            #{sellerId,jdbcType=INTEGER},
            #{sellerName,jdbcType=VARCHAR},
            #{companyName,jdbcType=VARCHAR},
            #{createTime,jdbcType=TIMESTAMP},
            #{updateTime,jdbcType=TIMESTAMP}
        )
    </insert>

    <update id="update" parameterType="com.maidao.center.product.web.seller.SellerInvoiceInfo">
        UPDATE seller_invoice_info
        <set>
            <if test="sellerId != null">
                seller_id = #{sellerId,jdbcType=INTEGER},
            </if>
            <if test="sellerName != null">
                seller_name = #{sellerName,jdbcType=VARCHAR},
            </if>
            <if test="updateTime != null">
                update_time = #{updateTime,jdbcType=TIMESTAMP},
            </if>
        </set>
        WHERE id = #{id,jdbcType=INTEGER}
    </update>

    <select id="queryByID" parameterType="Integer" resultType="com.maidao.center.product.web.seller.SellerInvoiceInfo">
        SELECT id AS id,
               seller_id AS sellerId,
               seller_name AS sellerName,
               company_name AS companyName,
               tax_number AS taxNumber,
               address AS address,
               verify_user_name AS verifyUserName,
               create_time AS createTime,
               update_time AS updateTime
          FROM seller_invoice_info
         WHERE id = #{id,jdbcType=INTEGER}
    </select>

    <select id="queryList" resultType="com.maidao.center.product.web.seller.SellerInvoiceInfo">
        SELECT id AS id,
               seller_id AS sellerId,
               seller_name AS sellerName,
               company_name AS companyName,
               tax_number AS taxNumber,
               address AS address,
               tel AS tel,
               verify_user_name AS verifyUserName,
          FROM seller_invoice_info
    </select>
</mapper>
```

### Controller
```java
package com.maidao.center.product.web.seller;
import com.maidao.center.product.web.seller.SellerInvoiceInfo;
import com.maidao.center.product.web.seller.SellerInvoiceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import com.maidao.commons.model.base.dto.Resp;
import java.util.List;

/**
* SellerInvoiceInfoController
*
* @author : yn
* @date: 2018/08/23 05:10:14
**/
@RestController
@RequestMapping("seller/invoice/info")
public class SellerInvoiceInfoController {

    @Autowired
    private SellerInvoiceInfoService sellerInvoiceInfoService;

    @PostMapping
    @ApiOperation("新增")
    public Resp<String> insert(@ModelAttribute SellerInvoiceInfo sellerInvoiceInfo) {
        return sellerInvoiceInfoService.insert(sellerInvoiceInfo);
    }

    @PutMapping
    @ApiOperation("修改")
    public Resp<String> update(@ModelAttribute SellerInvoiceInfo sellerInvoiceInfo) {
        return sellerInvoiceInfoService.update(sellerInvoiceInfo);
    }

    @GetMapping("{id}")
    @ApiOperation("查询详情")
    public Resp<SellerInvoiceInfo> detail(@PathVariable("id") Integer id) {
        return sellerInvoiceInfoService.detail(id);
    }

    @GetMapping
    @ApiOperation("查询列表")
    public Resp<List<SellerInvoiceInfo>> list() {
        return sellerInvoiceInfoService.list();
    }
}
```

### Service
```java
package com.maidao.center.product.web.seller;
import com.maidao.center.product.web.seller.SellerInvoiceInfo;
import com.maidao.commons.model.base.dto.Resp;
import java.util.List;

/**
* SellerInvoiceInfoService
*
* @author : yn
* @date: 2018/08/23 05:10:14
**/
public interface SellerInvoiceInfoService {

    /**
     * 新增
     * @param sellerInvoiceInfo
     * @return String
     */
    Resp<String> insert(SellerInvoiceInfo sellerInvoiceInfo);

    /**
     * 修改
     * @param sellerInvoiceInfo
     * @return String
     */
    Resp<String> update(SellerInvoiceInfo sellerInvoiceInfo);

    /**
     * 查询
     * @param id
     * @return SellerInvoiceInfo
     */
    Resp<SellerInvoiceInfo> detail(Integer id);

    /**
    * 查询列表
    * @return SellerInvoiceInfo
    */
    Resp<List<SellerInvoiceInfo>> list();
}
```

### ServiceImpl
```java
package com.maidao.center.product.web.seller.impl;

import com.maidao.center.product.web.seller.SellerInvoiceInfoMapper;
import com.maidao.center.product.web.seller.SellerInvoiceInfo;
import com.maidao.center.product.web.seller.SellerInvoiceInfoService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.maidao.commons.model.base.dto.Resp;
import java.util.List;


/**
* SellerInvoiceInfoServiceImpl
*
* @author : yn
* @date: 2018/08/23 05:10:14
**/
@Service
public class SellerInvoiceInfoServiceImpl implements SellerInvoiceInfoService {

    @Autowired
    private SellerInvoiceInfoMapper sellerInvoiceInfoMapper;

    @Override
    public Resp<String> insert(SellerInvoiceInfo sellerInvoiceInfo) {
        sellerInvoiceInfoMapper.insert(sellerInvoiceInfo);
        return Resp.success("新增成功");
    }

    @Override
    public Resp<String> update(SellerInvoiceInfo sellerInvoiceInfo) {
        sellerInvoiceInfoMapper.update(sellerInvoiceInfo);
        return Resp.success("修改成功");
    }

    @Override
    public Resp<SellerInvoiceInfo> detail(Integer id) {
        return Resp.success(sellerInvoiceInfoMapper.queryByID(id));
    }

    @Override
    public Resp<List<SellerInvoiceInfo>> list() {
        return Resp.success(sellerInvoiceInfoMapper.queryList());
    }
}
```

## 插件源码
```text
https://github.com/BigBigNing/MyBatisCodePlugin
```

有bug或建议及时反馈
