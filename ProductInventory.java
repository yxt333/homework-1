import java.util.*;

// Vector of products - add, remove, find, filter, capacity
public class ProductInventory{
  private Vector<Product> products; //stores products 

  public ProductInventory(){
    products = new Vector<>();
  }

  // add if not duplicate by id; skip null
  public void addProduct(Product product){
    if (product == null || product.getProductId() == null) return;
    if (findProduct(product.getProductId()) != null) return;
    products.add(product);
  }

  // remove by id, true if found
  public boolean removeProduct(String productId){
    Product p = findProduct(productId);
    if (p == null) return false;
    products.remove(p);
    return true;
  }

  // find by id, null if not found
  public Product findProduct(String productId){
    if (productId == null) return null;
    for (int i = 0; i < products.size(); i = i + 1){
      if (productId.equals(products.get(i).getProductId()))
        return products.get(i);
    }
    return null;
  }

  // products in category; empty if category null
  public Vector<Product> getProductsByCategory(String category){
    Vector<Product> out = new Vector<>();
    if (category == null) return out;
    for (int i = 0; i < products.size(); i = i + 1){
      if (category.equals(products.get(i).getCategory()))
        out.add(products.get(i));
    }
    return out;
  }

  public Vector<Product> getLowStockProducts(int threshold){
    Vector<Product> out = new Vector<>();
    for (int i = 0; i < products.size(); i = i + 1){
      if (products.get(i).getQuantityInStock() < threshold)
        out.add(products.get(i));
    }
    return out;
  }

  public double getTotalInventoryValue(){
    double tot = 0;
    for (int i = 0; i < products.size(); i = i + 1){
      Product p = products.get(i);
      tot = tot + (p.getPrice() * p.getQuantityInStock());
    }
    return tot;
  }

  // update stock by quantityChange; clamp to 0 min
  public void updateStock(String productId, int quantityChange){
    Product p = findProduct(productId);
    if (p == null) return;
    int newQty = p.getQuantityInStock() + quantityChange;
    if (newQty < 0) newQty = 0; //clamp to 0 minimum
    p.setQuantityInStock(newQty);
  }

  public void printAllProducts(){
    System.out.println("\nAll Products\n------");
    for (int i = 0; i < products.size(); i = i + 1)
      System.out.println((i+1) + ". " + products.get(i));
  }

  public int getTotalProducts(){
    return products.size();
  }

  public void printCapacityInfo(){
    System.out.println("size: " + products.size() + ", capacity: " + products.capacity());
  }

  public void optimizeCapacity(){
    products.trimToSize();
  }

  public void ensureCapacity(int minCapacity){
    products.ensureCapacity(minCapacity);
  }

  public void printCapacityReport(){
    int sz = products.size();
    int cap = products.capacity();
    double pct = (cap > 0) ? (100.0 * sz / cap) : 0;
    int beforeResize = cap - sz;
    System.out.println("current size: " + sz);
    System.out.println("current capacity: " + cap);
    System.out.println("capacity utilization: " + pct + "%");
    System.out.println("can add " + beforeResize + " more before resize");
  }

  // legacy Enumeration - use Iterator for new code
  public void printProductsUsingEnumeration(){    
    Enumeration<Product> e = products.elements();
    System.out.println("\nProducts (via Enumeration)\n------");
    while (e.hasMoreElements()) 
      System.out.println(e.nextElement());  //enumeration's elements method to get next product
  }

  public Vector<Product> getProducts(){
    return new Vector<>(products);
  }
}
