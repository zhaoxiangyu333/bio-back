package cn.blz.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author blz
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BioUserToken extends BioUser{
    private String token;
}
