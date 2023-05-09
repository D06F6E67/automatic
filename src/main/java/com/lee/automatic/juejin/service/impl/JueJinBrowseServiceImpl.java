package com.lee.automatic.juejin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lee.automatic.common.utils.HttpUtils;
import com.lee.automatic.juejin.config.JueJinConfig;
import com.lee.automatic.juejin.model.BoilingPointListReq;
import com.lee.automatic.juejin.model.JueJinResp;
import com.lee.automatic.juejin.service.JueJinService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 掘金 浏览文章、沸点
 *
 * @author Lee
 */
@Slf4j
@Service
public class JueJinBrowseServiceImpl implements JueJinService, Ordered {

    @Resource
    private JueJinConfig jueJinConfig;

    /**
     * 文章列表
     *
     * @return 文章数量 -1异常
     */
    public int articleList() {
        try {
            Response response = HttpUtils.post(JueJinConfig.ARTICLE_LIST, jueJinConfig.getHeaders(), null);

            JueJinResp<List<Map<String, Object>>> resp = JSONObject.parseObject(response.body().string(),
                    new TypeReference<JueJinResp<List<Map<String, Object>>>>() {});

            return resp.getData().size();
        } catch (Exception e) {
            log.error("文章列表异常", e);
        }
        return -1;
    }

    /**
     * 沸点列表
     *
     * @return 沸点数量 -1异常
     */
    public int boilingPointList() {
        try {
            Response response = HttpUtils.post(JueJinConfig.BOILING_POINT_LIST,
                    jueJinConfig.getHeaders(), new BoilingPointListReq());

            JueJinResp<List<Map<String, Object>>> resp = JSONObject.parseObject(response.body().string(),
                    new TypeReference<JueJinResp<List<Map<String, Object>>>>() {});

            return resp.getData().size();
        } catch (Exception e) {
            log.error("沸点列表异常", e);
        }
        return -1;
    }


    /**
     * 掘金文章、沸点
     * 增加活跃避免系统检测
     */
    @Override
    public String job() {
        return String.format("  文章数量：%s个\n  沸点数量：%s个\n", articleList(), boilingPointList());
    }

    @Override
    public int getOrder() {
        return 3;
    }
}