package cn.cruder.dousx.feign.json;

import cn.hutool.log.Log;
import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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
public class GsonDecoder extends Decoder.Default {
    private final Gson gson;
    private static final Log log = Log.get();


    public GsonDecoder(Gson gson) {
        this.gson = gson;
    }


    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        InputStream inputStream = response.body().asInputStream();
        String responseStr = new String(ByteStreams.toByteArray(inputStream));
        try {
            return gson.fromJson(responseStr, type);
        } catch (JsonSyntaxException e) {
            log.warn("JSON 反序列化失败[直接返回responseStr] - type: {} , responseStr: {} ", type, responseStr);
            return responseStr;
        }
    }
}
