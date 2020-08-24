package com.jiang.vhr.exception;

import com.jiang.vhr.util.RespUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author 李林江
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public RespUtil sqlException(SQLException e) {
        if (e instanceof SQLIntegrityConstraintViolationException) {
            return RespUtil.error("该数据有关联数据，操作失败!");
        }
        return RespUtil.error("数据库异常，操作失败!");
    }
}