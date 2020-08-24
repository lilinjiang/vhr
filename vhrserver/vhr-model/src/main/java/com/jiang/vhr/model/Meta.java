package com.jiang.vhr.model;

import java.io.Serializable;

/**
 * 与菜单类相关的属性（用于前端判断）
 */
public class Meta implements Serializable {

    @Override
    public String toString() {
        return "Meta{" +
                "keepAlive=" + keepAlive +
                ", requireAuth=" + requireAuth +
                '}';
    }

    /**
     * 保存组件不销毁（导活）
     */
    private Boolean keepAlive;
    /**
     * 该组件是否只能登录才能访问
     */
    private Boolean requireAuth;

    public Boolean getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Boolean getRequireAuth() {
        return requireAuth;
    }

    public void setRequireAuth(Boolean requireAuth) {
        this.requireAuth = requireAuth;
    }
}
