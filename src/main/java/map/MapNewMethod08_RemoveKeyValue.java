package map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lei
 * @version 1.0
 * @date 2021/9/5 14:08
 * @desc remove(key,value)  当指定的 KEY 与VALUE 在映射集中存在，且为绑定的键值对关系时，才移除
 */
public class MapNewMethod08_RemoveKeyValue {
    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>(4);
        map.put("lisa", 1);

        // lisa-2 无映射关系，因为不会移除
        map.remove("lisa", 2);
        // {lisa=1}
        System.out.println(map);
    }
}
