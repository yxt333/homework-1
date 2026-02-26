import java.util.*;

// one line in an order - product id, name, qty, unit price, subtotal
public class OrderItem{
  private String productId;
  private String productName;
  private int quantity;
  private double unitPrice;
  private double subtotal;

  // subtotal = quantity * unitPrice
  public OrderItem(String productId, String productName, int quantity, double unitPrice){
    this.productId = productId;
    this.productName = productName;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
    this.subtotal = quantity * unitPrice;
  }

  public String getProductId(){ return productId; }
  public void setProductId(String productId){ this.productId = productId; }
  public String getProductName(){ return productName; }
  public void setProductName(String productName){ this.productName = productName; }
  public int getQuantity(){ return quantity; }
  public void setQuantity(int quantity){ this.quantity = quantity; }
  public double getUnitPrice(){ return unitPrice; }
  public void setUnitPrice(double unitPrice){ this.unitPrice = unitPrice; }
  public double getSubtotal(){ return subtotal; }
  public void setSubtotal(double subtotal){ this.subtotal = subtotal; }

  // recalc subtotal after qty or price change
  public double calculateSubtotal(){
    subtotal = quantity * unitPrice;
    return subtotal;
  }

  public String toString(){
    return quantity + " x " + productName + " ($" + unitPrice + ") = $" + subtotal;
  }
}
