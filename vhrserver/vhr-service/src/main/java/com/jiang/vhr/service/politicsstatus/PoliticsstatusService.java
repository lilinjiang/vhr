package com.jiang.vhr.service.politicsstatus;

import com.jiang.vhr.model.Politicsstatus;

import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-17  21:30
 */
public interface PoliticsstatusService {

    /**
     * 获取所有政治面貌
     *
     * @return
     */
    List<Politicsstatus> getAllPoliticsstatus();
}
