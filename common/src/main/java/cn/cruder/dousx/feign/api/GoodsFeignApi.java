package cn.cruder.dousx.feign.api;

import cn.cruder.dousx.dto.common.Authorization;
import cn.cruder.dousx.dto.goods.param.SearchGoodsParam;
import cn.cruder.dousx.dto.goods.result.SearchGoodsResult;
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
public interface GoodsFeignApi {
    String BEAN_NAME = "goodsFeignApi";

    /**
     * 查询商品信息
     *
     * @param authorization {@link Authorization}
     * @param body          {@link SearchGoodsParam}
     * @return {@link SearchGoodsResult}
     */
    @RequestLine(value = "POST /searchGoods")
    @Headers({"Authorization: {token}", FeignConstants.HTTP_APPLICATION_JSON})
    @Body("{body}")
    CommonRestResult<SearchGoodsResult> searchGoods(@Param(value = "token", expander = AuthorizationExpander.class) Authorization authorization,
                                                    @Param(value = "body", expander = GsonExpander.class) SearchGoodsParam body);
}
