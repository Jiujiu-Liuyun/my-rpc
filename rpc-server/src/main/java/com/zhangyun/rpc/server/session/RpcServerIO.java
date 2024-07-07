package com.zhangyun.rpc.server.session;

import com.zhangyun.rpc.common.domain.RpcRequest;
import com.zhangyun.rpc.common.domain.RpcResponse;
import com.zhangyun.rpc.common.service.AccountService;
import com.zhangyun.rpc.common.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

@Service
@Slf4j
public class RpcServerIO implements InitializingBean {
    @Resource
    private AccountService accountService;

    @Override
    public void afterPropertiesSet() throws Exception {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(8899)) {
                log.info("服务端启动了");
                while (true) {
                    Socket socket = serverSocket.accept();
                    new Thread(() -> {
                        try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
                            RpcRequest request = (RpcRequest) ois.readObject();
                            log.info("发起远程调用, request:{}", request);
                            Object bean = SpringContextUtils.getBean(request.getServiceClz());
                            Method method = request.getServiceClz().getMethod(request.getMethod(), request.getParamsTypes());
                            Object result = method.invoke(bean, request.getParams());
                            log.info("远程调用成功, response:{}", result);
                            oos.writeObject(RpcResponse.success(result));
                            oos.flush();
                        } catch (IOException | ClassNotFoundException | IllegalAccessException |
                                 InvocationTargetException | NoSuchMethodException e) {
                            System.out.println("从IO中读取数据错误");
                        }
                    }).start();
                }
            } catch (IOException e) {
                System.out.println("服务器启动失败");
            }
        }, "socket监听线程").start();
    }
}
