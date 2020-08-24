package com.jiang.vhr.service.position;

import com.jiang.vhr.model.Position;

import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-07  22:35
 */
public interface PositionService {

    /**
     * 获取所有职位
     *
     * @return
     */
    List<Position> getAllPositions();

    /**
     * 添加一个职位
     *
     * @param position
     * @return
     */
    Integer addPosition(Position position);

    /**
     * 修改职位
     *
     * @param position
     * @return
     */
    Integer updatePosition(Position position);


    /**
     * 删除一个职位
     *
     * @param id
     * @return
     */
    Integer deletePositionById(Integer id);

    /**
     * 根据id数组批量删除职位
     *
     * @param ids
     * @return
     */
    Integer deletePositionsByIds(Integer[] ids);
}
