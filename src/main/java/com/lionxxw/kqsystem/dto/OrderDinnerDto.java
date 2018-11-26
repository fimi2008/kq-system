package com.lionxxw.kqsystem.dto;

import com.lionxxw.kqsystem.code.utils.DateUtils;
import com.lionxxw.kqsystem.code.utils.ObjectUtils;
import com.lionxxw.kqsystem.code.utils.StringUtils;
import com.lionxxw.kqsystem.entity.OptionTemplate;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class OrderDinnerDto implements Serializable {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private Date orderDate;

    private String orderDateStr;

    public String getOrderDateStr() {
        if (ObjectUtils.notNull(orderDate)){
            return DateUtils.formatDate(orderDate, DateUtils.DATE_FROMAT_DAY);
        }
        return orderDateStr;
    }

    public void setOrderDateStr(String orderDateStr) {
        this.orderDateStr = orderDateStr;
        if (StringUtils.notTrimEmpty(orderDateStr)){
            setOrderDate(DateUtils.getDate(orderDateStr, DateUtils.DATE_FROMAT_DAY));
        }
    }

    @Getter
    @Setter
    private String note;

    @Getter
    @Setter
    private Long publishUserId;

    @Getter
    @Setter
    private Date publishTime;

    @Getter
    @Setter
    private Date createTime;

    @Getter
    @Setter
    private Long createUserId;

    @Getter
    @Setter
    private Integer state;

    @Getter
    @Setter
    private Date lastUpdateTime;

    @Getter
    @Setter
    private Date endTime;

    private String endTimeStr;

    public String getEndTimeStr() {
        if (ObjectUtils.notNull(endTime)){
            return DateUtils.formatDate(endTime, "HH:mm");
        }
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
        if (StringUtils.notTrimEmpty(endTimeStr)){
            setEndTime(DateUtils.getDate(this.orderDateStr + " " +endTimeStr, DateUtils.DATE_FROMAT_MM));
        }
    }

    @Getter
    @Setter
    private List<OrderDinnerOptionDto> options;

    @Getter
    @Setter
    private List<OptionTemplateDto> temps;

    @Getter
    @Setter
    private Long[] tempIds;

    public enum OrderState{
        INIT(0,"未发布"),PUBLISH(1, "已发布"),CANCEL(-1, "已作废"),FINISH(2, "已完成");
        private int state;
        private String value;

        OrderState(int state, String value) {
            this.state = state;
            this.value = value;
        }

        public static String getValue(int state){
            OrderState[] values = OrderState.values();
            for (OrderState v : values){
                if (v.state == state){
                    return v.value;
                }
            }
            return "";
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}