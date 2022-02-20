package com.mavericsystems.demo.feign;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class HystrixFallBackFactory implements FallbackFactory<AccountFeign> {

    @Override
    public AccountFeign create(Throwable cause) {
        System.out.println("Fallback"+cause.getMessage());
        return null;
    }
}
