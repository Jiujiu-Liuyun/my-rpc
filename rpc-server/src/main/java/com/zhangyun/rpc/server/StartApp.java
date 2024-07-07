package com.zhangyun.rpc.server;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Slf4j
@MapperScan("com.zhangyun.rpc.server.db.mapper")
public class StartApp {

    public static void main(String[] args) {
        SpringApplication.run(StartApp.class, args);
        log.info(">>>>>>>>>>>>>>>>>>>>>> 服务启动成功 <<<<<<<<<<<<<<<<<<<<<<");
    }
}
