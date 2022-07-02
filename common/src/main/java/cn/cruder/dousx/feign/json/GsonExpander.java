package cn.cruder.dousx.feign.json;

import cn.cruder.tools.json.JsonUtilPool;
import feign.Param;

/**
 * @author dousx
 * @date 2022-07-02 17:57
 */
public class GsonExpander implements Param.Expander {
    @Override
    public String expand(Object value) {
        return JsonUtilPool.getGsonInstance().toJson(value);
    }
}