package com.lee.automatic.chatGPT;

import com.lee.automatic.chatGTP.OpenAiService;
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
    private OpenAiService openAiService;


    @Test
    public void ask() {
        System.out.println(openAiService.ask("hello"));
    }
}
