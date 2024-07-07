package com.zhangyun.rpc.common.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Account implements Serializable {
    private Long id;

    private String username;

    private String password;

    private Integer sex;
}
