package models;

import javax.persistence.*;


@Entity
//@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
//    @Column(name = "price")
    public int price;
//    @Column(name = "description")
    public String description;
//    @Column(name = "size")
    public String size;
//    @Column(name = "imageURL")
    public String imageURL;
//    @Column(name = "name")
    public String productName;

    protected  Product(){}

    public Product(long id, int price, String description, String size, String imageURL, String productName){
        this.id = id;
        this.price = price;
        this.description = size;
        this.size = size;
        this.imageURL = imageURL;
        this.productName = productName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String name) {
        this.productName = name;
    }

    @Override
    public String toString() {
        return "Product [id= " + id + ", product name= " + productName + ", price = " + price;

    }
}


