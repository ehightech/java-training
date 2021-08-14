package com.homework.data.sharding.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.sql.Date;
import lombok.Builder;
import lombok.Data;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author bob
 * @since 2021-08-14
 */
@Data
@Builder
public class OrderInfo implements Serializable {

    /**
     * 订单id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 会员id
     */
    @TableField("member_id")
    private Long memberId;

    /**
     * 订单编号
     */
    @TableField("order_sn")
    private String orderSn;

    /**
     * 用户帐号
     */
    @TableField("member_username")
    private String memberUsername;

    /**
     * 提交时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;

    @Override
    public String toString() {
        return "OrderInfo{" +
        "id=" + id +
        ", memberId=" + memberId +
        ", orderSn=" + orderSn +
        ", memberUsername=" + memberUsername +
        ", createTime=" + createTime +
        "}";
    }
}
