package cn.cruder.dousx.goods;

import cn.cruder.logutil.annotation.EnableAopLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dousx
 * @date 2022-07-02 16:45
 */
@SpringBootApplication
@EnableAopLog
public class GoodsApp {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApp.class, args);
    }
}
