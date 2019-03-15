package cn.blz;

import cn.blz.dao.SearchProductDao;
import cn.blz.entity.SearchProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author blz
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BioSearchApplication.class)
public class ElTest {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private SearchProductDao searchProductDao;

    @Test
    public void testCreate() {
        // 创建索引，会根据Item类的@Document注解信息来创建
        elasticsearchTemplate.createIndex(SearchProduct.class);
        // 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        elasticsearchTemplate.putMapping(SearchProduct.class);
    }

    @Test
    public void deleteIndex() {
        elasticsearchTemplate.deleteIndex("bio");
    }


    @Test
    public void index(){
        SearchProduct searchProduct = new SearchProduct();
        searchProduct.setId(1);
        searchProduct.setName("iPhoneX");
        searchProduct.setCategoryId(1);
        searchProduct.setBrandId(1);
        searchProduct.setIntro("苹果第十代手机");
        searchProduct.setKeywords("苹果手机");

//        SearchProduct searchProduct = new SearchProduct();
//        searchProduct.setId(2);
//        searchProduct.setName("三星S10");
//        searchProduct.setCategoryId(1);
//        searchProduct.setBrandId(2);
//        searchProduct.setIntro("三星手机第十代");
//        searchProduct.setKeywords("三星手机");

        searchProductDao.save(searchProduct);

    }
}
