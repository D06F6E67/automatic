package com.lee.automatic.chatGPT;

import com.lee.automatic.chatGTP.impl.XeasyAiServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;

/**
 * @author Lee
 */
@SpringBootTest
@ActiveProfiles("local")
public class OpenAiServiceTest {

    @Resource
    private XeasyAiServiceImpl aiService;


    @Test
    public void ask() {
        // aiService.forEach(ai -> {
            String hello = aiService.ask("hello");
            System.out.println(hello);

        //     System.out.println(ai.getClass());
        // });
    }
}
