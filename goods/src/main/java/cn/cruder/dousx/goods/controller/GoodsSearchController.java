package cn.cruder.dousx.goods.controller;

import cn.cruder.dousx.dto.goods.param.SearchGoodsParam;
import cn.cruder.dousx.dto.goods.result.SearchGoodsResult;
import cn.cruder.logutil.annotation.AopLogger;
import cn.cruder.logutil.enums.LevelEnum;
import cn.cruder.tools.rest.CommonRestResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Random;

/**
 * @author dousx
 * @date 2022-07-02 16:49
 */
@RestController
public class GoodsSearchController {

    @Value("${server.port:8080}")
    private Integer serverPort;

    @PostMapping("/searchGoods")
    @AopLogger(describe = "查询商品", isFormat = true, appointLog = "controllerLog", level = LevelEnum.INFO)
    public CommonRestResult<SearchGoodsResult> searchGoods(@RequestBody SearchGoodsParam param) {
        Random random = new Random();
        SearchGoodsResult searchGoodsResult = SearchGoodsResult.builder().goodsId(param.getGoodsId())
                .goodsName("雪糕")
                .unit("个/袋")
                .price(BigDecimal.valueOf(random.nextInt(200)))
                .serverPort(serverPort)
                .build();
        return CommonRestResult.ok(searchGoodsResult);
    }

}
