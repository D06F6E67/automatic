package com.lee.automatic.weixin;

import com.lee.automatic.weixin.service.WeiXinInteractMessageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
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
    public String verify(
            @RequestParam("msg_signature") String msgSignature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam("echostr") String echostr) {


        return interactMsgService.verify(msgSignature, timestamp, nonce, echostr);
    }

    /**
     * 消息接收和被动回复
     */
    @PostMapping
    public void receiveReplyMessage(
            @RequestParam("msg_signature") String msgSignature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestBody String body) {

        interactMsgService.receiveReplyMessage(msgSignature, timestamp, nonce, body);
    }
}
