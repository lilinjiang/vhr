package com.jiang.vhr.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 职称
 */
@TableName("joblevel")
public class JobLevel implements Serializable {
    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 职称名称
     */
    private String name;

    /**
     * 职称等级
     */
    @TableField(value = "titleLevel")
    private String titleLevel;

    //创建时间
//    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    @TableField(value = "createDate")
    private Date createDate;

    /**
     * 是否可用
     */
    private Boolean enabled;

    @Override
    public String toString() {
        return "JobLevel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", titleLevel='" + titleLevel + '\'' +
                ", createDate=" + createDate +
                ", enabled=" + enabled +
                '}';
    }

    /**
     * 修改匹配机制为name一致则通过
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobLevel jobLevel = (JobLevel) o;
        return Objects.equals(name, jobLevel.name);
    }

    /**
     * 修改匹配机制为name一致则通过
     */
    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    public JobLevel() {

    }

    public JobLevel(String name) {

        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitleLevel() {
        return titleLevel;
    }

    public void setTitleLevel(String titleLevel) {
        this.titleLevel = titleLevel;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}