package com.jiang.mailserver.receiver;

import com.jiang.vhr.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @author lilinjiang
 */
@Component
public class MailReceiver {

    public static final Logger logger = LoggerFactory.getLogger(MailReceiver.class);

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    MailProperties mailProperties;

    /**
     * 模板
     */
    @Autowired
    TemplateEngine templateEngine;

    /**
     * 监听队列
     *
     * @param employee
     */
    @RabbitListener(queues = "vhr.mail.welcome")
    public void handler(Employee employee) {
        logger.info(employee.toString());
        //收到消息，发送邮件
        //构建一个复杂的邮件
        MimeMessage msg = javaMailSender.createMimeMessage();
        //使用MimeMessageHelper对象设置内容//但是要处理异常
        MimeMessageHelper helper = new MimeMessageHelper(msg);
        try {
            //接收人
            helper.setTo(employee.getEmail());
            //发送人
            helper.setFrom(mailProperties.getUsername());
            //主题
            helper.setSubject("入职欢迎");
            //发送时间 为当前
            helper.setSentDate(new Date());

            Context context = new Context();
            //给模板赋值
            context.setVariable("name", employee.getName());
            context.setVariable("posName", employee.getPosition().getName());
            context.setVariable("joblevelName", employee.getJobLevel().getName());
            context.setVariable("departmentName", employee.getDepartment().getName());
            String mail = templateEngine.process("mail", context);
            //填充邮件信息 第二个参数是代表这是一个html
            helper.setText(mail, true);
            //发送邮件
            javaMailSender.send(msg);
        } catch (MessagingException e) {
            logger.error("邮件发送失败：" + e.getMessage());
        } catch (Exception e) {
            logger.error("没有此邮箱地址");
        }
    }
}
