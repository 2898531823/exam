package com.wsy.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@TableName("problem")
@ApiModel(value = "Problem对象", description = "")
public class Problem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("题目id")
    @TableId("problem_id")
    private String problemId;

    @ApiModelProperty("题目内容")
    @TableField("problem_content")
    private String problemContent;

    @ApiModelProperty("题目类型id")
    @TableField("problem_type_id")
    private Integer problemTypeId;

    @ApiModelProperty("题目所属科目id")
    @TableField("problem_category_id")
    private Integer problemCategoryId;

    @ApiModelProperty("题目等级")
    @TableField("problem_level")
    private Integer problemLevel;

    @ApiModelProperty("题目选项")
    @TableField("problem_choice")
    private String problemChoice;

    @ApiModelProperty("题目创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("题目更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}
