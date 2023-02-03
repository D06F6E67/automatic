package com.lee.automatic.pudn;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * Pudn 签到、金币数量 测试
 *
 * @author Lee
 */
@SpringBootTest
@ActiveProfiles("local")
public class PudnChinkInTest {

    @Resource
    private PudnChickInService pudnChickInService;

    @Test
    public void chickIn() {
        String chickIn = pudnChickInService.chickIn();

        Assert.isTrue(!"签到失败".equals(chickIn), "签到失败");
        System.out.println(chickIn);

    }

    @Test
    public void goldInfo() {
        String goldInfo = pudnChickInService.goldInfo();

        Assert.isTrue(!"-1".equals(goldInfo), "获取金币数量异常");
        System.out.println(goldInfo);
    }

    @Test
    public void job() {
        System.out.println(pudnChickInService.job());
    }

}
