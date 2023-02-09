package com.lee.automatic.juejin;

import com.lee.automatic.juejin.service.impl.JueJinGameServiceImpl;
import com.lee.automatic.juejin.service.impl.JueJinBugFixServiceImpl;
import com.lee.automatic.juejin.service.impl.JueJinChickInServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;

/**
 * 掘金 游戏 测试
 *
 * @author Lee
 */
@SpringBootTest
@ActiveProfiles("local")
public class JueJinTaskTest {

    @Resource
    private JueJinChickInServiceImpl junJinService;
    @Resource
    private JueJinBugFixServiceImpl jueJinBugFixServiceImpl;
    @Resource
    private JueJinGameServiceImpl jueJinGameServiceImpl;


    @Test
    public void job() {
        System.out.println(
                junJinService.job()
                + jueJinBugFixServiceImpl.job()
                + jueJinGameServiceImpl.job());
    }

}