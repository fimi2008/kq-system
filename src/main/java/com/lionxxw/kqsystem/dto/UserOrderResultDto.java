package com.lionxxw.kqsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class UserOrderResultDto implements Serializable {
    private Long id;

    private Long userId;

    private Long orderId;

    private Long optionId;

    private String userCname;

    private String userEname;

    private Date createTime;

    private Date lastUpdateTime;

    private Integer state;

    private Boolean isAppend;

    private Long tempId;

    public enum ResultState{
        confirm(1, "已点"),cancel(-1, "取消");
        private int state;
        private String value;

        ResultState(int state, String value) {
            this.state = state;
            this.value = value;
        }

        public static String getValue(int state){
            ResultState[] values = ResultState.values();
            for (ResultState v : values)
                if (v.state == state) {
                    return v.value;
                }
            return "";
        }
    }
}