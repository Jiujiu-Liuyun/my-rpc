package com.zhangyun.rpc.server.assembly;

import com.zhangyun.rpc.common.domain.Account;
import com.zhangyun.rpc.server.db.po.AccountPO;

public class AccountAssembly {
    public static Account assembly(AccountPO accountPO) {
        return Account.builder()
                .id(accountPO.getId())
                .username(accountPO.getUsername())
                .password(accountPO.getPassword())
                .sex(accountPO.getSex())
                .build();
    }

    public static AccountPO assemblyPO(Account account) {
        AccountPO accountPO = new AccountPO();
        accountPO.setId(account.getId());
        accountPO.setUsername(account.getUsername());
        accountPO.setPassword(account.getPassword());
        accountPO.setSex(account.getSex());
        return accountPO;
    }

}
