package com.lee.automatic.telecom;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;

/**
 * 电信 云宠物 测试
 *
 * @author Lee
 */
@SpringBootTest
@ActiveProfiles("local")
public class TelecomServiceTest {

    @Resource
    private TelecomService service;

    @Test
    public void chickIn() {
        System.out.println(service.chickIn());
    }

    @Test
    public void browsePageTask() {
        System.out.println(service.browsePageTask());
    }

    @Test
    public void getCatFoodAmount() {
        System.out.println(service.getCatFoodAmount());
    }

    @Test
    public void getLastTimeFeedCatDate() {
        System.out.println(service.getLastTimeFeedCatDate());
    }

    @Test
    public void feedCat() {
        service.feedCat();
    }

    @Test
    public void job() {
        System.out.println(service.job());
    }
}