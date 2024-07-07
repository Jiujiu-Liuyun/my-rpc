package com.zhangyun.rpc.server.service.impl;

import com.zhangyun.rpc.common.domain.Account;
import com.zhangyun.rpc.server.assembly.AccountAssembly;
import com.zhangyun.rpc.server.db.repository.AccountRepository;
import com.zhangyun.rpc.common.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountRepository accountRepository;

    @Override
    public Account getAccountById(Long id) {
        return AccountAssembly.assembly(accountRepository.getAccountPOById(id));
    }

    @Override
    public Long insert(Account account) {
        return accountRepository.insert(AccountAssembly.assemblyPO(account));
    }
}
