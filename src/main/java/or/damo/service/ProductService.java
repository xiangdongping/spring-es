package or.damo.service;

import or.damo.entity.Product;

/**
 * @author xdp
 * @date 2018/7/4
 */
public interface ProductService {

    /**
     * 更新product 库存
     * @return
     */
    Boolean updateStock(Integer productId,Integer num);

    /**
     * 扣库存 需要考虑到并发因数
     * @param productId
     * @param num
     * @return
     */
    Boolean subStock(Integer productId,Integer num);

    /**
     * 查询库存
     * @param productId
     * @return
     */
    Integer getStock(Integer productId);

    /**
     * 新建产品
     * @param product
     * @return
     */
    Boolean save(Product product);

    /**
     * 跟新product
     * @param product
     */
    void updateProduct(Product product);
}
