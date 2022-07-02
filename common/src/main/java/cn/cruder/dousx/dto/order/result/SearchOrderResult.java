package cn.cruder.dousx.dto.order.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dousx
 * @date 2022-07-02 17:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchOrderResult {
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 下单时间
     */
    private Date orderTime;
    /**
     * 总金额
     */
    private BigDecimal totalAmount;
    /**
     * 具体服务
     */
    private Integer serverPort;

}
