package com.lee.automatic.juejin;

import com.lee.automatic.juejin.game.JueJinGameService;
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
    private JueJinChickInService junJinService;
    @Resource
    private JueJinBugFixService jueJinBugFixService;
    @Resource
    private JueJinGameService jueJinGameService;


    @Test
    public void job() {
        System.out.println(
                junJinService.job()
                + jueJinBugFixService.job()
                + jueJinGameService.job());
    }

}