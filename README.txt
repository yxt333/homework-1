YIXIAN TAN, HW1 Vector-Based Inventory Management System

Product
  Single product: id, name, category, price, qty in stock, supplier.
  Implements Comparable for VectorUtils.findMax(). equals/hashCode by productId.

OrderItem
  One line in an order. product id, name, quantity, unit price.
  Subtotal = quantity * unitPrice. calculateSubtotal() recalculates after changes.

Order
  orderId, customer, date, status. Vector<OrderItem> for items.
  add, remove, find item, calculate total, update status.

ProductInventory
  Vector<Product>. methods for managing inventory by adding, remove, find, getByCategory, getLowStock,
  getTotalValue, updateStock, printAll, printCapacityInfo, optimizeCapacity,
  ensureCapacity, printCapacityReport, printProductsUsingEnumeration.

OrderManager
  Vector<Order>. add, find, getByStatus, getByCustomer, getTotalRevenue,
  cancel, printAll, getPendingOrders.

Predicate<T>
  interface with test(T). used by VectorUtils.filter().

VectorUtils
  swap, findMax, countMatches, filter, sumNumbers, averageNumbers.
  null and edge cases handled.

GenericContainer<T>
  Wraps Vector<T>. add, get, remove, size, getAll, clear, contains, addAll.

VectorComparisonDemo
  Vector vs ArrayList: add 10k, access 1k random. timing and memory.

InventorySystemMain
  Uses a menu. Sample products and orders are preloaded. Adds/Removes/Finds Product through using the respective options,
  lists all products, Create Order, View Orders, Process Order, Generate Reports, Exit.


HOW TO COMPILE AND RUN
----------------------
1: using terminal/command prompt: cd to yxt_hw1_submit folder

2: run following commands from hw1 folder:
    javac *.java
    java InventorySystemMain

alternative:
for running the vector vs arraylist comparison:
1: using terminal/command prompt: cd to yxt_hw1_submit folder

2: run the following command from hw1 folder:
    java VectorComparisonDemo


ASSUMPTIONS MADE
----------------

- product id and order id are unique
- date is formatted as string, YYYY-MM-DD
- stock can go to 0, but not negative. (using clamp in productinventory)
- revenue = delivered orders only
- non-numeric input caught with InputMismatchException


WHAT I LEARNED ABOUT VECTORS AND GENERICS
-----------------------------------------

Vectors:
- Vector is thread-safe but slower. ArrayList is faster for single-threaded.

Generics:
- GenericContainer<T> works for String, Integer, Product, etc. no casting.
