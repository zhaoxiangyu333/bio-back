package cn.blz.controller;

import cn.blz.entity.BioSpecificate;
import cn.blz.entity.BioSpecificateValue;
import cn.blz.service.BioSpecificateService;
import cn.blz.service.BioSpecificateValueService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * bio_specificate 商品规格表(BioSpecificate)表控制层
 *
 * @author blz
 * @since 2019-02-08 13:36:52
 */
@RestController
@RequestMapping("bioSpecificate")
public class BioSpecificateController {
    /**
     * 服务对象
     */
    @Resource
    private BioSpecificateService bioSpecificateService;
    @Resource
    private BioSpecificateValueService bioSpecificateValueService;

    /**
     * 根据传入的spring字符串返回规格，规格值
     *
     * @param line 字符串
     * @return 规格，规格值
     */
    @PostMapping("getSpecificateByString/{line}")
    public Map<String, List<String>> getSpecificateByString(@PathVariable("line") String line) {
        Map<String, List<String>> map = new HashMap<>();

        String[] lines = line.split("_");
        for (String s : lines) {
            BioSpecificateValue bioSpecificateValue = this.bioSpecificateValueService.getById(s);
            BioSpecificate bioSpecificate = this.bioSpecificateService.getById(bioSpecificateValue.getSpecificateId());

            if (map.containsKey(bioSpecificate.getName())) {
                map.get(bioSpecificate.getName()).add(bioSpecificateValue.getSpecificateValue());
            } else {
                List<String> list = new ArrayList<>();
                list.add(bioSpecificateValue.getSpecificateValue());
                map.put(bioSpecificate.getName(), list);
            }
        }

        return map;
    }

}
