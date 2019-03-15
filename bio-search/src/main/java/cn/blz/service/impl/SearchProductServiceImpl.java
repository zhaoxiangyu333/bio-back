package cn.blz.service.impl;

import cn.blz.dao.SearchProductDao;
import cn.blz.entity.SearchProduct;
import cn.blz.service.SearchProductService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.*;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * bio_product_spu 商品表(spu)(BioProductSpu)表服务实现类
 *
 * @author blz
 * @since 2019-02-25 20:21:19
 */
@Service("searchProductService")
public class SearchProductServiceImpl implements SearchProductService {
    @Resource
    private SearchProductDao searchProductDao;

    /**
     * 根据搜索关键词查询商品
     *
     * @param title 搜索关键词
     * @return
     */
    @Override
    public List<Integer> getByTitleOrContentLike(String title) {
        if (StringUtils.isBlank(title)) {
            return null;
        }

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("name",title))
                .should(QueryBuilders.matchQuery("keywords",title)));

        Page<SearchProduct> search = this.searchProductDao.search(nativeSearchQueryBuilder.build());

        return copyIterator(search.iterator());
    }

    private static List<Integer> copyIterator(Iterator<SearchProduct> iter) {
        List<Integer> copy = new ArrayList<Integer>();
        while (iter.hasNext()) {
            copy.add(iter.next().getId());
        }
        return copy;
    }


}
