package cn.blz.dao;

import cn.blz.entity.SearchProduct;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * SearchProductDao 商品表(spu)(SearchProductDao)表数据库访问层
 *
 * @author blz
 * @since 2019-02-25 20:21:19
 */
public interface SearchProductDao extends ElasticsearchRepository<SearchProduct, Integer> {

}
