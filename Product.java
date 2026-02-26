import java.util.*;

// product in inventory - id, name, category, price, qty, supplier
public class Product implements Comparable<Product>{
  private String productId;
  private String name;
  private String category;
  private double price;
  private int quantityInStock;
  private String supplier;

  // all fields required
  public Product(String productId, String name, String category, double price, int quantityInStock, String supplier){
    this.productId = productId;
    this.name = name;
    this.category = category;
    this.price = price;
    this.quantityInStock = quantityInStock;
    this.supplier = supplier;
  }

  public String getProductId(){ return productId; }
  public void setProductId(String productId){ this.productId = productId; }
  public String getName(){ return name; }
  public void setName(String name){ this.name = name; }
  public String getCategory(){ return category; }
  public void setCategory(String category){ this.category = category; }
  public double getPrice(){ return price; }
  public void setPrice(double price){ this.price = price; }
  public int getQuantityInStock(){ return quantityInStock; }
  public void setQuantityInStock(int quantityInStock){ this.quantityInStock = quantityInStock; }
  public String getSupplier(){ return supplier; }
  public void setSupplier(String supplier){ this.supplier = supplier; }

  public String toString(){
    return "Product[id=" + productId + ", name=" + name + ", category=" + category + ", price=" + price + ", qty=" + quantityInStock + ", supplier=" + supplier + "]";
  }

  // compare by productId only
  public boolean equals(Object obj){
    if (this == obj) return true;
    if (obj == null || !(obj instanceof Product)) return false;
    Product other = (Product) obj;
    return productId != null && productId.equals(other.productId);
  }

  public int hashCode(){
    return productId != null ? productId.hashCode() : 0;
  }

  public int compareTo(Product p){
    return productId.compareTo(p.productId);
  }
}
