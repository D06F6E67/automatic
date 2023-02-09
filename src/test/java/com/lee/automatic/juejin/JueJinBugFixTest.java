package com.lee.automatic.juejin;

import com.alibaba.fastjson.JSONObject;
import com.lee.automatic.juejin.model.BugInfoResp;
import com.lee.automatic.juejin.service.impl.JueJinBugFixServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;
import java.util.List;

/**
 * 掘金 收集bug 测试
 *
 * @author Lee
 */
@SpringBootTest
@ActiveProfiles("local")
public class JueJinBugFixTest {

    @Resource
    private JueJinBugFixServiceImpl jueJinBugFixServiceImpl;

    @Test
    public void getBug() {

        List<BugInfoResp> bug = jueJinBugFixServiceImpl.getBug();

        bug.forEach(System.out::println);

        BugInfoResp bugInfoResp = bug.get(0);

        System.out.println(JSONObject.toJSONString(bugInfoResp));

        Boolean aBoolean = jueJinBugFixServiceImpl.collectBugfix(bug.get(0));
        System.out.println(aBoolean);

    }

    @Test
    public void getCompetition() {
        String competition = jueJinBugFixServiceImpl.getCompetition();

        Integer bugCount = jueJinBugFixServiceImpl.getBugCount(competition);

        System.out.println(competition + "->" + bugCount);
    }

    @Test
    public void job() {
        System.out.println(jueJinBugFixServiceImpl.job());
    }

}
