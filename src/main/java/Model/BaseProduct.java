package Model;

public class BaseProduct extends MenuItem implements Comparable<MenuItem>{
    public BaseProduct(String title, double rating, int calories, int fat, int sodium, int price) {
        this.title = title;
        this.rating = rating;
        this.calories=calories;
        this.fat=fat;
        this.sodium=sodium;
        this.price=price;
    }

    public BaseProduct(String title, String rating, String calories, String fat, String sodium, String price) {
        this.title = title;
        this.rating = Double.parseDouble(rating);
        this.calories=Integer.parseInt(calories);
        this.fat=Integer.parseInt(fat);
        this.sodium=Integer.parseInt(sodium);
        this.price=Integer.parseInt(price);
    }

    public BaseProduct() {
    }

    @Override
    public int computePrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null) return false;
        if(!(obj instanceof BaseProduct)) return false;
        BaseProduct mi = (BaseProduct)obj;
        if(this.title.equals(mi.title)) return true;
        return false;
    }

    @Override
    public String toString() {
        return "BaseProduct{" +
                "title='" + title + '\'' +
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
