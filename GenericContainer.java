import java.util.*;

// wraps Vector<T> - add, get, remove, size, getAll, etc
public class GenericContainer<T>{
  private Vector<T> items;

  public GenericContainer(){
    items = new Vector<>();
  }

  public void add(T item){
    items.add(item);
  }

  public T get(int idx){
    if (idx < 0 || idx >= items.size()) return null;
    return items.get(idx);
  }

  public boolean remove(T item){
    return items.remove(item);
  }

  public int size(){
    return items.size();
  }

  public Vector<T> getAll(){
    return new Vector<>(items);
  }

  public void clear(){
    items.clear();
  }

  public boolean contains(T item){
    return items.contains(item);
  }

  // add all from other; skip null
  public void addAll(Vector<T> other){
    if (other != null)
      for (int i = 0; i < other.size(); i = i + 1)
        items.add(other.get(i));
  }
}
