package cn.cruder.dousx.dto.order.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dousx
 * @date 2022-07-02 17:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchOrderParam {
    /**
     * 订单id
     */
    private String orderId;

}
