package com.davemercer.ezmenu.bean;

import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Product {

    private Integer id;
    private Double price;
    private String name, image, currency;

    public Product() {
    }

    public Product(Integer id, Double price, String name, String image, String currency) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.image = image;
        this.currency = currency;
    }

    public Integer getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getCurrency() {
        return currency;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public static List<Product> productList(String xml) {
        Product product = null;
        List<Product> pl = new ArrayList<Product>();
        Reader reader = new StringReader(xml);
        XMLInputFactory f = XMLInputFactory.newInstance();
        XMLStreamReader sr = null;
        try {
            sr = f.createXMLStreamReader(reader);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        XmlMapper mapper = new XmlMapper();
        try{
            int cont = 0;
            sr.next(); // to point to <root>
            //sr.getAttributeValue(0);
            // TODO: aggiungere nell'XML un attributo che dica quanti elementi sono presenti.
            for (int i = 0; i < 3 && sr.hasNext(); i++){
                sr.next(); // to point to root-element under root
                product = mapper.readValue(sr, Product.class);
                pl.add(product);
            }
            sr.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pl;
    }
}
