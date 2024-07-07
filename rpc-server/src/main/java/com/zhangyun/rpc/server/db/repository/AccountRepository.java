package com.zhangyun.rpc.server.db.repository;

import com.zhangyun.rpc.server.db.mapper.AccountPOMapper;
import com.zhangyun.rpc.server.db.po.AccountPO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class AccountRepository {
    @Resource
    private AccountPOMapper accountPOMapper;

    public AccountPO getAccountPOById(Long id) {
        return accountPOMapper.selectByPrimaryKey(id);
    }

}
