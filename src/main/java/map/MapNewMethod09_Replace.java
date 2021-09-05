package map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lei
 * @version 1.0
 * @date 2021/9/5 14:15
 * @desc 方法替换 hMap 中是指定的 key 对应的 value。
 */
public class MapNewMethod09_Replace {
    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>(4);
        map.put("lisa", 1);
        map.put("hobo", null);

        // key 存在 且原 value 不为null
        Integer lisaOldValue = map.replace("lisa", 333);
        // 返回原来旧值 1
        System.out.println(lisaOldValue);
        // lisa的值已被替换  {lisa=333, hobo=null}
        System.out.println(map);

        System.out.println("___________________");

        // key 存在 且原 value 为null
        Integer hoboOldValue = map.replace("hobo", -1);
        // 返回旧值 null
        System.out.println(hoboOldValue);
        // hobo的值已被替换 {lisa=333, hobo=-1}
        System.out.println(map);
        System.out.println("___________________");

        // key 不存在
        Integer adasOldValue = map.replace("adas", -1);
        // null
        System.out.println(adasOldValue);
        // 未变 {lisa=333, hobo=-1}
        System.out.println(map);
    }
}
