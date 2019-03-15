package cn.blz.service;


import cn.blz.entity.SearchProduct;

import java.util.List;

/**
 * SearchProductService 商品表(spu)(SearchProductService)表服务接口
 *
 * @author blz
 * @since 2019-02-25 20:21:19
 */
public interface SearchProductService {

    List<Integer> getByTitleOrContentLike(String title);
}
