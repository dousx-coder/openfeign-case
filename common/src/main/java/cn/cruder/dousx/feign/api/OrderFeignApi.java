package cn.cruder.dousx.feign.api;

import cn.cruder.dousx.dto.common.Authorization;
import cn.cruder.dousx.dto.order.param.SearchOrderParam;
import cn.cruder.dousx.dto.order.result.SearchOrderResult;
import cn.cruder.dousx.feign.constants.FeignConstants;
import cn.cruder.dousx.feign.json.AuthorizationExpander;
import cn.cruder.dousx.feign.json.GsonExpander;
import cn.cruder.tools.rest.CommonRestResult;
import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * @author dousx
 * @date 2022-07-02 17:47
 */
public interface OrderFeignApi {
    String BEAN_NAME = "orderFeignApi";

    /**
     * 查询商品信息
     *
     * @param authorization 虽然指定了{@link Authorization},但是类型校验不通过,会直接返回字符串
     * @param body          {@link SearchOrderParam}
     * @return {@link SearchOrderResult}
     */
    @RequestLine(value = "POST /searchOrder")
    @Headers({"Authorization: {token}", FeignConstants.HTTP_APPLICATION_JSON})
    @Body("{body}")
    CommonRestResult<SearchOrderResult> searchOrder(@Param(value = "token", expander = AuthorizationExpander.class) String authorization,
                                                    @Param(value = "body", expander = GsonExpander.class) SearchOrderParam body);
}
