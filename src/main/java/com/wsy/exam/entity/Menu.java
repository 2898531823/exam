package com.wsy.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 菜单表
 * </p>
 *
 * @author wsy
 * @since 2022-04-20
 */
@Getter
@Setter
@TableName("menu")
@ApiModel(value = "Menu对象", description = "菜单表")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("菜单名")
    @TableField("menu_name")
    private String menuName;

    @ApiModelProperty("路由地址")
    @TableField("path")
    private String path;

    @ApiModelProperty("组件路径")
    @TableField("component")
    private String component;

    @ApiModelProperty("菜单状态（0显示 1隐藏）")
    @TableField("visible")
    private String visible;

    @ApiModelProperty("菜单状态（0正常 1停用）")
    @TableField("status")
    private String status;

    @ApiModelProperty("权限标识")
    @TableField("perms")
    private String perms;

    @ApiModelProperty("菜单图标")
    @TableField("icon")
    private String icon;

    @TableField("create_by")
    private Long createBy;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_by")
    private Long updateBy;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("是否删除（0未删除 1已删除）")
    @TableField("del_flag")
    private Integer delFlag;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;


}
