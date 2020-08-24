package com.jiang.vhr.service.politicsstatus;

import com.jiang.vhr.mapper.politicsstatus.PoliticsstatusMapper;
import com.jiang.vhr.model.Politicsstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-17  21:30
 * 政治面貌业务类
 */
@Service
public class PoliticsstatusServiceImpl implements PoliticsstatusService {

    @Autowired
    private PoliticsstatusMapper politicsstatusMapper;

    /**
     * 获取所有政治面貌
     *
     * @return
     */
    @Override
    public List<Politicsstatus> getAllPoliticsstatus() {
        return politicsstatusMapper.selectList(null);
    }
}
