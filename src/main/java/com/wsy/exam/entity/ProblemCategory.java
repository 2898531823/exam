package com.wsy.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author wsy
 * @since 2022-04-04
 */
@Getter
@Setter
@TableName("problem_category")
@ApiModel(value = "ProblemCategory对象", description = "")
public class ProblemCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(" 科目id")
    @TableId("category_id")
    private Integer categoryId;

    @ApiModelProperty("科目名称")
    @TableField("category_name")
    private String categoryName;

    @ApiModelProperty("科目描述")
    @TableField("category_desc")
    private String categoryDesc;


}
