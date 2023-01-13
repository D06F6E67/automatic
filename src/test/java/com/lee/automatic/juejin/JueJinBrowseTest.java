package com.lee.automatic.juejin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * 掘金 掘金 浏览文章、沸点 测试
 *
 * @author Lee
 */
@SpringBootTest
@ActiveProfiles("local")
public class JueJinBrowseTest {

    @Resource
    private JueJinBrowseService jueJinBrowseService;

    @Test
    public void articleList() {
        int num = jueJinBrowseService.articleList();
        Assert.isTrue(num >= 0, "获取文章失败");
    }

    @Test
    public void boilingPointList() {
        int num = jueJinBrowseService.boilingPointList();
        Assert.isTrue(num >= 0, "获取沸点失败");
    }

    @Test
    public void job() {
        jueJinBrowseService.job();
    }

}
