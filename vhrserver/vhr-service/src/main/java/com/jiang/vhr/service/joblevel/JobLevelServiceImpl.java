package com.jiang.vhr.service.joblevel;

import com.jiang.vhr.mapper.joblevel.JobLevelMapper;
import com.jiang.vhr.model.JobLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-08  16:03
 */
@Service
public class JobLevelServiceImpl implements JobLevelService {

    @Autowired
    private JobLevelMapper jobLevelMapper;

    @Override
    public List<JobLevel> getAllJobLevels() {
        return jobLevelMapper.selectList(null);
    }

    @Override
    public Integer addJobLevel(JobLevel jobLevel) {
        jobLevel.setEnabled(true);
        jobLevel.setCreateDate(new Date());
        return jobLevelMapper.insert(jobLevel);
    }

    @Override
    public Integer updateLevelById(JobLevel jobLevel) {
        return jobLevelMapper.updateById(jobLevel);
    }

    @Override
    public Integer deleteLevelById(Integer id) {
        return jobLevelMapper.deleteById(id);
    }

    @Override
    public Integer deleteLevelByIds(Integer[] ids) {
        return jobLevelMapper.deleteBatchIds(Arrays.asList(ids));
    }
}
