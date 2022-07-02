package cn.cruder.dousx.feign.json;

import cn.cruder.dousx.dto.common.Authorization;
import cn.cruder.tools.json.JsonUtilPool;
import feign.Param;

/**
 * @author dousx
 * @date 2022-07-02 18:06
 */
public class AuthorizationExpander implements Param.Expander {
    @Override
    public String expand(Object value) {
        if (value instanceof Authorization) {
            // 模拟到授权中心拿token
            return JsonUtilPool.getGsonInstance().toJson(value);
        }
        return String.valueOf(value);
    }
}