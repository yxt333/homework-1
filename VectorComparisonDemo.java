import java.util.*;

// Vector vs ArrayList: add 10k, access 1k random, memory
public class VectorComparisonDemo{
  public static void main(String[] args){
    ProductInventory inv = new ProductInventory();
    inv.addProduct(new Product("P001", "Laptop", "Electronics", 999.99, 10, "TechCorp"));
    inv.addProduct(new Product("P002", "T-Shirt", "Clothing", 19.99, 50, "FashionInc"));
    inv.addProduct(new Product("P003", "Mouse", "Electronics", 29.99, 5, "TechCorp"));

    Vector<Product> vec = inv.getProducts();
    int n = 10000;
    Runtime rt = Runtime.getRuntime();  //gets runtime to get memory and time

    /* add 10000 products - time and memory for Vector */
    rt.gc();      //to trigger garbage collection
    long memBeforeV = rt.totalMemory() - rt.freeMemory();   //gets memory before adding products
    long t1 = System.currentTimeMillis();   //gets time before adding products
    Vector<Product> v2 = new Vector<>(vec);
    for (int i = 0; i < n; i = i + 1)
      v2.add(new Product("P" + i, "Item" + i, "Cat", 10 + i, i, "Sup")); //loop to add 10000 products to vector
    long t2 = System.currentTimeMillis();   //gets time after adding products
    rt.gc();
    long memAfterV = rt.totalMemory() - rt.freeMemory(); 
    long memV = memAfterV - memBeforeV;   //gets memory after adding products

    /* add 10000 products - time and memory for ArrayList */
    v2 = null;
    rt.gc();
    long memBeforeA = rt.totalMemory() - rt.freeMemory();
    long t3 = System.currentTimeMillis();
    ArrayList<Product> a2 = new ArrayList<>(vec);
    for (int i = 0; i < n; i = i + 1)
      a2.add(new Product("P" + i, "Item" + i, "Cat", 10 + i, i, "Sup"));
    long t4 = System.currentTimeMillis();
    rt.gc(); 
    long memAfterA = rt.totalMemory() - rt.freeMemory();
    long memA = memAfterA - memBeforeA;

    /* access 1000 random - need v2 and a2, so rebuild v2 after we nulled it for memory test */
    v2 = new Vector<>(vec);
    for (int i = 0; i < n; i = i + 1)
      v2.add(new Product("P" + i, "Item" + i, "Cat", 10 + i, i, "Sup"));

    Random r = new Random();
    long t5 = System.currentTimeMillis();
    for (int i = 0; i < 1000; i = i + 1){
      int idx = r.nextInt(v2.size());
      v2.get(idx);
    }
    long t6 = System.currentTimeMillis();

    long t7 = System.currentTimeMillis();
    for (int i = 0; i < 1000; i = i + 1){
      int idx = r.nextInt(a2.size());
      a2.get(idx);
    }
    long t8 = System.currentTimeMillis();

    System.out.println("\nVector vs ArrayList Comparison Report\n------------------------------------");
    System.out.println("Add " + n + " elements:");
    System.out.println("  Vector:   " + (t2 - t1) + " ms");
    System.out.println("  ArrayList: " + (t4 - t3) + " ms");
    System.out.println("Access 1000 random elements:");
    System.out.println("  Vector:   " + (t6 - t5) + " ms");
    System.out.println("  ArrayList: " + (t8 - t7) + " ms");
    System.out.println("Memory (approx): Vector " + memV + " bytes, ArrayList " + memA + " bytes");
    System.out.println("\nSummary:");
    System.out.println("- Vector is slower due to synchronization overhead on every operation.");
    System.out.println("- Use ArrayList when you have single-threaded code and want faster performance.");
    System.out.println("- Use Vector when multiple threads access the same collection and you need thread safety.");
  }
}
