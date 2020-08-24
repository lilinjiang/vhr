package com.jiang.vhr.model;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单类
 */
public class Menu implements Serializable {

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", name='" + name + '\'' +
                ", iconCls='" + iconCls + '\'' +
                ", meta=" + meta +
                ", parentId=" + parentId +
                ", enabled=" + enabled +
                ", children=" + children +
                ", roles=" + roles +
                '}';
    }

    /**
     * id
     */
    private Integer id;

    /**
     * 请求该资源的接口
     */
    private String url;

    /**
     * 路径
     */
    private String path;

    /**
     * 对应的前端组件
     */
    private String component;

    /**
     * 前端显示的菜单名
     */
    private String name;

    /**
     * 前端显示的icon
     */
    private String iconCls;

    /**
     * 相关属性 因为vue的router在定义额外的属性时智能放在 meta下
     */
    private Meta meta;

    /**
     * 父级菜单
     */
    private Integer parentId;

    /**
     * 是否可用
     */
    private Boolean enabled;

    /**
     * 子菜单
     */
    private List<Menu> children;

    /**
     * 访问所需角色
     */
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}