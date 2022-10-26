package com.atguigu.ggkt.vod.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 讲师
 * </p>
 *
 * @author atguigu
 * @since 2022-10-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Teacher implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 讲师姓名
     */
    private String name;

    /**
     * 讲师简介
     */
    private String intro;

    /**
     * 讲师资历,一句话说明讲师
     */
    private String career;

    /**
     * 头衔 1高级讲师 2首席讲师
     */
    private Integer level;

    /**
     * 讲师头像
     */
    private String avatar;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 入驻时间
     */
    private LocalDate joinDate;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer isDeleted;


}
