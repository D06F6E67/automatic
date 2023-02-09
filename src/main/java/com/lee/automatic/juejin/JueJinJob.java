package com.lee.automatic.juejin;

import com.lee.automatic.juejin.service.JueJinService;
import org.springframework.core.OrderComparator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 掘金任务总执行
 *
 * @author Lee
 */
@Service
public class JueJinJob {

    @Resource
    private List<JueJinService> junJinServiceList;

    public String job() {
        junJinServiceList.sort(OrderComparator.INSTANCE);

        StringBuilder result = new StringBuilder();

        junJinServiceList.forEach(service -> result.append(service.job()));

        return result.toString();
    }
}
