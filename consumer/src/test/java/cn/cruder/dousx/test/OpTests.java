package cn.cruder.dousx.test;

import cn.cruder.dousx.consumer.ConsumerApp;
import cn.cruder.dousx.dto.common.Authorization;
import cn.cruder.dousx.dto.goods.param.SearchGoodsParam;
import cn.cruder.dousx.dto.goods.result.SearchGoodsResult;
import cn.cruder.dousx.dto.order.param.SearchOrderParam;
import cn.cruder.dousx.dto.order.result.SearchOrderResult;
import cn.cruder.dousx.feign.api.GoodsFeignApi;
import cn.cruder.dousx.feign.api.OrderFeignApi;
import cn.cruder.tools.json.JsonUtilPool;
import cn.cruder.tools.rest.CommonRestResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author dousx
 * @date 2022-07-02 23:28
 */
@Slf4j
@SpringBootTest(classes = ConsumerApp.class)
public class OpTests {

    @Resource(name = OrderFeignApi.BEAN_NAME)
    private OrderFeignApi orderFeignApi;
    @Autowired
    @Qualifier(value = GoodsFeignApi.BEAN_NAME)
    private GoodsFeignApi goodsFeignApi;

    @Test
    void searchOrder() {
        for (int i = 0; i < 10; i++) {
            CommonRestResult<SearchOrderResult> result = orderFeignApi.searchOrder("1", new SearchOrderParam(UUID.randomUUID().toString()));
            log.info(JsonUtilPool.getGsonInstance().toJson(result));
        }
    }


    @Test
    void searchGoods() {
        Authorization authorization = new Authorization();
        authorization.setUsername("dousx");
        authorization.setPassword("123456");
        for (int i = 0; i < 10; i++) {
            CommonRestResult<SearchGoodsResult> result = goodsFeignApi.searchGoods(authorization, new SearchGoodsParam(UUID.randomUUID().toString()));
            log.info(JsonUtilPool.getGsonInstance().toJson(result));
        }
    }

}

