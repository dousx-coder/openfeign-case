package cn.cruder.dousx.feign.json;

import com.google.gson.Gson;
import feign.RequestTemplate;
import feign.codec.Encoder;

import java.lang.reflect.Type;

/**
 * @author dousx
 * @date 2022-06-26 15:02
 */
public class GsonEncoder extends Encoder.Default {
    private final Gson gson;

    public GsonEncoder(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) {
        try {
            String toJson = gson.toJson(object, bodyType);
            template.body(toJson);
        } catch (Exception e) {
            super.encode(object, bodyType, template);
        }
    }
}