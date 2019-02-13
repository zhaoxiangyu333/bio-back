package cn.blz.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author blz
 */
@Data
public class BioProductSkuSpecificate extends BioProductSku {
    private String intro;
    private Map<String, List<String>> specificates;
}
