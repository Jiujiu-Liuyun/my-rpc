package com.zhangyun.rpc.client.session;

import com.zhangyun.rpc.common.domain.Account;
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

    @Override
    public void afterPropertiesSet() throws Exception {
        new Thread(() -> {
            try (Socket socket = new Socket("127.0.0.1", 8899)) {
                try(ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
                    oos.writeLong(1);
                    oos.flush();
                    Account account = (Account) ois.readObject();
                    System.out.println(account);
                } catch (IOException | ClassNotFoundException e){
                    System.out.println("从IO中读取数据错误");
                }
            } catch (IOException e) {
                System.out.println("服务器启动失败");
            }
        }).start();
    }
}
