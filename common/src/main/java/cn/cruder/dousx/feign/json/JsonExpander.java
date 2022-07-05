package cn.cruder.dousx.feign.json;

import cn.cruder.tools.json.JsonUtilPool;
import com.alibaba.fastjson2.JSON;
import feign.Param;

/**
 * @author dousx
 * @date 2022-07-02 17:57
 */
public class JsonExpander implements Param.Expander {
    @Override
    public String expand(Object value) {
        try {
            return JsonUtilPool.getGsonInstance().toJson(value);
        } catch (Exception e) {
            try {
                return JSON.toJSONString(value);
            } catch (Exception ex) {
                try {
                    return JsonUtilPool.getObjectMapperInstancePool().writeValueAsString(value);
                } catch (Exception exc) {
                    throw new RuntimeException(exc);
                }
            }
        }
    }
}