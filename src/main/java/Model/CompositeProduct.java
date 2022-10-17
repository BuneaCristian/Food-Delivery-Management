package Model;

import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem implements Comparable<MenuItem>{
    private List<MenuItem> products;

    public CompositeProduct(String title, double rating, int calories, int fat, int sodium, int price) {
        this.title = title;
        this.rating = rating;
        this.calories=calories;
        this.fat=fat;
        this.sodium=sodium;
        this.price=price;
        this.products = new ArrayList<>();
    }

    public CompositeProduct() {
        this.products = new ArrayList<>();
    }

    public List<MenuItem> getProducts() {
        return products;
    }

    public void calculateContents() {
        for(MenuItem mi : products) {
            this.calories += mi.calories;
            this.protein += mi.protein;
            this.fat += mi.fat;
            this.sodium += mi.sodium;
            this.price += mi.price;
        }
    }

    public String getProductNames() {
        String names= "";
        for(MenuItem mi : products) {
             names += mi.title + ", ";
        }
        return names;
    }

    @Override
    public int computePrice() {
        int price = 0;
        for(MenuItem mi : products) {
            price += mi.computePrice();
        }
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null) return false;
        if(!(obj instanceof CompositeProduct)) return false;
        CompositeProduct mi = (CompositeProduct) obj;
        if(this.title.equals(mi.title)) return true;
        return false;
    }

    @Override
    public String toString() {
        return "CompositeProduct{" +
                "products=" + products +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(MenuItem o) {
        return this.title.compareTo(o.title);
    }
}
