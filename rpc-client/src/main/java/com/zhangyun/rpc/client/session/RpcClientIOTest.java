package com.zhangyun.rpc.client.session;

import com.zhangyun.rpc.common.domain.Account;
import com.zhangyun.rpc.common.domain.TestD;
import com.zhangyun.rpc.common.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Service
@Slf4j
public class RpcClientIOTest implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        new Thread(() -> {
            try (Socket socket = new Socket("127.0.0.1", 9999)) {
                try(ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
                    TestD insert = TestD.builder().method("11").build();
                    oos.writeObject(insert);
                    oos.flush();
                    String str = (String) ois.readObject();
                    log.info("测试: resp:{}", str);
                } catch (IOException | ClassNotFoundException e){
                    log.error("从IO中读取数据错误, e: {}", ExceptionUtils.getStackTrace(e));
                }
            } catch (IOException e) {
                log.error("服务器启动失败, e: {}", ExceptionUtils.getStackTrace(e));
            }
        }).start();
    }
}
