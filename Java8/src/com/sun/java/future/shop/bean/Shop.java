package future.shop.bean;

import future.util.Discount;

import java.util.Random;

import static future.shop.service.ShopService.calculatePrice;
import static future.shop.service.ShopService.calculatePrice1;

/**
 * 商店实体类
 *
 * @Author Sun
 * @date 2019-03-15
 */
public class Shop {

    /**
     * 商店名称
     */
    public String name;

    /**
     * 商店名称
     */
    public Integer price;

    /**
     * 商店名称
     */
    public String product;

    /**
     * 接收一个商店名称的构造函数
     */
    public Shop(String product) {
        this.name = product;
        this.product = product;
    }

    /**
     * 根据商品名称返回商品价格
     *
     * @param product 商品名称
     * @return 商品价格
     */
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public String getPrice1(String product) {
        double price = calculatePrice1(product);
        Discount.Code code = Discount.Code.values()[
                new Random().nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "{" + this.name + ", " +
                "price: " + this.price + ", " +
                "product:" + this.product + "}";
    }

}
