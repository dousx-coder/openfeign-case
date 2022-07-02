package cn.cruder.dousx.test;

import cn.cruder.dousx.dto.order.param.SearchOrderParam;
import cn.cruder.dousx.dto.order.result.SearchOrderResult;
import cn.cruder.dousx.feign.api.OrderFeignApi;
import cn.cruder.tools.json.JsonUtilPool;
import cn.cruder.tools.rest.CommonRestResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * todo 单元测试有问题[No tests found for given includes] 抽空解决
 * @author dousx
 * @date 2022-07-02 18:12
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class OpenFeignTests {
    @Resource(name = OrderFeignApi.BEAN_NAME)
    private OrderFeignApi orderFeignApi;

    @Test
    void testOrder() {

        for (int i = 0; i < 10; i++) {
            CommonRestResult<SearchOrderResult> result = orderFeignApi.searchOrder("1", new SearchOrderParam(UUID.randomUUID().toString()));
            log.info(JsonUtilPool.getGsonInstance().toJson(result));
        }
    }
}
