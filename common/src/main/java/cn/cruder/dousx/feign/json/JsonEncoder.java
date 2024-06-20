package cn.cruder.dousx.feign.json;

import cn.cruder.tools.json.JsonUtilPool;
import cn.hutool.log.Log;
import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import feign.RequestTemplate;
import feign.codec.Encoder;

import java.lang.reflect.Type;

/**
 * @author dousx
 * @date 2022-06-26 15:02
 */
public class JsonEncoder extends Encoder.Default {
    private static final Log log = Log.get();
    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) {
        try {
            template.body(JsonUtilPool.getGsonInstance().toJson(object, bodyType));
        } catch (Exception e) {
            try {
                template.body(JSON.toJSONString(object));
            } catch (Exception ex) {
                try {
                    template.body(JsonUtilPool.getObjectMapperInstance().writeValueAsString(object));
                } catch (JsonProcessingException exc) {
                    super.encode(object, bodyType, template);
                }
            }
        }
    }


}
