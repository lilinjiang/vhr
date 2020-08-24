package com.jiang.vhr.util;

import com.jiang.vhr.model.*;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lilinjiang
 * <p>
 * 导出表格帮助类
 */
public class POIUtils {

    public static ResponseEntity<byte[]> employee2Excel(List<Employee> list) {
        //1. 创建一个 Excel 文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2. 创建文档摘要
        workbook.createInformationProperties();
        //3. 获取并配置文档信息
        DocumentSummaryInformation docInfo = workbook.getDocumentSummaryInformation();
        //文档类别
        docInfo.setCategory("员工信息");
        //文档管理员
        docInfo.setManager("lilinjiang");
        //设置公司信息
        docInfo.setCompany("www.jiang.com");
        //4. 获取文档摘要信息
        SummaryInformation summInfo = workbook.getSummaryInformation();
        //文档标题
        summInfo.setTitle("员工信息表");
        //文档作者
        summInfo.setAuthor("lilinjiang");
        // 文档备注
        summInfo.setComments("本文档由 lilinjiang 提供");
        //5. 创建样式
        //创建标题行的样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        //背景颜色
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        //填充模式
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //日期列样式
        HSSFCellStyle dateCellStyle = workbook.createCellStyle();
        //时间格式化样式
        dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
        //创建表单
        HSSFSheet sheet = workbook.createSheet("员工信息表");
        //设置列的宽度
        sheet.setColumnWidth(0, 5 * 256);
        sheet.setColumnWidth(1, 12 * 256);
        sheet.setColumnWidth(2, 10 * 256);
        sheet.setColumnWidth(3, 5 * 256);
        sheet.setColumnWidth(4, 12 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 10 * 256);
        sheet.setColumnWidth(7, 10 * 256);
        sheet.setColumnWidth(8, 16 * 256);
        sheet.setColumnWidth(9, 12 * 256);
        sheet.setColumnWidth(10, 15 * 256);
        sheet.setColumnWidth(11, 20 * 256);
        sheet.setColumnWidth(12, 16 * 256);
        sheet.setColumnWidth(13, 14 * 256);
        sheet.setColumnWidth(14, 14 * 256);
        sheet.setColumnWidth(15, 12 * 256);
        sheet.setColumnWidth(16, 8 * 256);
        sheet.setColumnWidth(17, 20 * 256);
        sheet.setColumnWidth(18, 20 * 256);
        sheet.setColumnWidth(19, 15 * 256);
        sheet.setColumnWidth(20, 8 * 256);
        sheet.setColumnWidth(21, 25 * 256);
        sheet.setColumnWidth(22, 14 * 256);
        sheet.setColumnWidth(23, 15 * 256);
        sheet.setColumnWidth(24, 15 * 256);
        //6. 创建标题行
        HSSFRow r0 = sheet.createRow(0);

        HSSFCell c0 = r0.createCell(0);
        c0.setCellValue("编号");
        c0.setCellStyle(headerStyle);

        HSSFCell c1 = r0.createCell(1);
        c1.setCellStyle(headerStyle);
        c1.setCellValue("姓名");

        HSSFCell c2 = r0.createCell(2);
        c2.setCellStyle(headerStyle);
        c2.setCellValue("工号");

        HSSFCell c3 = r0.createCell(3);
        c3.setCellStyle(headerStyle);
        c3.setCellValue("性别");

        HSSFCell c4 = r0.createCell(4);
        c4.setCellStyle(headerStyle);
        c4.setCellValue("出生日期");

        HSSFCell c5 = r0.createCell(5);
        c5.setCellStyle(headerStyle);
        c5.setCellValue("身份证号码");

        HSSFCell c6 = r0.createCell(6);
        c6.setCellStyle(headerStyle);
        c6.setCellValue("婚姻状况");

        HSSFCell c7 = r0.createCell(7);
        c7.setCellStyle(headerStyle);
        c7.setCellValue("民族");

        HSSFCell c8 = r0.createCell(8);
        c8.setCellStyle(headerStyle);
        c8.setCellValue("籍贯");

        HSSFCell c9 = r0.createCell(9);
        c9.setCellStyle(headerStyle);
        c9.setCellValue("政治面貌");

        HSSFCell c10 = r0.createCell(10);
        c10.setCellStyle(headerStyle);
        c10.setCellValue("电话号码");

        HSSFCell c11 = r0.createCell(11);
        c11.setCellStyle(headerStyle);
        c11.setCellValue("联系地址");

        HSSFCell c12 = r0.createCell(12);
        c12.setCellStyle(headerStyle);
        c12.setCellValue("所属部门");

        HSSFCell c13 = r0.createCell(13);
        c13.setCellStyle(headerStyle);
        c13.setCellValue("职称");

        HSSFCell c14 = r0.createCell(14);
        c14.setCellStyle(headerStyle);
        c14.setCellValue("职位");

        HSSFCell c15 = r0.createCell(15);
        c15.setCellStyle(headerStyle);
        c15.setCellValue("聘用形式");

        HSSFCell c16 = r0.createCell(16);
        c16.setCellStyle(headerStyle);
        c16.setCellValue("最高学历");

        HSSFCell c17 = r0.createCell(17);
        c17.setCellStyle(headerStyle);
        c17.setCellValue("专业");

        HSSFCell c18 = r0.createCell(18);
        c18.setCellStyle(headerStyle);
        c18.setCellValue("毕业院校");

        HSSFCell c19 = r0.createCell(19);
        c19.setCellStyle(headerStyle);
        c19.setCellValue("入职日期");

        HSSFCell c20 = r0.createCell(20);
        c20.setCellStyle(headerStyle);
        c20.setCellValue("在职状态");

        HSSFCell c21 = r0.createCell(21);
        c21.setCellStyle(headerStyle);
        c21.setCellValue("邮箱");

        HSSFCell c22 = r0.createCell(22);
        c22.setCellStyle(headerStyle);
        c22.setCellValue("合同期限(年)");

        HSSFCell c23 = r0.createCell(23);
        c23.setCellStyle(headerStyle);
        c23.setCellValue("合同起始日期");

        HSSFCell c24 = r0.createCell(24);
        c24.setCellStyle(headerStyle);
        c24.setCellValue("合同终止日期");

        HSSFCell c25 = r0.createCell(25);
        c25.setCellStyle(headerStyle);
        c25.setCellValue("转正日期");

        for (int i = 0; i < list.size(); i++) {
            //获取到当前便利到的员工对象
            Employee emp = list.get(i);
            //创建当前行数据
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(emp.getId());
            row.createCell(1).setCellValue(emp.getName());
            row.createCell(2).setCellValue(emp.getWorkID());
            row.createCell(3).setCellValue(emp.getGender());
            HSSFCell cell4 = row.createCell(4);
            cell4.setCellStyle(dateCellStyle);
            cell4.setCellValue(emp.getBirthday());
            row.createCell(5).setCellValue(emp.getIdCard());
            row.createCell(6).setCellValue(emp.getWedlock());
            row.createCell(7).setCellValue(emp.getNation().getName());
            row.createCell(8).setCellValue(emp.getNativePlace());
            row.createCell(9).setCellValue(emp.getPoliticsstatus().getName());
            row.createCell(10).setCellValue(emp.getPhone());
            row.createCell(11).setCellValue(emp.getAddress());
            row.createCell(12).setCellValue(emp.getDepartment().getName());
            row.createCell(13).setCellValue(emp.getJobLevel().getName());
            row.createCell(14).setCellValue(emp.getPosition().getName());
            row.createCell(15).setCellValue(emp.getEngageForm());
            row.createCell(16).setCellValue(emp.getTiptopDegree());
            row.createCell(17).setCellValue(emp.getSpecialty());
            row.createCell(18).setCellValue(emp.getSchool());
            HSSFCell cell19 = row.createCell(19);
            cell19.setCellStyle(dateCellStyle);
            cell19.setCellValue(emp.getBeginDate());
            row.createCell(20).setCellValue(emp.getWorkState());
            row.createCell(21).setCellValue(emp.getEmail());
            row.createCell(22).setCellValue(emp.getContractTerm());
            HSSFCell cell23 = row.createCell(23);
            cell23.setCellStyle(dateCellStyle);
            cell23.setCellValue(emp.getBeginContract());
            HSSFCell cell24 = row.createCell(24);
            cell24.setCellStyle(dateCellStyle);
            cell24.setCellValue(emp.getEndContract());
            HSSFCell cell25 = row.createCell(25);
            cell25.setCellStyle(dateCellStyle);
            cell25.setCellValue(emp.getConversionTime());
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        try {
            //配置文件名及乱码处理
            headers.setContentDispositionFormData("attachment", new String("员工表.xls".getBytes("UTF-8"), "ISO-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }

    /**
     * Excel 解析成 员工数据集合
     *
     * @param file
     * @param allNations
     * @param allPoliticsstatus
     * @param allDepartments
     * @param allPositions
     * @param allJobLevels
     * @return
     */
    public static List<Employee> excel2Employee(MultipartFile file, List<Nation> allNations, List<Politicsstatus> allPoliticsstatus, List<Department> allDepartments, List<Position> allPositions, List<JobLevel> allJobLevels) {
        //用于保存员工的集合
        List<Employee> list = new ArrayList<>();
        //用于重复使用的员工对象
        Employee employee = null;
        try {
            //1. 创建一个 workbook 对象 通过InputStream去构建一个
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            //2. 获取 workbook 中表单的数量
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                //3. 获取表单 表单中数据就是一行行一列列的那种了
                HSSFSheet sheet = workbook.getSheetAt(i);
                //4. 获取表单中的行数
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    //5. 跳过标题行 如果j等于0说明是第一行 ，也就是标题行
                    if (j == 0) {
                        continue;//跳过标题行
                    }
                    //6. 获取行
                    HSSFRow row = sheet.getRow(j);
                    //row如果获取到空行就跳过
                    if (row == null) {
                        continue;//防止数据中间有空行
                    }
                    //7. 获取列数
                    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                    // 在这里去实例一个空的员工对象
                    employee = new Employee();
                    //遍历每一列去拿到每一列的数据
                    for (int k = 0; k < physicalNumberOfCells; k++) {
                        HSSFCell cell = row.getCell(k);
                        //判断当前列的数据类型
                        switch (cell.getCellType()) {
                            //如果是字符串
                            case STRING:
                                //取出该列的值
                                String cellValue = cell.getStringCellValue();
                                switch (k) {
                                    //员工姓名
                                    case 1:
                                        employee.setName(cellValue);
                                        break;
                                    //工号
                                    case 2:
                                        employee.setWorkID(cellValue);
                                        break;
                                    //性别
                                    case 3:
                                        employee.setGender(cellValue);
                                        break;
                                    //身份证号
                                    case 5:
                                        employee.setIdCard(cellValue);
                                        break;
                                    //婚姻状况
                                    case 6:
                                        employee.setWedlock(cellValue);
                                        break;
                                    //民族
                                    case 7:
                                        //构建一个民族对象去匹配民族集合中的元素获取到匹配到的元素的下标
                                        int nationIndex = allNations.indexOf(new Nation(cellValue));
                                        //根据元素下标去拿到该元素的id这样子就完成了name到id的转换
                                        employee.setNationId(allNations.get(nationIndex).getId());
                                        break;
                                    //籍贯
                                    case 8:
                                        employee.setNativePlace(cellValue);
                                        break;
                                    //政治面貌
                                    case 9:
                                        //与民族的做法一直
                                        int politicstatusIndex = allPoliticsstatus.indexOf(new Politicsstatus(cellValue));
                                        employee.setPoliticId(allPoliticsstatus.get(politicstatusIndex).getId());
                                        break;
                                    //联系电话
                                    case 10:
                                        employee.setPhone(cellValue);
                                        break;
                                    //联系地址
                                    case 11:
                                        employee.setAddress(cellValue);
                                        break;
                                    //部门
                                    case 12:
                                        //与民族做法一致
                                        int departmentIndex = allDepartments.indexOf(new Department(cellValue));
                                        employee.setDepartmentId(allDepartments.get(departmentIndex).getId());
                                        break;
                                    //职称
                                    case 13:
                                        //与民族做法一致
                                        int jobLevelIndex = allJobLevels.indexOf(new JobLevel(cellValue));
                                        employee.setJobLevelId(allJobLevels.get(jobLevelIndex).getId());
                                        break;
                                    //职位
                                    case 14:
                                        //与民族做法一致
                                        int positionIndex = allPositions.indexOf(new Position(cellValue));
                                        employee.setPosId(allPositions.get(positionIndex).getId());
                                        break;
                                    //聘用形式
                                    case 15:
                                        employee.setEngageForm(cellValue);
                                        break;
                                    //学历
                                    case 16:
                                        employee.setTiptopDegree(cellValue);
                                        break;
                                    //所属专业
                                    case 17:
                                        employee.setSpecialty(cellValue);
                                        break;
                                    //毕业院校
                                    case 18:
                                        employee.setSchool(cellValue);
                                        break;
                                    //在职状态
                                    case 20:
                                        employee.setWorkState(cellValue);
                                        break;
                                    //电子邮箱
                                    case 21:
                                        employee.setEmail(cellValue);
                                        break;
                                }
                                break;
                            //因为导出表格时只有String于date两种格式所以只要进入了default那么肯定就是代表该单元格属于日期
                            default: {
                                switch (k) {
                                    //出生日期
                                    case 4:
                                        employee.setBirthday(cell.getDateCellValue());
                                        break;
                                    //入职日期
                                    case 19:
                                        employee.setBeginDate(cell.getDateCellValue());
                                        break;
                                    //合同起始日期
                                    case 23:
                                        employee.setBeginContract(cell.getDateCellValue());
                                        break;
                                    //合同截止日期
                                    case 24:
                                        employee.setEndContract(cell.getDateCellValue());
                                        break;
                                    //合同期限
                                    case 22:
                                        employee.setContractTerm(cell.getNumericCellValue());
                                        break;
                                    //转正日期
                                    case 25:
                                        employee.setConversionTime(cell.getDateCellValue());
                                        break;
                                }
                            }
                            break;
                        }
                    }
                    list.add(employee);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
