package com.zhangyun.rpc.common.service;

import com.zhangyun.rpc.common.domain.Account;

public interface AccountService {
    Account getAccountById(Long id);

    Long insert(Account account);
}
