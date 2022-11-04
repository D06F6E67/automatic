package com.lee.automatic.juejin;

import com.alibaba.fastjson.JSONObject;
import com.lee.automatic.juejin.game.GameConfig;
import com.lee.automatic.juejin.game.JueJinGameService;
import com.lee.automatic.juejin.game.JueJinSeaGoldService;
import com.lee.automatic.juejin.game.enums.DirectionEnum;
import com.lee.automatic.juejin.game.model.SeaEndResp;
import com.lee.automatic.juejin.game.model.SeaGoldResp;
import com.lee.automatic.juejin.game.model.SeaPosition;
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
    private JueJinGameService jueJinGameService;
    @Resource
    private JueJinSeaGoldService seaGoldService;


    @Test
    public void getUid() {
        jueJinGameService.getUid();

        System.out.println(gameConfig.getUid());
    }

    @Test
    public void getToken() {
        jueJinGameService.getToken();

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
        jueJinGameService.getUid();
        jueJinGameService.getToken();
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
        System.out.println(jueJinGameService.job());
    }

}