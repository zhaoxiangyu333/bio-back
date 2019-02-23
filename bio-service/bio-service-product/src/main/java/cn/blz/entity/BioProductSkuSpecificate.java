package cn.blz.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * @author blz
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BioProductSkuSpecificate extends BioProductSku {
    private String intro;
    private Map<String, List<String>> specificates;
}
