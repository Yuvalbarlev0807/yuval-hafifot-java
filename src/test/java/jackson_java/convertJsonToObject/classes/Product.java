package jackson_java.convertJsonToObject.classes;

import java.util.List;

public class Product{
    public int id;
    public String title;
    public double price;
    public boolean inStock;

    public Specifications specifications;
    public List<Variant> variants;
    public List<String> tags;
}