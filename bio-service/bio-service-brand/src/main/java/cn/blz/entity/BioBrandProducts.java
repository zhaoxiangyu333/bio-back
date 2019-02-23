package cn.blz.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author blz
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BioBrandProducts extends BioBrand{
    private String products;
}
