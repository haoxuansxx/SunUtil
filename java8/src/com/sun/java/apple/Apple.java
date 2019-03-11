package apple;

/**
 * 苹果基础类
 *
 * @Author Sun
 * @date 2019-02-20
 */
public class Apple {

    /**
     * 苹果唯一标识
     */
    private Integer id;

    /**
     * 苹果类型
     */
    private String type;

    /**
     * 苹果颜色
     */
    private String color;

    /**
     * 苹果重量
     */
    private Integer weight;

    public Apple(){
    }

    public Apple(Integer weight){
        this.weight = weight;
    }

    public Apple(Integer weight, String color){
        this.color = color;
        this.weight = weight;
    }
    public Apple(String type, String color, Integer weight){
        this.type = type;
        this.color = color;
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" + "id='" + id + '\'' + ", type='" + type + '\'' + ", color='" + color + '\'' + ", weight=" + weight + '}';
    }

}
