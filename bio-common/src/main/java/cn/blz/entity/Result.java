package cn.blz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author blz
 */
@Data
@AllArgsConstructor
public class Result {
    //是否成功
    private boolean flag;
    // 返回码
    private Integer code;
    // 返回信息
    private String message;
    // 返回数据
    private Object data;
}
