package cn.cruder.dousx.dto.goods.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author dousx
 * @date 2022-07-02 16:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchGoodsResult {
    /**
     * 商品id
     */
    private String goodsId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 规格
     */
    private String unit;

    /**
     * 具体服务
     */
    private Integer serverPort;
}
