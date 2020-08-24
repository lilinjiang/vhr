package com.jiang.vhr.service.nation;

import com.jiang.vhr.mapper.nation.NationMapper;
import com.jiang.vhr.model.Nation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-17  21:21
 * 民族业务类
 */
@Service
public class NationServiceImpl implements NationService {

    @Autowired
    private NationMapper nationMapper;

    /**
     * 获取所有民族
     *
     * @return
     */
    @Override
    public List<Nation> getAllNations() {
        return nationMapper.selectList(null);
    }
}
