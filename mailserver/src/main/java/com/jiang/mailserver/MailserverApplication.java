package com.jiang.mailserver;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author DEEL
 */
@EnableRabbit
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MailserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailserverApplication.class, args);
    }

    /**
     * 创建队列
     *
     * @return
     */
    @Bean
    Queue queue() {
        return new Queue("vhr.mail.welcome");
    }
}
