package com.zhangyun.rpc.server.session;

import com.zhangyun.rpc.common.domain.Account;
import com.zhangyun.rpc.common.service.AccountService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Service
public class RpcServer implements InitializingBean {
    @Resource
    private AccountService accountService;

    @Override
    public void afterPropertiesSet() throws Exception {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(8899)) {
                System.out.println("服务端启动了");
                while (true) {
                    Socket socket = serverSocket.accept();
                    try(ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
                        long id = ois.readLong();
                        Account account = accountService.getAccountById(id);
                        oos.writeObject(account);
                        oos.flush();
                    } catch (IOException e){
                        System.out.println("从IO中读取数据错误");
                    }
                }
            } catch (IOException e) {
                System.out.println("服务器启动失败");
            }
        }).start();
    }
}
