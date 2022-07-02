package cn.cruder.dousx.consumer.config;

import cn.cruder.dousx.consumer.properties.RibbonProperties;
import cn.cruder.dousx.consumer.properties.ServicesUrlProperties;
import cn.cruder.dousx.feign.api.GoodsFeignApi;
import cn.cruder.dousx.feign.api.OrderFeignApi;
import cn.cruder.dousx.feign.json.GsonDecoder;
import cn.cruder.dousx.feign.json.GsonEncoder;
import cn.cruder.tools.json.JsonUtilPool;
import com.netflix.client.ClientFactory;
import com.netflix.client.config.ClientConfigFactory;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import feign.Feign;
import feign.Logger;
import feign.Target;
import feign.ribbon.LBClient;
import feign.ribbon.RibbonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author dousx
 * @date 2022-07-02 17:36
 */
@Configuration
public class FeginConfig {

    @Bean
    @ConditionalOnClass({RibbonProperties.class})
    public Feign feign(@Autowired RibbonProperties ribbonProperties) {
        Map<String, List<String>> ribbonInfos = ribbonProperties.getRibbonInfos();
        if (CollectionUtils.isEmpty(ribbonInfos)) {
            throw new RuntimeException("没有ribbon配置");
        }
        RibbonClient ribbonClient = RibbonClient.builder().lbClientFactory(clientName -> {
            BaseLoadBalancer loadBalancer = new BaseLoadBalancer();
            List<String> serverList = ribbonInfos.getOrDefault(clientName, new ArrayList<>());
            for (String hostPort : serverList) {
                String[] split = hostPort.split(":");
                loadBalancer.addServer(new Server(split[0], Integer.parseInt(split[1])));
            }
            IClientConfig producer = ClientFactory.getNamedConfig(clientName, ClientConfigFactory.DEFAULT::newConfig);
            return LBClient.create(loadBalancer, producer);
        }).build();
        return Feign.builder()
                .logger(new Logger.JavaLogger(this.getClass().getName()))
                .client(ribbonClient)
                .logLevel(Logger.Level.FULL)
                .encoder(new GsonEncoder(JsonUtilPool.getGsonInstance()))
                .decoder(new GsonDecoder(JsonUtilPool.getGsonInstance()))
                .build();
    }


    @Bean(name = OrderFeignApi.BEAN_NAME)
    @ConditionalOnClass({ServicesUrlProperties.class, Feign.class})
    public OrderFeignApi orderFeignApi(Feign feign, ServicesUrlProperties servicesUrlProperties) {
        return feign.newInstance(new Target.HardCodedTarget<>(OrderFeignApi.class, servicesUrlProperties.getOrderServer()));
    }

    @Bean(name = GoodsFeignApi.BEAN_NAME)
    @ConditionalOnClass({ServicesUrlProperties.class, Feign.class})
    public GoodsFeignApi goodsFeignApi(Feign feign, ServicesUrlProperties servicesUrlProperties) {
        return feign.newInstance(new Target.HardCodedTarget<>(GoodsFeignApi.class, servicesUrlProperties.getGoodsServer()));
    }

}
