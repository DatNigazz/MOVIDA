package src.niccolaibalica.sort;

import java.util.Comparator;
import java.lang.reflect.Array;

public class MergeSort{
    //TODO quale Comparator usare qui? Dobbiamo poter scegliere?
    //TODO c'e' bisogno di tipizzare?
    public static<V> void merge (V[] a, V[] l, V[] r, int left, int right, Comparator<V> c) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (c.compare(l[i], r[j]) <= 0) { //TODO testare come operano i comparators
                a[k++] = r[j++];
            }
            else {
                a[k++] = l[i++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
  }

  public static<V> void sort(V[] a, int n, Comparator<V> c, Class<V> param) {
      if (n < 2) {
          return;
      }
      int mid = n / 2;

      V[] l = null;
      l = (V[]) Array.newInstance(param, mid);

      V[] r = null;
      r = (V[]) Array.newInstance(param, n-mid);

      for (int i = 0; i < mid; i++) {
          l[i] = a[i];
      }
      for (int i = mid; i < n; i++) {
          r[i - mid] = a[i];
      }
      sort(l, mid, c, param);
      sort(r, n - mid, c, param);

      merge(a, l, r, mid, n - mid, c);
      }
  }
