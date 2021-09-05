package map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lei
 * @version 1.0
 * @date 2021/9/5 13:21
 * @desc 如果指定KEY的映射存在且Value非null，则将指定KEY的原Value,替换为现在的Value，保存存在映射集中,如果现Value为null，会将指定KEY的映射删除
 */
public class MapNewMethod05_ComputeIfPresent {
    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>(4);
        map.put("lisa", 1);
        map.put("jone", 2);
        map.put("selina", 3);
        map.put("ddd", null);
        // java8 之前的写法
        if (map.get("selina") != null) {
            Integer oldValue = map.get("selina");
            Integer newValue = 1232312;
            if (newValue != null) {
                map.put("selina", newValue);
            } else {
                map.remove("selina");
            }
        }

        // 原映射集中存在对应KEY  ,原Value不为null,现value不为null,则覆盖原来 键值对映射
        Integer selinaNewValue = map.computeIfPresent("selina", (k, v) -> 888999);
        // 888999
        System.out.println(selinaNewValue);
        // {lisa=1, jone=2, selina=888999, ddd=null}
        System.out.println(map);

        System.out.println("-------------------");
        // 原映射集中存在对应KEY  ,原Value不为null,现value为null,则移除对应键值对
        Integer selinaNewValue2 = map.computeIfPresent("selina", (k, v) -> null);
        // null
        System.out.println(selinaNewValue2);
        // 移除了selina
        System.out.println(map);

        System.out.println("-------------------");
        // 原映射集中存在对应KEY  ,原Value为null,现value 不为null,  对原映射集不做任何操作
        Integer dddNewValue = map.computeIfPresent("ddd", (k, v) -> 2222222);
        System.out.println(dddNewValue);
        System.out.println(map);

        System.out.println("-------------------");
        // 原映射集中不存在对应KEY  ,现value 不为null  对原映射集不做任何操作
        Integer mqNewValue = map.computeIfPresent("mq", (k, v) -> -1);
        System.out.println(mqNewValue);
        System.out.println(map);
    }
}
