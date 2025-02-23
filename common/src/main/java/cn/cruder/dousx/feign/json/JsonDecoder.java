package cn.cruder.dousx.feign.json;

import cn.cruder.tools.json.JsonUtilPool;
import cn.hutool.log.Log;
import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.databind.JavaType;
import com.google.common.io.ByteStreams;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * @author dousx
 * @date 2022-06-26 14:44
 */
public class JsonDecoder extends Decoder.Default {
    private static final Log log = Log.get();

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        InputStream inputStream = response.body().asInputStream();
        String responseStr = new String(ByteStreams.toByteArray(inputStream));
        try {
            if (log.isDebugEnabled()) {
                log.debug("尝试Gson解析...");
            }
            Object result = JsonUtilPool.getGsonInstance().fromJson(responseStr, type);
            if (log.isDebugEnabled()) {
                log.debug("Gson解析成功...");
            }
            return result;
        } catch (Exception e) {
            try {
                if (log.isDebugEnabled()) {
                    log.debug("尝试ObjectMapper解析. ...");
                }

                Object result = JsonUtilPool.getObjectMapperInstance().readValue(responseStr, (JavaType) type);
                if (log.isDebugEnabled()) {
                    log.debug("ObjectMapper解析成功...");
                }
                return result;
            } catch (Exception ex) {
                try {
                    if (log.isDebugEnabled()) {
                        log.debug("尝试fastjson解析...");
                    }
                    Object result = JSON.parseObject(responseStr, type);
                    if (log.isDebugEnabled()) {
                        log.debug("fastjson解析成功...");
                    }
                    return result;
                } catch (Exception exc) {
                    log.warn("JSON 反序列化失败[直接返回responseStr] - type: {} , responseStr: {} ", type, responseStr);
                    return responseStr;
                }
            }
        }
    }
}
