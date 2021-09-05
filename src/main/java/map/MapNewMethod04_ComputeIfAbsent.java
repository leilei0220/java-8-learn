package map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lei
 * @version 1.0
 * @date 2021/9/5 13:12
 * @desc computeIfAbsent 如果指定的KEY 的键值对(k,v)不存在，或者 对应的Value 为空，则将键值对新增至映射集（Map）中
 */
public class MapNewMethod04_ComputeIfAbsent {
    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>(4);
        map.put("lisa", 1);
        map.put("jone", 2);
        map.put("selina", 3);
        map.put("ddd", null);

        //java8 之前的操作
        Integer lisaValue = map.get("adas");
        if (!map.containsKey("adas") || map.get("adas") == null) {
            map.put("adas", 2345);
        }

        // computeIfAbsent

        // 原映射集无 key为 adas的键值对，且 新key  adas 对应的 value 不为null (2345) 则会将其添加到map映射集中并返回该值
        Integer adas = map.computeIfAbsent("adas", x -> 2345);
        // 2345
        System.out.println(adas);
        // {lisa=1, jone=2, selina=3, ddd=null, adas=2345}
        System.out.println(map);

        System.out.println("--------------");

        // 原映射集无 key为 dml的键值对，且 新key  ml 对应的 value 为null,则忽略添加，并返回null
        Integer dmlValue = map.computeIfAbsent("dml", x -> null);
        // null
        System.out.println(dmlValue);
        // {lisa=1, jone=2, selina=3, ddd=null, adas=2345}
        System.out.println(map);

        // 原映射集有 Key为 ddd的键值对,但ddd对应的value 为空,如果我们再次添加有效value到ddd,则会将原键值对覆盖
        Integer dddNewValue = map.computeIfAbsent("ddd", x -> 0);
        // 0
        System.out.println(dddNewValue);
        // {lisa=1, jone=2, selina=3, ddd=0, adas=2345}
        System.out.println(map);
    }
}
