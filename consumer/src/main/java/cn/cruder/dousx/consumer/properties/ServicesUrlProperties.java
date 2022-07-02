package cn.cruder.dousx.consumer.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author dousx
 * @date 2022-07-02 17:40
 */
@Data
@Configuration
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "services.url")
public class ServicesUrlProperties {
    /**
     * 订单服务地址
     */
    private String orderServer;
    /**
     * 订单服务地址
     */
    private String goodsServer;

}

