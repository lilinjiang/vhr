package com.jiang.vhr.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 部门
 */
@TableName("department")
public class Department implements Serializable {

    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    //父级编号
    @TableField(value = "parentId")
    private Integer parentId;

    /**
     * 部门路径 此字段用于搜索 比如 .1.28 代表 这条记录是id为 28 父级 id 为 1
     */
    @TableField(value = "depPath")
    private String depPath;

    /**
     * 是否可用
     */
    private Boolean enabled;

    /**
     * 是否具有父级
     */
    @TableField(value = "isParent")
    private Boolean isParent;

    /**
     * 子级
     */
    @TableField(exist = false)
    private List<Department> children = new ArrayList<>();

    @TableField(exist = false)

    private Department parentDep;

    public Department() {
    }

    public Department(String name) {

        this.name = name;
    }

    /**
     * 修改匹配机制为name一直则通过
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(name, that.name);
    }

    /**
     * 修改匹配机制为name一直则通过
     */
    @Override
    public int hashCode() {

        return Objects.hash(name);
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getDepPath() {
        return depPath;
    }

    public void setDepPath(String depPath) {
        this.depPath = depPath;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }

    public List<Department> getChildren() {
        return children;
    }

    public void setChildren(List<Department> children) {
        this.children = children;
    }

    public Department getParentDep() {
        return parentDep;
    }

    public void setParentDep(Department parentDep) {
        this.parentDep = parentDep;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", depPath='" + depPath + '\'' +
                ", enabled=" + enabled +
                ", isParent=" + isParent +
                ", children=" + children +
                ", parentDep=" + parentDep +
                '}';
    }
}