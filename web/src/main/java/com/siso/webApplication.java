package com.siso;

import com.siso.WSnetty.netty;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.siso")
@EnableJpaAuditing
@EnableAsync
@EnableTransactionManagement
@EntityScan("com.siso.entity")
@EnableScheduling
@EnableJpaRepositories("com.siso.repository")
public class webApplication {

    public static void main(String[] args) {
        SpringApplication.run(webApplication.class, args);
        netty nettyServer = new netty();
        nettyServer.start();
    }
}