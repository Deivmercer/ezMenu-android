package com.davemercer.ezmenu.factory;

import com.davemercer.ezmenu.bean.Product;

public class ProductFactory {

    public static Product create(String[] fields) {
        Product c = new Product();
        c.setId(Integer.parseInt(fields[0]));
        c.setName(fields[1]);
        c.setPrice(Double.parseDouble(fields[2]));
        c.setCurrency(fields[3]);
        c.setImage(fields[4]);
        return c;
    }
}
