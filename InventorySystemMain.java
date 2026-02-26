import java.util.*;

// menu-driven inventory and order management
public class InventorySystemMain{
  private static ProductInventory inv;
  private static OrderManager ordMgr;

  public static void main(String[] args){
    inv = new ProductInventory();
    ordMgr = new OrderManager();

    // sample data
    inv.addProduct(new Product("P001", "Laptop", "Electronics", 999.99, 10, "TechCorp"));
    inv.addProduct(new Product("P002", "T-Shirt", "Clothing", 19.99, 50, "FashionInc"));
    inv.addProduct(new Product("P003", "Mouse", "Electronics", 29.99, 5, "TechCorp"));

    Order o1 = new Order("O001", "Alice", "2024-01-15");
    o1.addItem(new OrderItem("P001", "Laptop", 1, 999.99));
    o1.addItem(new OrderItem("P003", "Mouse", 2, 29.99));
    ordMgr.addOrder(o1);

    Order o2 = new Order("O002", "Bob", "2024-01-16");
    o2.addItem(new OrderItem("P002", "T-Shirt", 3, 19.99));
    ordMgr.addOrder(o2);

    Scanner usrinput = new Scanner(System.in);
    int selection;
    do{
      System.out.println("\n\nInventory Management System\n------");
      System.out.println("(1) Add Product");
      System.out.println("(2) Remove Product");
      System.out.println("(3) Find Product");
      System.out.println("(4) List All Products");
      System.out.println("(5) Create Order");
      System.out.println("(6) View Orders");
      System.out.println("(7) Process Order");
      System.out.println("(8) Generate Reports");
      System.out.println("(9) Exit");
      System.out.print("\nYour selection? ");
      try {
        selection = usrinput.nextInt();
      } catch (InputMismatchException e) {
        usrinput.nextLine();
        selection = 0;
      }
      while ((selection < 1) || (selection > 9)){
        System.out.print("\nInvalid selection, please reenter: ");
        try {
          selection = usrinput.nextInt();
        } catch (InputMismatchException e) {
          usrinput.nextLine();
          selection = 0;
        }
      }
      if (selection == 1) addProduct();
      else if (selection == 2) removeProduct();
      else if (selection == 3) findProduct();
      else if (selection == 4) listProducts();
      else if (selection == 5) createOrder();
      else if (selection == 6) viewOrders();
      else if (selection == 7) processOrder();
      else if (selection == 8) generateReports();
    }while (selection != 9);
  }

  private static void addProduct(){
    Scanner kb = new Scanner(System.in);
    System.out.print("\nPlease input the product id: ");
    String id = kb.nextLine().trim();
    System.out.print("Please input the product name: ");
    String name = kb.nextLine().trim();
    System.out.print("Please input the category: ");
    String cat = kb.nextLine().trim();
    double price = 0;
    int qty = 0;
    try {
      System.out.print("Please input the price: ");
      price = kb.nextDouble();
      if (price < 0) { System.out.println("price cannot be negative"); return; }
      System.out.print("Please input the quantity in stock: ");
      qty = kb.nextInt();
      if (qty < 0) { System.out.println("quantity cannot be negative"); return; }
    } catch (InputMismatchException e) {
      kb.nextLine();
      System.out.println("invalid input - please enter numbers");
      return;
    }
    kb.nextLine();
    System.out.print("Please input the supplier: ");
    String sup = kb.nextLine().trim();
    if (id.isEmpty()) { System.out.println("product id cannot be empty"); return; }
    inv.addProduct(new Product(id, name, cat, price, qty, sup));
    System.out.println("product added");
  }

  private static void removeProduct(){
    Scanner kb = new Scanner(System.in);
    System.out.print("\nPlease input the product id to remove: ");
    String id = kb.nextLine();
    if (inv.removeProduct(id))
      System.out.println("product removed");
    else
      System.out.println("product not found");
  }

  private static void findProduct(){
    Scanner kb = new Scanner(System.in);
    System.out.print("\nPlease input the product id to find: ");
    String id = kb.nextLine();
    Product p = inv.findProduct(id);
    if (p != null)
      System.out.println(p);
    else
      System.out.println("product not found");
  }

  private static void listProducts(){
    if (inv.getTotalProducts() == 0){
      System.out.println("\nyou have no products entered");
      return;
    }
    inv.printAllProducts();
    inv.printCapacityInfo();
  }

  private static void createOrder(){
    Scanner kb = new Scanner(System.in);
    System.out.print("\nPlease input the order id: ");
    String oid = kb.nextLine().trim();
    System.out.print("Please input the customer name: ");
    String cust = kb.nextLine().trim();
    System.out.print("Please input the order date (YYYY-MM-DD): ");
    String dt = kb.nextLine().trim();
    if (oid.isEmpty()) { System.out.println("order id cannot be empty"); return; }
    Order o = new Order(oid, cust, dt);
    int itemCount = 0;
    try {
      System.out.print("How many items are there in this order? ");
      itemCount = kb.nextInt();
      if (itemCount < 0) { System.out.println("item count cannot be negative"); return; }
    } catch (InputMismatchException e) {
      kb.nextLine();
      System.out.println("invalid input - please enter a number");
      return;
    }
    kb.nextLine();  //consume newline left by nextInt before loop
    for (int i = 0; i < itemCount; i = i + 1){
      System.out.print("\nproduct id for item " + (i+1) + " ? ");
      String pid = kb.nextLine().trim();
      Product p = inv.findProduct(pid);
      if (p == null){
        System.out.println("product not found, skipping");
        continue;
      }
      try {  //try to get quantity input and check if it is positive
        System.out.print("quantity? ");
        int qty = kb.nextInt();
        if (qty <= 0) {
          System.out.println("quantity must be positive, skipping");
          kb.nextLine();
          continue;
        }
        o.addItem(new OrderItem(pid, p.getName(), qty, p.getPrice()));
        kb.nextLine();  //consume newline for next product id prompt
      } catch (InputMismatchException e) { //catch invalid quantity input
        kb.nextLine();
        System.out.println("invalid quantity, skipping"); 
      }
    } 
    ordMgr.addOrder(o); 
    System.out.println("order created");
  }

  private static void viewOrders(){
    if (ordMgr.getOrderCount() == 0){
      System.out.println("\nyou have no orders entered");
      return;
    }
    ordMgr.printAllOrders();
  }

  private static void processOrder(){
    Scanner kb = new Scanner(System.in);
    System.out.print("\nPlease input the order id to process: ");
    String oid = kb.nextLine();
    Order o = ordMgr.findOrder(oid);
    if (o == null){
      System.out.println("order not found");
      return;
    }
    System.out.print("new status (Processing/Shipped/Delivered)? ");
    String st = kb.nextLine();
    o.updateStatus(st);
    System.out.println("order status updated");
  }

  private static void generateReports(){
    System.out.println("\nReports\n------");
    System.out.println("Total products: " + inv.getTotalProducts());
    System.out.println("Total inventory value: $" + inv.getTotalInventoryValue());
    System.out.println("Total orders: " + ordMgr.getOrderCount());
    System.out.println("Total revenue (delivered): $" + ordMgr.getTotalRevenue());
    Vector<Product> low = inv.getLowStockProducts(10);
    System.out.println("Low stock items (< 10): " + low.size());
    inv.printCapacityReport();
  }
}
