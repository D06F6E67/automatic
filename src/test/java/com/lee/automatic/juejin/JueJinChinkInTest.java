package com.lee.automatic.juejin;

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
    private JueJinChickInService jueJinChickInService;

    @Test
    public void dipLuck() {
        System.out.println(jueJinChickInService.dipLuck());

    }

    @Test
    public void job() {
        System.out.println(jueJinChickInService.job());
    }

}
