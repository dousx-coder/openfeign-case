package cn.cruder.dousx.dto.goods.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dousx
 * @date 2022-07-02 16:50
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchGoodsParam {
    /**
     * 商品id
     */
    private String goodsId;
}
