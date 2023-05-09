package com.lee.automatic.weixin.service;

import com.lee.automatic.chatGTP.AiReplySend;
import com.lee.automatic.weixin.WeiXinConfig;
import com.lee.automatic.weixin.aes.WXBizMsgCrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

/**
 * 企业微信 被动回复消息
 *
 * <pre><a href="https://developer.work.weixin.qq.com/document/10514">消息接收和回复</a></pre>
 * <pre><a href="https://developer.work.weixin.qq.com/document/path/90241#%E6%96%87%E6%9C%AC%E9%80%9A%E7%9F%A5%E5%9E%8B">被动回复消息格式</a></pre>
 *
 * @author Lee
 */
@Slf4j
@Service
public class WeiXinInteractMessageService {

    @Resource
    private WeiXinConfig wXConfig;
    @Resource
    private AiReplySend aiReplySend;

    /**
     * 微信服务器验证
     */
    public String verify(HttpServletRequest request) {

        // 企业微信加密签名，msg_signature结合了企业填写的token、请求中的timestamp、nonce参数、加密的消息体
        String msgSignature = request.getParameter("msg_signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 加密的字符串。需要解密得到消息内容明文，解密后有random、msg_len、msg、CorpID四个字段，其中msg即为消息内容明文
        String echostr = request.getParameter("echostr");

        String result = null;
        try {
            result = wXConfig.getMsgCrypt().VerifyURL(msgSignature, timestamp, nonce, echostr);
            log.info("微信服务器验证结果:" + result);
        } catch (Exception e) {
            log.error("微信服务器验证异常", e);
        }

        return result;
    }

    /**
     * 消息接收和回复
     */
    public void receiveReplyMessage(String body, HttpServletRequest request) {

        String msgSignature = request.getParameter("msg_signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");

        try {
            WXBizMsgCrypt msgCrypt = wXConfig.getMsgCrypt();

            String msg = msgCrypt.DecryptMsg(msgSignature, timestamp, nonce, body);

            Element root = string2Element(msg);
            String content = getElementByName(root, "Content");

            aiReplySend.WXSend(content, getElementByName(root, "FromUserName"));

            /*  被动回复 消息，openai接口容易引起微信接口超时，使用主动回复方式
            ReplyMsgEncryptResp fromUserName = ReplyMsgEncryptResp.builder()
                    .toUserName(getElementByName(root, "FromUserName"))
                    .fromUserName(wXConfig.getCorpId())
                    .createTime(timestamp)
                    .msgType("text")
                    .content("hello").build();

            return msgCrypt.EncryptMsg(fromUserName.xml(), timestamp, nonce);
            */
        } catch (Exception e) {
            log.error("微信消息接收和回复异常", e);
        }
    }

    /**
     * 字符串转元素节点
     *
     * @param msg 字符串
     * @return 节点
     */
    private Element string2Element(String msg) {

        Document document = null;
        try (StringReader sr = new StringReader(msg)) {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(sr);
            document = db.parse(is);
        } catch (Exception e) {
            log.error("微信被动回复消息，字符串转元素节点异常", e);
        }

        return document.getDocumentElement();
    }

    /**
     * 获取元素节点内容
     *
     * @param element 元素节点
     * @param name    元素名称
     * @return 内容
     */
    private String getElementByName(Element element, String name) {
        NodeList nodeList = element.getElementsByTagName(name);
        return nodeList.item(0).getTextContent();
    }
}