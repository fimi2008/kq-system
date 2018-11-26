package com.lionxxw.kqsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class OptionTemplateDto implements Serializable {

    private Long id;

    private String name;

    private Date createTime;

    private Long createUserId;

    private Date lastUpdateTime;

    private Long lastUpdateUserId;

    private String note;

    private Integer count;

    private String createTimeS; // 格式 yyyy-MM-dd

    private String createTimeE;

    /**
     * 重写equals 方法 用于比较是否为已选的模板
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        if (getClass() != o.getClass()){
            if (o instanceof OrderDinnerOptionDto){
                OrderDinnerOptionDto that = (OrderDinnerOptionDto) o;
                if (id.equals(that.getTempId())){
                    that.setName(name);
                    return true;
                }
                return false;
            }
            return false;
        }else{
            OptionTemplateDto that = (OptionTemplateDto) o;
            return id.equals(that.id);
        }
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}