package com.jiang.vhr.service.position;

import com.jiang.vhr.mapper.position.PositionMapper;
import com.jiang.vhr.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-07  22:35
 */
@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionMapper positionMapper;

    /**
     * 获取所有职位
     *
     * @return
     */
    @Override
    public List<Position> getAllPositions() {
        return positionMapper.selectList(null);
    }

    /**
     * 添加一个职位
     *
     * @param position
     * @return
     */
    @Override
    public Integer addPosition(Position position) {
        //默认可用
        position.setEnabled(true);
        //创建时间为当前时间
        position.setCreateDate(new Date());
        return positionMapper.insert(position);
    }

    /**
     * 修改职位
     *
     * @param position
     * @return
     */
    @Override
    public Integer updatePosition(Position position) {
        return positionMapper.updateById(position);
    }


    /**
     * 根据id删除职位
     *
     * @param id
     * @return
     */
    @Override
    public Integer deletePositionById(Integer id) {
        return positionMapper.deleteById(id);
    }

    /**
     * 根据id数组批量删除职位
     *
     * @param ids
     * @return
     */
    @Override
    public Integer deletePositionsByIds(Integer[] ids) {
        return positionMapper.deleteBatchIds(Arrays.asList(ids));
    }
}
