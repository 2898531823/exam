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
@TableName("major")
@ApiModel(value = "Major对象", description = "")
public class Major implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("专业id")
    @TableId("id")
    private Integer id;

    @ApiModelProperty("专业名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("所属院系id")
    @TableField("department_id")
    private Integer departmentId;


}
