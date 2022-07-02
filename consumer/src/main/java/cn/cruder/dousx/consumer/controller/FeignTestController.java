package cn.cruder.dousx.consumer.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author dousx
 * @date 2022-07-02 18:49
 */
@Slf4j
@RestController
public class FeignTestController {
    @Resource(name = OrderFeignApi.BEAN_NAME)
    private OrderFeignApi orderFeignApi;
    @Resource(name = GoodsFeignApi.BEAN_NAME)
    private GoodsFeignApi goodsFeignApi;

    @GetMapping("/searchOrder")
    public CommonRestResult<String> searchOrder() {
        for (int i = 0; i < 10; i++) {
            CommonRestResult<SearchOrderResult> result = orderFeignApi.searchOrder("1", new SearchOrderParam(UUID.randomUUID().toString()));
            log.info(JsonUtilPool.getGsonInstance().toJson(result));
        }
        return CommonRestResult.ok("测试成功");
    }


    @GetMapping("/searchGoods")
    public CommonRestResult<String> searchGoods() {
        Authorization authorization = new Authorization();
        authorization.setUsername("dousx");
        authorization.setPassword("123456");
        for (int i = 0; i < 10; i++) {

            CommonRestResult<SearchGoodsResult> result = goodsFeignApi.searchGoods(authorization, new SearchGoodsParam(UUID.randomUUID().toString()));
            log.info(JsonUtilPool.getGsonInstance().toJson(result));
        }
        return CommonRestResult.ok("测试成功");
    }


}
