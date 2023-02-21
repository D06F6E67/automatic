package com.lee.automatic.weixin;

import com.lee.automatic.weixin.service.WeiXinInteractMessageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 微信机器人消息接收
 *
 * @author Lee
 */
@RestController
@RequestMapping("/weiXin")
public class WeiXinController {

    @Resource
    private WeiXinInteractMessageService interactMsgService;

    /**
     * 配置服务器时微信验证
     */
    @GetMapping
    public String verify(HttpServletRequest request) {

        return interactMsgService.verify(request);
    }

    /**
     * 消息接收和被动回复
     */
    @PostMapping
    public void receiveReplyMessage(@RequestBody String body, HttpServletRequest request) {

        interactMsgService.receiveReplyMessage(body, request);
    }
}
