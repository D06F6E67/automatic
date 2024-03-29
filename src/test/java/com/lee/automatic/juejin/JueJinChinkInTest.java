package com.lee.automatic.juejin;

import com.lee.automatic.juejin.service.impl.JueJinChickInServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;

/**
 * 掘金 签到抽奖 测试
 *
 * @author Lee
 */
@SpringBootTest
@ActiveProfiles("local")
public class JueJinChinkInTest {

    @Resource
    private JueJinChickInServiceImpl jueJinChickInServiceImpl;

    @Test
    public void job() {
        System.out.println(jueJinChickInServiceImpl.job());
    }

}
