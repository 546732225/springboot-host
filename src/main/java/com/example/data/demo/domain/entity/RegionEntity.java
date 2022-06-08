package com.example.data.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.data.annotation.ExcelAttribute;
import lombok.Data;

import java.io.Serializable;


@TableName(value ="dic_region_zbi_all")
@Data
public class RegionEntity implements Serializable {


    @TableId(value = "ID")
    private String id;

    /**
     * 
     */
    @TableField(value = "REGION_CODE")
    @ExcelAttribute(title = "regionCode",column="A")
    private String regionCode;

    /**
     * 
     */
    @TableField(value = "REGION_VALUE")
    @ExcelAttribute(title = "regionValue",column="F")
    private String regionValue;

    /**
     * 
     */
    @TableField(value = "PARENT_CODE")
    private String parentCode;

    /**
     * 
     */
    @TableField(value = "GRADE")
    private Integer grade;

    /**
     * 
     */
    @TableField(value = "REGION_ALL_VALUE")
    @ExcelAttribute(title = "regionAllValue",column="B")
    private String regionAllValue;

    /**
     * 
     */
    @TableField(value = "USED_REGION_CODE")
    private String usedRegionCode;

    /**
     * 
     */
    @TableField(value = "STATE")
    private Integer state;

    /**
     * 
     */
    @TableField(value = "LOCATION_VALUE")
    private String locationValue;

    /**
     * 
     */
    @TableField(value = "ISDL")
    private Integer isdl;

    /**
     * 
     */
    @TableField(value = "LOCATION_OK")
    private Integer locationOk;

    /**
     * 
     */
    @TableField(value = "PARENT_VALUE")
    private String parentValue;

    /**
     * 
     */
    @TableField(value = "weight")
    private String weight;

}