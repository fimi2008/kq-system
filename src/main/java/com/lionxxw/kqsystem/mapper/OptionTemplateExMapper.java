package com.lionxxw.kqsystem.mapper;

import org.apache.ibatis.annotations.Param;

public interface OptionTemplateExMapper {

    void addCount(@Param(value = "tempId") Long tempId);

    void subCount(@Param(value = "tempId") Long tempId);

    void addCountByOptionId(@Param(value = "optionId")Long optionId);

    void subCountByOptionId(@Param(value = "optionId")Long optionId);
}