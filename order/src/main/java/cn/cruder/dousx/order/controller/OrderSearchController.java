package cn.cruder.dousx.order.controller;

import cn.cruder.dousx.dto.order.param.SearchOrderParam;
import cn.cruder.dousx.dto.order.result.SearchOrderResult;
import cn.cruder.logutil.annotation.AopLogger;
import cn.cruder.logutil.enums.LevelEnum;
import cn.cruder.tools.rest.CommonRestResult;
import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Random;

/**
 * @author dousx
 * @date 2022-07-02 16:55
 */
@RestController
public class OrderSearchController {

    @Value("${server.port:8080}")
    private Integer serverPort;


    @PostMapping("/searchOrder")
    @AopLogger(describe = "查询订单", isFormat = true, appointLog = "controllerLog", level = LevelEnum.INFO)
    public CommonRestResult<SearchOrderResult> searchOrder(@RequestBody SearchOrderParam param) {
        Random random = new Random();
        SearchOrderResult result = SearchOrderResult.builder()
                .orderId(param.getOrderId())
                .orderTime(DateUtil.yesterday())
                .totalAmount(BigDecimal.valueOf(random.nextInt(1000)))
                .serverPort(serverPort)
                .build();
        return CommonRestResult.ok(result);
    }

}
