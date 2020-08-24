package com.jiang.vhr.service.joblevel;

import com.jiang.vhr.model.JobLevel;

import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-08  16:03
 */
public interface JobLevelService {
    /**
     * 获取全部的职称
     *
     * @return
     */
    List<JobLevel> getAllJobLevels();

    /**
     * 新增一个职称
     *
     * @param jobLevel
     * @return
     */
    Integer addJobLevel(JobLevel jobLevel);

    /**
     * 根据id修改职称
     *
     * @param jobLevel
     * @return
     */
    Integer updateLevelById(JobLevel jobLevel);

    /**
     * 根据id删除一个职称
     *
     * @param id
     * @return
     */
    Integer deleteLevelById(Integer id);

    /**
     * 根据id批量删除职称
     *
     * @param ids
     * @return
     */
    Integer deleteLevelByIds(Integer[] ids);
}
