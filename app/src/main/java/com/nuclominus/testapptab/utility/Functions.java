package com.nuclominus.testapptab.utility;

import java.util.ArrayList;

public class Functions {
    public interface IF1<T, V1> {
        T call(V1 v1);
    }

    public static <T, V> ArrayList<V> map(Iterable<T> items, IF1<V, T> func) {
        if(items == null) return new ArrayList<>();

        ArrayList<V> result = new ArrayList<>();
        for (T item : items) {
            if(item == null) continue;

            V entry = func.call(item);
            if (entry != null) {
                result.add(entry);
            }
        }
        return result;
    }
}
