package cn.cruder.dousx.order;

import cn.cruder.logutil.annotation.EnableAopLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dousx
 * @date 2022-07-02 16:47
 */
@SpringBootApplication
@EnableAopLog
public class OrderApp {
    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class, args);
    }
}
