package map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lei
 * @version 1.0
 * @date 2021/9/5 14:23
 * @desc replaceAll() 方法将 映射集中的所有映射Value替换成给定的函数所执行的结果。
 */
public class MapNewMethod10_ReplaceAll {
    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>(4);
        map.put("lisa", 1);
        map.put("jone", 2);
        map.put("selina", 3);
        map.put("qq", null);
        // replaceAll之前:{lisa=1, jone=2, selina=3, qq=null}
        System.out.println("replaceAll之前:" + map);
        map.replaceAll((k, v) -> v == null ? -1 : v * 100);

        // replaceAll之后:{lisa=100, jone=200, selina=300, qq=-1}
        System.out.println("replaceAll之后:" + map);
    }
}
