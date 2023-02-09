package com.lee.automatic.juejin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;

/**
 * 掘金任务总执行 测试
 *
 * @author Lee
 */
@SpringBootTest
@ActiveProfiles("local")
public class JueJinJobTest {

    @Resource
    private JueJinJob jueJinJob;

    @Test
    public void job() {
        System.out.println(jueJinJob.job());
    }
}
