package cn.blz.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("search")
public interface BioSearchService {
    /**
     * 根据关键词搜索商品
     * @param title 关键词
     * @return 商品id集合
     */
    @GetMapping("searchProduct/elSearch/{title}")
    List<Integer> getByTitleOrContentLike(@PathVariable("title") String title);
}
