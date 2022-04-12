package com.wsy.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
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
@TableName("exam_answer")
@ApiModel(value = "ExamAnswer对象", description = "")
public class ExamAnswer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("本次考试记录id")
    @TableId("answer_id")
    private String answerId;

    @ApiModelProperty("本次考试考生id")
    @TableField("answer_user_id")
    private String answerUserId;

    @ApiModelProperty("本次考试试卷id")
    @TableField("answer_exam_id")
    private String answerExamId;

    @ApiModelProperty("本次考试考生答题列表")
    @TableField("answer_list")
    private String answerList;

    @ApiModelProperty("本次考试成绩")
    @TableField("answer_score")
    private BigDecimal answerScore;

    @ApiModelProperty("考试完成时间")
    @TableField("answer_time")
    private LocalDateTime answerTime;


}
