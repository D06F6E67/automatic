package com.lee.automatic.juejin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lee.automatic.common.utils.HttpUtils;
import com.lee.automatic.juejin.config.JueJinConfig;
import com.lee.automatic.juejin.model.BugInfoResp;
import com.lee.automatic.juejin.model.CompetitionResp;
import com.lee.automatic.juejin.model.JueJinResp;
import com.lee.automatic.juejin.service.JueJinService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * 掘金 收集bug
 *
 * @author Lee
 */
@Slf4j
@Service
public class JueJinBugFixServiceImpl implements JueJinService, Ordered {

    @Resource
    private JueJinConfig jueJinConfig;

    /**
     * 获取bug
     *
     * @return bug列表
     */
    public List<BugInfoResp> getBug() {
        try {
            Response response = HttpUtils.post(JueJinConfig.GET_BUGFIX_URL, jueJinConfig.getHeaders(), null);
            JueJinResp<List<BugInfoResp>> resp = JSONObject.parseObject(response.body().string(),
                    new TypeReference<JueJinResp<List<BugInfoResp>>>() {});

            return resp.getData();
        } catch (Exception e) {
            log.error("掘金获取bug出错", e);
            return Collections.emptyList();
        }
    }


    /**
     * 收集bug
     *
     * @param bugInfo bug信息
     */
    public Boolean collectBugfix(BugInfoResp bugInfo) {
        try {
            Response response = HttpUtils.post(JueJinConfig.COLLECT_BUGFIX_URL, jueJinConfig.getHeaders(), bugInfo);
            JueJinResp<Object> jueJinResp = JSONObject.parseObject(response.body().string(),
                    new TypeReference<JueJinResp<Object>>() {});

            if (0 == jueJinResp.getErrNo()) {
                return true;
            }

            log.error("掘金收集bug失败：{}", jueJinResp.getErrMsg());
        } catch (Exception e) {
            log.error("掘金收集bug出错", e);
        }
        return false;
    }

    /**
     * 获取活动ID
     *
     * @return 活动ID
     */
    public String getCompetition() {
        try {
            Response response = HttpUtils.post(JueJinConfig.GET_COMPETITION_URL, jueJinConfig.getHeaders(),
                    new CompetitionResp());
            JueJinResp<CompetitionResp> competitionResp = JSONObject.parseObject(response.body().string(),
                    new TypeReference<JueJinResp<CompetitionResp>>() {});

            if (0 == competitionResp.getErrNo()) {
                return competitionResp.getData().getCompetitionId();
            }

            log.error("掘金获取活动ID失败：{}", competitionResp.getErrMsg());
        } catch (Exception e) {
            log.error("掘金获取活动ID异常", e);
        }
        return "";
    }

    /**
     * 获取bug数量
     *
     * @param competitionId 活动ID
     * @return bug数量
     */
    public Integer getBugCount(String competitionId) {
        try {
            Response response = HttpUtils.post(JueJinConfig.BUG_COUNT_URL, jueJinConfig.getHeaders(),
                    new CompetitionResp(competitionId));
            JueJinResp<CompetitionResp> resp = JSONObject.parseObject(response.body().string(),
                    new TypeReference<JueJinResp<CompetitionResp>>() {});

            if (0 == resp.getErrNo()) {
                return resp.getData().getUserOwnBug();
            }

            log.error("掘金获取bug数量失败：{}", resp.getErrMsg());
        } catch (Exception e) {
            log.error("掘金获取bug数量异常", e);
        }
        return 0;
    }

    /**
     * 掘金收集bug、获取bug数量
     *
     * @return 任务结果
     */
    @Override
    public String job() {

        List<BugInfoResp> bugList = getBug();

        if (!CollectionUtils.isEmpty(bugList)) {
            bugList.forEach(this::collectBugfix);
        }

        return String.format("  收集BUG ：%s个\n  BUG数量 ：%s个\n", bugList.size(), getBugCount(getCompetition()));
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
