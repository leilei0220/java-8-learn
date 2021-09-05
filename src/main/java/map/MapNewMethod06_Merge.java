package map;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lei
 * @version 1.0
 * @date 2021/9/5 13:37
 * @desc merge 为合并的意思
 * merge() 方法会先判断指定的 key 是否存在，如果不存在，则添加键值对到 hashMap 中，如果存在则根据逻辑重新映射该KEY键值对（如何合并相同KEY的新旧VALUE）
 */
public class MapNewMethod06_Merge {
    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>(4);
        map.put("lisa", 1);
        map.put("jone", 2);
        map.put("selina", 3);

        Integer lisaNewValue = map.merge("lisa", 100, Integer::sum);
        System.out.println(lisaNewValue);
        System.out.println(map);

        // stream toMap merge
        User adas = new User("adas", 23);
        User adas2 = new User("adas", 28);
        User mlg = new User("mlg", 34);
        ArrayList<User> list = new ArrayList<>();
        list.add(adas);
        list.add(adas2);
        list.add(mlg);
        Map<String, Integer> userMap = list.stream()
                .filter(x->x.name!=null && x.value!=null)
                // 这里采用新值覆盖旧值
                .collect(Collectors.toMap(User::getName, User::getValue,(oldValue,newValue)->newValue));
        // {adas=28, mlg=34}
        System.out.println(userMap);
    }

    public static class User{
        private String name;
        private Integer value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public User(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        public User() {
        }
    }
}
