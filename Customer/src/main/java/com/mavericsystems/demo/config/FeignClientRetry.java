package com.mavericsystems.demo.config;

import feign.RetryableException;
import feign.Retryer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeignClientRetry implements Retryer {

    @Override
    public void continueOrPropagate(RetryableException e) {
        throw e;
    }

    @Override
    public Retryer clone() {
        log.info("Attempting Feign Retry");
        return new Default(10000, 5000, 5);
    }
}
