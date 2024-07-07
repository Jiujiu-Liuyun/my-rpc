package com.zhangyun.rpc.common.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Method;

@Data
@Builder
public class TestD implements Serializable {
    private String method;
}
