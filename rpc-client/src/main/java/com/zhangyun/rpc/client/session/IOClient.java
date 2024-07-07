package com.zhangyun.rpc.client.session;

import com.zhangyun.rpc.common.domain.Account;
import com.zhangyun.rpc.common.domain.RpcRequest;
import com.zhangyun.rpc.common.domain.RpcResponse;
import com.zhangyun.rpc.common.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Slf4j
public class IOClient {

    public static RpcResponse sendRequest(String host, int port, RpcRequest request) {
        try (Socket socket = new Socket(host, port);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            log.info("发起远程调用, request:{}", request);
            oos.writeObject(request);
            oos.flush();
            RpcResponse response = (RpcResponse) ois.readObject();
            log.info("远程调用成功, response:{}", response);
            return response;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("从IO中读取数据错误");
        }
        return null;
    }
}
