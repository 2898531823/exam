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
 * 考试的详细信息表
 * </p>
 *
 * @author wsy
 * @since 2022-04-04
 */
@Getter
@Setter
@TableName("exam")
@ApiModel(value = "Exam对象", description = "考试的详细信息表")
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("考试表的主键")
    @TableId("exam_id")
    private String examId;

    @ApiModelProperty("考试名称")
    @TableField("exam_name")
    private String examName;

    @ApiModelProperty("考试的预览图")
    @TableField("exam_avatar")
    private String examAvatar;

    @ApiModelProperty("考试描述")
    @TableField("exam_description")
    private String examDescription;

    @ApiModelProperty("当前考试下的题目的id用-连在一起地字符串")
    @TableField("exam_question_ids")
    private String examQuestionIds;

    @ApiModelProperty("当前考试每个单选题的分数")
    @TableField("exam_score_radio")
    private Integer examScoreRadio;

    @ApiModelProperty("当前考试每个多选题的分数")
    @TableField("exam_score_check")
    private Integer examScoreCheck;

    @ApiModelProperty("当前考试每个判断题的分数")
    @TableField("exam_score_judge")
    private Integer examScoreJudge;

    @ApiModelProperty("考试创建者的用户id")
    @TableField("exam_creator_id")
    private String examCreatorId;

    @ApiModelProperty("考试的时间限制，单位为分钟")
    @TableField("exam_time_limit")
    private Integer examTimeLimit;

    @ApiModelProperty("考试有效期开始时间")
    @TableField("exam_start_date")
    private LocalDateTime examStartDate;

    @ApiModelProperty("考试有效期结束时间")
    @TableField("exam_end_date")
    private LocalDateTime examEndDate;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}
