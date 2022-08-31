package com.lee.automatic.youdao;

import com.alibaba.fastjson.JSONObject;
import com.lee.automatic.common.utils.HttpUtils;
import com.lee.automatic.youdao.model.CheckinResp;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 有道笔记 业务处理
 *
 * @author Lee
 */
@Slf4j
@Service
public class YouDaoService {

    @Resource
    private YouDaoConfig youDaoConfig;

    /**
     * 签到
     *
     * @param headers 请求头
     * @return 签到结果
     */
    public String checkIn(Map<String, String> headers) {
        try {
            Response response = HttpUtils.post(YouDaoConfig.CHECK_IN_URL, headers, null);
            CheckinResp checkinResp = JSONObject.parseObject(response.body().string(), CheckinResp.class);

            return String.format("%sMB 总获得%sMB",
                    // Total / 1024 / 1024 --> Total >> 20
                    checkinResp.getSpace() >> 20,
                    checkinResp.getTotal() >> 20);
        } catch (Exception e) {
            log.error("有道笔记签到失败", e);
            return "签到失败";
        }
    }

    /**
     * 有道签到
     *
     * @return 任务结果
     */
    public String job() {
        Map<String, String> headers = new HashMap<>(1);
        headers.put("cookie", youDaoConfig.getCookie());

        return String.format("有道笔记签到结果：%s\n",
                checkIn(headers));
    }
}
