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
@TableName("problem_type")
@ApiModel(value = "ProblemType对象", description = "")
public class ProblemType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("题目类型id")
    @TableId("type_id")
    private Integer typeId;

    @ApiModelProperty("题目类型名称")
    @TableField("type_name")
    private String typeName;

    @ApiModelProperty("题目类型描述")
    @TableField("type_desc")
    private String typeDesc;


}
