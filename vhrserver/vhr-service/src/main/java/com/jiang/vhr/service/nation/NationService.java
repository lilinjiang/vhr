package com.jiang.vhr.service.nation;

import com.jiang.vhr.model.Nation;

import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-17  21:20
 */
public interface NationService {
    /**
     * 获取所有民族
     *
     * @return
     */
    List<Nation> getAllNations();
}
