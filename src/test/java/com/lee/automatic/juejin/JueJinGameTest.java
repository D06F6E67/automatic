package com.lee.automatic.juejin;

import com.alibaba.fastjson.JSONObject;
import com.lee.automatic.juejin.config.GameConfig;
import com.lee.automatic.juejin.service.impl.JueJinGameServiceImpl;
import com.lee.automatic.juejin.service.JueJinSeaGoldService;
import com.lee.automatic.juejin.model.game.enums.DirectionEnum;
import com.lee.automatic.juejin.model.game.model.SeaEndResp;
import com.lee.automatic.juejin.model.game.model.SeaGoldResp;
import com.lee.automatic.juejin.model.game.model.SeaPosition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 掘金 游戏 测试
 *
 * @author Lee
 */
@SpringBootTest
@ActiveProfiles("local")
public class JueJinGameTest {

    @Resource
    private GameConfig gameConfig;
    @Resource
    private JueJinGameServiceImpl jueJinGameServiceImpl;
    @Resource
    private JueJinSeaGoldService seaGoldService;


    @Test
    public void getUid() {
        jueJinGameServiceImpl.getUid();

        System.out.println(gameConfig.getUid());
    }

    @Test
    public void getToken() {
        jueJinGameServiceImpl.getToken();

        System.out.println(gameConfig.getToken());
    }

    @Test
    public void canMove() {
        List<Integer> mapData = Arrays.asList(0, 25, 13, 0, 0, 12, 10, 0, 23, 0, 13, 6, 6, 0, 0, 0, 6, 22, 0, 6, 15, 0, 0, 24);
        SeaPosition position = new SeaPosition();
        position.setX(3);
        position.setY(2);
        boolean b = seaGoldService.canMove(mapData, position, DirectionEnum.U);
        Assertions.assertTrue(b);
        Assertions.assertEquals(position.getX(), 3);
        Assertions.assertEquals(position.getY(), 1);

        b = seaGoldService.canMove(mapData, position, DirectionEnum.R);
        Assertions.assertTrue(b);
        Assertions.assertEquals(position.getX(), 4);
        Assertions.assertEquals(position.getY(), 1);

        b = seaGoldService.canMove(mapData, position, DirectionEnum.R);
        Assertions.assertFalse(b);
        Assertions.assertEquals(position.getX(), 4);
        Assertions.assertEquals(position.getY(), 1);

        b = seaGoldService.canMove(mapData, position, DirectionEnum.D);
        Assertions.assertFalse(b);
        Assertions.assertEquals(position.getX(), 4);
        Assertions.assertEquals(position.getY(), 1);
    }

    public void before() {
        jueJinGameServiceImpl.getUid();
        jueJinGameServiceImpl.getToken();
    }

    @Test
    public void seaGoldStartGame() {
        before();
        seaGoldService.login();

        seaGoldService.freshMap();
        SeaGoldResp start = seaGoldService.startGame();
        List<Integer> mapData = start.getMapData();
        for (Integer mapDatum : mapData) {
            System.out.print(mapDatum + ",");
        }
        System.out.println();

        List<DirectionEnum> route = seaGoldService.generateRoute(start);
        System.out.println(JSONObject.toJSONString(route));

        seaGoldService.roleMove(route, start.getGameId());

        SeaEndResp end = seaGoldService.endGame();
        System.out.println(end);
    }

    @Test
    public void seaGoldEndGame() {
        before();
        seaGoldService.login();

        SeaEndResp endGame = seaGoldService.endGame();
        System.out.println(endGame);
    }

    @Test
    public void seaGoldJob(){
        System.out.println(jueJinGameServiceImpl.job());
    }

}