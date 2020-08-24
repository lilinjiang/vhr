package com.jiang.vhr.util;

import com.jiang.vhr.model.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author lilinjiang
 * @create 2020-02-11  20:24
 */
public class HrUtils {

    /**
     * 获取当前登录的hr信息
     *
     * @return
     */
    public static Hr getCurrentHr() {
        return (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
