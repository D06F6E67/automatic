package com.lee.automatic.juejin.game;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lee.automatic.common.utils.DateUtils;
import com.lee.automatic.common.utils.HttpUtils;
import com.lee.automatic.common.utils.JwtUtils;
import com.lee.automatic.juejin.game.enums.DirectionEnum;
import com.lee.automatic.juejin.game.enums.RoleEnum;
import com.lee.automatic.juejin.game.model.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 掘金 海底掘金游戏
 *
 * @author Lee
 */
@Slf4j
@Service
public class JueJinSeaGoldService {

    @Resource
    private GameConfig config;

    /**
     * 登陆游戏
     */
    public void login() {
        try {
            HttpUtils.post(GameConfig.SEA_LOGIN_URL, config.getParams(),
                    config.getHeaders(), new SeaLoginReq(config.getUserName()));
        } catch (Exception e) {
            log.error("登陆游戏异常", e);
        }
    }

    /**
     * 获取游戏主页信息
     *
     * @return 游戏状态
     * <pre>0 未开始 1 游戏中 -1 异常</pre>
     */
    public int getHomeInfo() {
        try {
            Response response = HttpUtils.get(GameConfig.SEA_HOME_INFO_URL, config.getHeaders(), config.getParams());
            GameResp<SeaHomeResp> seaHomeResp = JSONObject.parseObject(response.body().string(),
                    new TypeReference<GameResp<SeaHomeResp>>() {
                    });

            return seaHomeResp.getData().getGameStatus();
        } catch (Exception e) {
            log.error("获取游戏主页信息异常", e);
        }
        return -1;
    }

    /**
     * 开始游戏
     *
     * @return 游戏数据
     */
    public SeaGoldResp startGame() {
        try {
            Response response = HttpUtils.post(GameConfig.SEA_START_URL, config.getParams(),
                    config.getHeaders(), new SeaStartReq(RoleEnum.CLICK.getId()));
            GameResp<SeaGoldResp> seaGoldResp = JSONObject.parseObject(response.body().string(),
                    new TypeReference<GameResp<SeaGoldResp>>() {
                    });

            return seaGoldResp.getData();
        } catch (Exception e) {
            log.error("开始游戏异常", e);
        }
        return null;
    }

    /**
     * 能否移动
     *
     * @param mapData   地图数据
     * @param position  位置
     * @param direction 方向
     * @return 结果
     */
    public boolean canMove(List<Integer> mapData, SeaPosition position, DirectionEnum direction) {
        if (Objects.isNull(mapData) || Objects.isNull(position)) {
            return false;
        }

        Integer x = position.getX();
        Integer y = position.getY();

        if (x < 0 || x > GameConfig.COL_NUMBER || y < 0) {
            return false;
        }

        int index;
        switch (direction) {
            case U:
                if (y < 1) {
                    return false;
                }
                index = --y * GameConfig.COL_NUMBER + x;
                break;
            case D:
                index = ++y * GameConfig.COL_NUMBER + x;
                break;
            case L:
                if (x == 0) {
                    return false;
                }
                index = y * GameConfig.COL_NUMBER + --x;
                break;
            case R:
                if (x == GameConfig.COL_NUMBER - 1) {
                    return false;
                }
                index = y * GameConfig.COL_NUMBER + ++x;
                break;
            default:
                index = -1;

        }

        if (GameConfig.OBSTACLES != mapData.get(index)) {
            position.setX(x);
            position.setY(y);
            return true;
        }
        return false;
    }

    /**
     * 移动
     *
     * @param stepData  步数数据
     * @param direction 方向
     * @return 剩余步数
     */
    private Integer move(SeaStepData stepData, DirectionEnum direction) {
        switch (direction) {
            case U:
                return stepData.moveUp();
            case D:
                return stepData.moveDown();
            case L:
                return stepData.moveLeft();
            case R:
                return stepData.moveRight();
            default:
                return 0;
        }
    }

    /**
     * 反向
     *
     * @param direction 方向
     * @return 反向
     */
    public DirectionEnum reverse(DirectionEnum direction) {
        switch (direction) {
            case U:
                return DirectionEnum.D;
            case D:
                return DirectionEnum.U;
            case L:
                return DirectionEnum.R;
            case R:
                return DirectionEnum.L;
            default:
                return null;
        }
    }

    /**
     * 生成路线(随机)
     *
     * @param seaGoldResp 游戏数据
     * @return 路线轨迹
     */
    public List<DirectionEnum> generateRoute(SeaGoldResp seaGoldResp) {
        // 可选方向
        LinkedList<DirectionEnum> direction = new LinkedList<DirectionEnum>() {{
            add(DirectionEnum.U);
            add(DirectionEnum.D);
            add(DirectionEnum.L);
            add(DirectionEnum.R);
        }};

        List<DirectionEnum> route = new LinkedList<>();
        Random random = new Random();
        int tag = 0;

        do {
            int index = random.nextInt(direction.size());
            DirectionEnum moveDirection = direction.get(index);
            boolean moveResults = canMove(seaGoldResp.getMapData(), seaGoldResp.getCurPos(), moveDirection);
            tag++;

            if (moveResults) {
                tag = 0;
                Integer remainingSteps = move(seaGoldResp.getBlockData(), moveDirection);
                if (remainingSteps > 0) {
                    route.add(moveDirection);
                }
                if (remainingSteps == 0) {
                    direction.remove(index);
                    route.add(moveDirection);
                }
                if (remainingSteps < 0) {
                    direction.remove(index);
                    canMove(seaGoldResp.getMapData(), seaGoldResp.getCurPos(), reverse(moveDirection));
                }
            }

        } while (direction.size() > 0 && tag < 5);
        return route;
    }

    /**
     * 角色移动
     *
     * @return 获取矿石数据
     */
    public void roleMove(List<DirectionEnum> route, String gameId) {
        try {
            Map<String, String> headers = config.getHeaders();
            headers.put("x-tt-gameid", dataSign(gameId));
            HttpUtils.post(GameConfig.SEA_MOVE_URL, config.getParams(), headers, new SeaMoveReq(route));
        } catch (Exception e) {
            log.error("角色移动异常", e);
        }
    }

    /**
     * 数据加密
     *
     * @param gameId 游戏ID
     * @return 加密结果
     */
    private String dataSign(String gameId) {

        Map<String, Object> data = new HashMap<>(2);
        data.put("gameId", gameId);
        data.put("time", DateUtils.getTimeMillis());

        return JwtUtils.createEs256JwtToken(data, config.privateKey);
    }

    /**
     * 结束游戏
     *
     * @return 获取矿石数据
     */
    public SeaEndResp endGame() {
        String string = "";
        try {
            Response response = HttpUtils.post(GameConfig.SEA_END_URL, config.getParams(),
                    config.getHeaders(), new SeaEndReq());
            string = response.body().string();
            System.out.println(string);
            GameResp<SeaEndResp> seaGoldResp = JSONObject.parseObject(string,
                    new TypeReference<GameResp<SeaEndResp>>() {
                    });

            return seaGoldResp.getData();
        } catch (Exception e) {
            log.error("结束游戏异常", e);
            log.error("结束游戏数据: {}", string);
        }
        return null;
    }

    /**
     * 刷新地图
     */
    public void freshMap() {
        try {
            HttpUtils.post(GameConfig.SEA_FRESH_URL, config.getParams(), config.getHeaders(), new Object());
        } catch (Exception e) {
            log.error("刷新地图异常", e);
        }
    }

    /**
     * 自动挖矿石
     *
     * @return 获取矿石数量
     */
    @SneakyThrows
    public String autoSeaGold() {
        login();

        int gameStatus = getHomeInfo();
        if (0 != gameStatus) {
            endGame();
        }

        // 单个地图运行次数
        int mapRunNumber = 0;
        int runNumber = 0;
        boolean runMark;
        boolean changeRoute = true;
        List<DirectionEnum> route = null;
        Integer todayDiamond = 0;

        do {
            SeaGoldResp start = startGame();

            if (changeRoute) {
                route = generateRoute(start);
            }

            roleMove(route, start.getGameId());
            SeaEndResp end = endGame();
            mapRunNumber++;
            if (Objects.isNull(end)) {
                break;
            }

            if (mapRunNumber >= GameConfig.MAP_RUN_MAX_NUMBER && changeRoute) {
                freshMap();
            }
            if (end.getRealDiamond() > GameConfig.ROUND_MIN_NUMBER) {
                changeRoute = false;
            }
            runMark = end.getTodayDiamond() < end.getTodayLimitDiamond();
            todayDiamond = end.getTodayDiamond();

            Thread.sleep(1000);
        } while (++runNumber < GameConfig.RUN_MAX_NUMBER && runMark);

        return String.format("  海底掘金：\n    今日获得：%s个\n    运行次数：%s次\n",
                todayDiamond, runNumber);
    }

}