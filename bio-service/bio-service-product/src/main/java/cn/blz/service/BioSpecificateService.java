package cn.blz.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

/**
 * @author blz
 */
@FeignClient("specificate")
public interface BioSpecificateService {
    /**
     * 根据传入的spring字符串返回规格，规格值
     *
     * @param line 字符串
     * @return 规格，规格值
     */
    @PostMapping("bioSpecificate/getSpecificateByString/{line}")
    Map<String, List<String>> getSpecificateByString(@PathVariable("line") String line);
}
