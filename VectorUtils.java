import java.util.*;

// swap, findMax, countMatches, filter, sumNumbers, averageNumbers
public class VectorUtils{

  // swap at idx1 and idx2; no-op if invalid or null
  public static <T> void swap(Vector<T> vec, int idx1, int idx2){
    if (vec == null || idx1 < 0 || idx2 < 0 || idx1 >= vec.size() || idx2 >= vec.size()) return;
    T tmp = vec.get(idx1);
    vec.set(idx1, vec.get(idx2));
    vec.set(idx2, tmp);
  }

  // max by compareTo; null if empty
  public static <T extends Comparable<T>> T findMax(Vector<T> vec){
    if (vec == null || vec.isEmpty()) return null;
    T max = vec.get(0);
    for (int i = 1; i < vec.size(); i = i + 1){
      T curr = vec.get(i);
      if (curr.compareTo(max) > 0) max = curr;
    }
    return max;
  }

  // count matches of target; 0 if null
  public static <T> int countMatches(Vector<T> vec, T target){
    if (vec == null || target == null) return 0;
    int cnt = 0; 
    for (int i = 0; i < vec.size(); i = i + 1){
      if (target.equals(vec.get(i))) cnt = cnt + 1;
    }
    return cnt;
  }

  // filter where condition.test() true; empty if null
  public static <T> Vector<T> filter(Vector<T> vec, Predicate<T> condition){
    Vector<T> out = new Vector<>();
    if (vec == null || condition == null) return out;
    for (int i = 0; i < vec.size(); i = i + 1){
      if (condition.test(vec.get(i))) out.add(vec.get(i));
    }
    return out;
  }

  // sum as double; 0.0 if null or empty
  public static <T extends Number> double sumNumbers(Vector<T> numbers){
    if (numbers == null || numbers.isEmpty()) return 0.0;
    double tot = 0;
    for (int i = 0; i < numbers.size(); i = i + 1)
      tot = tot + numbers.get(i).doubleValue();
    return tot;
  }

  // average; 0.0 if null or empty
  public static <T extends Number> double averageNumbers(Vector<T> numbers){
    if (numbers == null || numbers.isEmpty()) return 0.0;
    return sumNumbers(numbers) / numbers.size();
  }
}
