package com.zhangyun.rpc.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Slf4j
@EnableOpenApi
public class StartApp {

    public static void main(String[] args) {
        SpringApplication.run(StartApp.class, args);
        log.info(">>>>>>>>>>>>>>>>>>>>>> 服务启动成功 <<<<<<<<<<<<<<<<<<<<<<");
    }
}