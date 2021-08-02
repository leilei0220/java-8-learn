package stream;

/**
 * @author lei
 * @version 1.0
 * @date 2021/7/31 16:29
 * @desc
 */
public class Trader {

    private  String name;
    private  String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }
    public Trader() {
    }
}
