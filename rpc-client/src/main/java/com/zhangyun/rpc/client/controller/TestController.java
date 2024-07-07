package com.zhangyun.rpc.client.controller;

import com.zhangyun.rpc.client.session.ClientProxy;
import com.zhangyun.rpc.common.domain.Account;
import com.zhangyun.rpc.common.service.AccountService;
import com.zhangyun.rpc.common.util.GsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(tags = "测试")
public class TestController {

    @GetMapping("/test")
    @ApiOperation("test")
    public String test() {
        return "test success!!!";
    }

    @PostMapping("/insert")
    @ApiOperation("insert")
    public String insert(@RequestBody Account account) {
        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 8899);
        AccountService accountService = clientProxy.getProxy(AccountService.class);
        return "id: " + accountService.insert(account);
    }

    @GetMapping("/getById")
    @ApiOperation("getById")
    public String getById(@RequestParam("id") Long id) {
        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 8899);
        AccountService accountService = clientProxy.getProxy(AccountService.class);
        return GsonUtil.toJsonString(accountService.getAccountById(id));
    }


}
