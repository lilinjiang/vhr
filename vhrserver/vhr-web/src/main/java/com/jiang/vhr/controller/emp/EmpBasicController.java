package com.jiang.vhr.controller.emp;

import com.github.pagehelper.PageInfo;
import com.jiang.vhr.model.*;
import com.jiang.vhr.service.department.DepartmentService;
import com.jiang.vhr.service.employee.EmployeeService;
import com.jiang.vhr.service.joblevel.JobLevelService;
import com.jiang.vhr.service.nation.NationService;
import com.jiang.vhr.service.politicsstatus.PoliticsstatusService;
import com.jiang.vhr.service.position.PositionService;
import com.jiang.vhr.util.POIUtils;
import com.jiang.vhr.util.RespUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author lilinjiang
 * @create 2020-02-15  19:51
 */
@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private NationService nationService;

    @Autowired
    private PoliticsstatusService politicsstatusService;

    @Autowired
    private JobLevelService jobLevelService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private DepartmentService departmentService;

    /**
     * 分页查询员工
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/")
    public PageInfo getEmployeeByPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            Employee employee,
            @RequestParam(required = false) Date[] beginDateScope) {
        System.out.println("employee = " + employee);
        System.out.println("beginDateScope = " + Arrays.toString(beginDateScope));
        return employeeService.getEmployeeByPage(page, size, employee, beginDateScope);
    }

    /**
     * 新增一个员工
     *
     * @param employee
     * @return
     */
    @PostMapping("/")
    public RespUtil addEmp(@RequestBody Employee employee) {

        if (employeeService.addEmp(employee) == 1) {
            return RespUtil.ok("添加成功！");
        }
        return RespUtil.error("添加失败！");
    }

    @PutMapping("/")
    public RespUtil updateEmp(@RequestBody Employee employee) {
        if (employeeService.updateEmp(employee) == 1) {
            return RespUtil.ok("修改成功！");
        }
        return RespUtil.error("修改失败!");
    }

    /**
     * 根据员工id删除员工
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public RespUtil deleteEmpByEid(@PathVariable Integer id) {
        if (employeeService.deleteEmpByEid(id) == 1) {
            return RespUtil.ok("删除成功！");
        }
        return RespUtil.error("删除失败！");
    }

    /**
     * 获取所有民族
     *
     * @return
     */
    @GetMapping("/nations")
    public List<Nation> getAllNations() {
        return nationService.getAllNations();
    }

    /**
     * 获取全部政治面貌
     *
     * @return
     */
    @GetMapping("/politicsstatus")
    public List<Politicsstatus> getAllPoliticsstatus() {
        return politicsstatusService.getAllPoliticsstatus();
    }

    /**
     * 获取全部职称
     */
    @GetMapping("/joblevels")
    public List<JobLevel> getAllJobLevels() {
        return jobLevelService.getAllJobLevels();
    }

    /**
     * 获取全部职位
     *
     * @return
     */
    @GetMapping("/position")
    public List<Position> getAllPosition() {
        return positionService.getAllPositions();
    }

    /**
     * 生成员工工号
     *
     * @return
     */
    @GetMapping("/maxworkid")
    public RespUtil maxWorkId() {
        return RespUtil.build().setStatus(200).setObj(String.format("%08d", employeeService.maxWorkId() + 1));
    }

    /**
     * 获取全部员工（用于添加员工的选择员工树）
     *
     * @return
     */
    @GetMapping("/deps")
    public List<Department> getAllDeps() {
        return departmentService.getAllDepartments();
    }

    /**
     * 以excel 表形式导出员工数据
     *
     * @return
     */
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportData() {
        return POIUtils.employee2Excel(employeeService.getAllEmployees());
    }


    /**
     * 导入excel表格并存表格数据（员工）
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/import")
    public RespUtil importData(MultipartFile file) throws IOException {
        List<Employee> list = POIUtils.excel2Employee(file, nationService.getAllNations(), politicsstatusService.getAllPoliticsstatus(), departmentService.getAllDepartmentsWithNotChildren(), positionService.getAllPositions(), jobLevelService.getAllJobLevels());
        if (employeeService.addEmps(list) == list.size()) {
            return RespUtil.ok("上传成功");
        }
        return RespUtil.error("上传失败");
    }
}
