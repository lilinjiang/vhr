package com.jiang.vhr.controller.system.basic;

import com.jiang.vhr.model.Position;
import com.jiang.vhr.service.position.PositionService;
import com.jiang.vhr.util.RespUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-07  22:25
 * 职位管理控制器
 */
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    PositionService positionService;

    /**
     * 获取全部职位
     *
     * @return
     */
    @GetMapping("/")
    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

    /**
     * 新增一个职位
     *
     * @param position
     * @return
     */
    @PostMapping("/")
    public RespUtil addPosition(@RequestBody Position position) {
        if (positionService.addPosition(position) == 1) {
            return RespUtil.ok("添加成功！");
        }
        return RespUtil.error("添加失败！");
    }

    /**
     * 修改一个职位
     *
     * @param position
     * @return
     */
    @PutMapping("/")
    public RespUtil updatePosition(@RequestBody Position position) {
        if (positionService.updatePosition(position) == 1) {
            return RespUtil.ok("修改成功！");
        }
        return RespUtil.error("修改失败！");
    }

    /**
     * 删除一个职位
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public RespUtil deletePositionById(@PathVariable Integer id) {
        if (positionService.deletePositionById(id) == 1) {
            return RespUtil.ok("删除成功！");
        }
        return RespUtil.error("删除失败！");
    }

    /**
     * 批量删除职位
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/")
    public RespUtil deletePositionsByIds(@RequestBody Integer[] ids) {
        if (positionService.deletePositionsByIds(ids) == ids.length) {
            return RespUtil.ok("删除成功！");
        }
        return RespUtil.error("删除失败！");
    }


}
