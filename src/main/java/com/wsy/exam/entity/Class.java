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
@TableName("class")
@ApiModel(value = "Class对象", description = "")
public class Class implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("班级id")
    @TableId("id")
    private Integer id;

    @ApiModelProperty("班级名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("所属部门id")
    @TableField("department_id")
    private Integer departmentId;

    @ApiModelProperty("所属专业id")
    @TableField("major_id")
    private Integer majorId;

    @ApiModelProperty("班级人数")
    @TableField("user_num")
    private Integer userNum;


}
