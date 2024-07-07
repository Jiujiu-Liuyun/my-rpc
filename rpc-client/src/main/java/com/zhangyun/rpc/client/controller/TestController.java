package com.zhangyun.rpc.client.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "测试")
public class TestController {

    @GetMapping("/test")
    @ApiOperation("test")
    public String test() {
        return "success2222";
    }

    @PostMapping("/insert")
    @ApiOperation("insert")
    public String insert() {

        return "";
    }
}
