package com.lionxxw.kqsystem.dto;

import com.lionxxw.kqsystem.code.utils.DateUtils;
import com.lionxxw.kqsystem.code.utils.ObjectUtils;
import com.lionxxw.kqsystem.code.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

public class WorkingLogDto implements Serializable {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private Long userId;

    @Getter
    private Date workDate;
    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
        if (ObjectUtils.notNull(workDate) && StringUtils.isTrimEmpty(workDateStr)){
            this.setWorkDateStr(DateUtils.formatDate(workDate, DateUtils.DATE_FROMAT_DAY));
        }
    }

    @Getter
    private String workDateStr;
    public void setWorkDateStr(String workDateStr) {
        this.workDateStr = workDateStr;
        if (StringUtils.notTrimEmpty(workDateStr) && ObjectUtils.isNull(workDate)){
            this.setWorkDate(DateUtils.getDate(workDateStr, DateUtils.DATE_FROMAT_DAY));
        }
    }

    @Getter
    @Setter
    private Date createTime;

    @Getter
    @Setter
    private Date lastUpdateTime;

    @Getter
    @Setter
    private Boolean isDel;

    @Getter
    @Setter
    private String note;

    @Getter
    @Setter
    private String workDateS; // 格式 yyyy-MM-dd

    @Getter
    @Setter
    private String workDateE;
}