import java.util.*;

// order with items, customer, date, status
public class Order{
  private String orderId;
  private String customerName;
  private String orderDate;
  private Vector<OrderItem> items;
  private String orderStatus;

  public Order(String orderId, String customerName, String orderDate){
    this.orderId = orderId;
    this.customerName = customerName;
    this.orderDate = orderDate;
    this.items = new Vector<>();
    this.orderStatus = "Pending";
  }

  public String getOrderId(){ return orderId; }
  public void setOrderId(String orderId){ this.orderId = orderId; }
  public String getCustomerName(){ return customerName; }
  public void setCustomerName(String customerName){ this.customerName = customerName; }
  public String getOrderDate(){ return orderDate; }
  public void setOrderDate(String orderDate){ this.orderDate = orderDate; }
  public String getOrderStatus(){ return orderStatus; }
  public void setOrderStatus(String orderStatus){ this.orderStatus = orderStatus; }

  // add item; skip null
  public void addItem(OrderItem item){
    if (item != null) items.add(item);
  }

  // remove first match by productId, true if found
  public boolean removeItem(String productId){
    if (productId == null) return false;
    for (int i = 0; i < items.size(); i = i + 1){
      if (productId.equals(items.get(i).getProductId())){
        items.remove(i);
        return true;
      }
    }
    return false;
  }

  // find by productId, null if not found
  public OrderItem findItem(String productId){
    if (productId == null) return null;
    for (int i = 0; i < items.size(); i = i + 1){
      if (productId.equals(items.get(i).getProductId()))
        return items.get(i);
    }
    return null;
  }

  public double calculateTotal(){
    double tot = 0;
    for (int i = 0; i < items.size(); i = i + 1)
      tot = tot + items.get(i).getSubtotal();
    return tot;
  }

  public int getTotalItems(){
    int tot = 0;
    for (int i = 0; i < items.size(); i = i + 1)
      tot = tot + items.get(i).getQuantity();
    return tot;
  }

  public void updateStatus(String newStatus){
    orderStatus = newStatus;
  }

  public void printOrder(){
    System.out.println("\nOrder " + orderId + " | " + customerName + " | " + orderDate + " | " + orderStatus);
    System.out.println("------");
    for (int i = 0; i < items.size(); i = i + 1)
      System.out.println("  " + (i+1) + ". " + items.get(i));
    System.out.println("Total: $" + calculateTotal());
  }

  public Vector<OrderItem> getItems(){
    return new Vector<>(items);
  }
}
