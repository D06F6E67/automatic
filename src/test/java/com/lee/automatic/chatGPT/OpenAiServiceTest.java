package com.lee.automatic.chatGPT;

import com.lee.automatic.chatGTP.impl.ChatosAiServiceImpl;
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
    private ChatosAiServiceImpl chatosAiServiceImpl;


    @Test
    public void ask() {
        // openAiService.forEach(ai -> {
            String hello = chatosAiServiceImpl.ask("hello");
            System.out.println(hello);
        // });
    }
}
