package com.lee.automatic.dingtalk;

import com.lee.automatic.dingtalk.model.ReceiveMessageResp;
import com.lee.automatic.dingtalk.service.DingTalkInteractMessageService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 钉钉消息接收
 *
 * @author Lee
 */
@RestController
@RequestMapping("/dingtalk")
public class DingTalkController {

    @Resource
    private DingTalkInteractMessageService interactMessageService;

    /**
     * 接收消息
     *
     * @param  receiveMessage 消息信息
     */
    @RequestMapping
    public void receiveMessage(@RequestBody ReceiveMessageResp receiveMessage, HttpServletRequest request) {

        interactMessageService.receiveMessage(receiveMessage, request);
    }
}