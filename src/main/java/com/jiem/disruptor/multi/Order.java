package com.jiem.disruptor.multi;

/**
 *
 * Created by jiem on 2018/5/6 18:11.
 */
public class Order {

    private String id ;
    private String name ;
    private Double price ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
