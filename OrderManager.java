import java.util.*;

// Vector of orders - add, find, filter, revenue, cancel
public class OrderManager{
  private Vector<Order> orders;

  public OrderManager(){
    orders = new Vector<>();
  }

  // add order; skip null
  public void addOrder(Order order){
    if (order != null) orders.add(order);
  }

  // find by id, null if not found
  public Order findOrder(String orderId){
    if (orderId == null) return null;
    for (int i = 0; i < orders.size(); i = i + 1){
      if (orderId.equals(orders.get(i).getOrderId()))
        return orders.get(i);
    }
    return null;
  }

  // orders by status; empty if status null
  public Vector<Order> getOrdersByStatus(String status){
    Vector<Order> out = new Vector<>();
    if (status == null) return out;
    for (int i = 0; i < orders.size(); i = i + 1){
      if (status.equals(orders.get(i).getOrderStatus()))
        out.add(orders.get(i));
    }
    return out;
  }

  // orders by customer; empty if name null
  public Vector<Order> getOrdersByCustomer(String customerName){
    Vector<Order> out = new Vector<>();
    if (customerName == null) return out;
    for (int i = 0; i < orders.size(); i = i + 1){
      if (customerName.equals(orders.get(i).getCustomerName()))
        out.add(orders.get(i));
    }
    return out;
  }

  public double getTotalRevenue(){
    double tot = 0;
    for (int i = 0; i < orders.size(); i = i + 1){
      if (orders.get(i).getOrderStatus().equals("Delivered"))
        tot = tot + orders.get(i).calculateTotal();
    }
    return tot;
  }

  // set status to Cancelled; no-op if not found
  public void cancelOrder(String orderId){
    Order o = findOrder(orderId);
    if (o != null) o.updateStatus("Cancelled");
  }

  public void printAllOrders(){
    System.out.println("\nAll Orders\n------");
    for (int i = 0; i < orders.size(); i = i + 1)
      orders.get(i).printOrder();
  }

  public Vector<Order> getPendingOrders(){
    return getOrdersByStatus("Pending");
  }

  public int getOrderCount(){
    return orders.size();
  }
}
