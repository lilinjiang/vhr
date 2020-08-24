package com.jiang.vhr.controller.system.basic;

import com.jiang.vhr.model.JobLevel;
import com.jiang.vhr.service.joblevel.JobLevelService;
import com.jiang.vhr.util.RespUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-08  16:06
 * 职称管理controller
 */
@RestController
@RequestMapping("/system/basic/joblevel")

public class JobLevelController {

    @Autowired
    private JobLevelService jobLevelService;

    /**
     * 查询所有职称
     *
     * @return
     */
    @GetMapping("/")
    public List<JobLevel> getAllJobLevels() {
        return jobLevelService.getAllJobLevels();
    }

    /**
     * 新增一个职称
     *
     * @param jobLevel
     * @return
     */
    @PostMapping("/")
    public RespUtil addJobLevel(@RequestBody JobLevel jobLevel) {
        int i = jobLevelService.addJobLevel(jobLevel);
        System.out.println("i = " + i);
        if (i == 1) {
            System.out.println("添加成功！");
            return RespUtil.ok("添加成功！");
        }
        return RespUtil.error("添加失败！");
    }

    /**
     * 修改一个职称
     *
     * @param jobLevel
     * @return
     */
    @PutMapping("/")
    public RespUtil updateLevelById(@RequestBody JobLevel jobLevel) {
        if (jobLevelService.updateLevelById(jobLevel) == 1) {
            return RespUtil.ok("修改成功！");
        }
        return RespUtil.error("修改失败！");
    }

    /**
     * 删除一个职称
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public RespUtil deleteLevelById(@PathVariable Integer id) {
        if (jobLevelService.deleteLevelById(id) == 1) {
            return RespUtil.ok("删除成功！");
        }
        return RespUtil.error("删除失败！");
    }

    /**
     * 删除一个职称
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/")
    public RespUtil deleteLevelByIds(@RequestBody Integer[] ids) {
        if (jobLevelService.deleteLevelByIds(ids) == ids.length) {
            return RespUtil.ok("删除成功！");
        }
        return RespUtil.error("删除失败！");
    }
}
