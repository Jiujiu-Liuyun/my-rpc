package com.zhangyun.rpc.server.session;

import com.zhangyun.rpc.common.domain.Account;
import com.zhangyun.rpc.common.domain.TestD;
import com.zhangyun.rpc.common.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Service
@Slf4j
public class RpcServerIOTest implements InitializingBean {
    @Resource
    private AccountService accountService;

    @Override
    public void afterPropertiesSet() throws Exception {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(9999)) {
                log.info("服务端启动了");
                while (true) {
                    Socket socket = serverSocket.accept();
                    new Thread(() -> {
                        try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
                            TestD request = (TestD) ois.readObject();
                            log.info("测试, req:{}", request.getMethod());
                            oos.writeObject("success");
                            oos.flush();
                        } catch (IOException | ClassNotFoundException e) {
                            log.error("从IO中读取数据错误, e: {}", ExceptionUtils.getStackTrace(e));
                        }
                    }).start();
                }
            } catch (IOException e) {
                log.error("服务器启动失败, e: {}", ExceptionUtils.getStackTrace(e));
            }
        }, "socket监听线程").start();
    }
}
