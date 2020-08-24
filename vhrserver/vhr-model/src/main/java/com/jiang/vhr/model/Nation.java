package com.jiang.vhr.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author lilinjiang
 * 民族
 */
@TableName("nation")
public class Nation implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;


    /**
     * 因为list集合中的 indexOf去查找相同的对象时 会采用所有属性匹配 ，主要依赖于 hashCode与 equals
     * 在这里修改了匹配方式只要name一致则代表两个对象相等
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nation nation = (Nation) o;
        return Objects.equals(name, nation.name);
    }

    /**
     * hashcode方法的匹配机制也该为 匹配name
     *
     * @return
     */
    @Override
    public int hashCode() {

        return Objects.hash(name);
    }


    public Nation() {
    }

    public Nation(String name) {

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
        this.name = name == null ? null : name.trim();
    }
}