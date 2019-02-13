package cn.blz.utils;

import org.springframework.boot.SpringBootConfiguration;

/**
 * Bio项目中所需的工具类
 *
 * @author blz
 */
@SpringBootConfiguration
public class BioUtil {
    /**
     * 消除fstjson转义String的字符串
     *
     * @param s JSON.toJsonString()
     * @return 清除后的字符串
     */
    public String dispelBackslash(String s) {
        return s.replaceAll("\\\\", "")
                .replaceAll("products\":\"", "products\": ")
                .replaceAll("\",\"sort", ",\"sort");
    }
}
