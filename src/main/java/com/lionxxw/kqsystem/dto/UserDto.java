package com.lionxxw.kqsystem.dto;

import com.lionxxw.kqsystem.code.constants.SexEnum;
import com.lionxxw.kqsystem.code.utils.ObjectUtils;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

/**
 * The type User dto.
 * Created by lionxxw on 2016-07-07 15:58:54
 */
public class UserDto implements Serializable{

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String account;

    @Getter
    @Setter
    private String cname;

    @Getter
    @Setter
    private String ename;

    @Getter
    @Setter
    @JsonIgnore
    private String password;

    @Getter
    @Setter
    @JsonIgnore
    private Date createTime;

    @Getter
    @Setter
    @JsonIgnore
    private Date lastLoginTime;

    @Getter
    @Setter
    @JsonIgnore
    private String lastLoginIp;

    @Getter
    private Integer sex;

    public void setSex(Integer sex) {
        this.sex = sex;
        if (ObjectUtils.notNull(sex)){
            setSexName(SexEnum.getSexByIndex(sex));
        }
    }

    @Getter
    @Setter
    private String sexName;

    @Getter
    @Setter
    private String mobile;

    @Getter
    @Setter
    private String email;
}