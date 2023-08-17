package org.ignite.iterables;

public class InsertionSort<T> implements Sort<T> {

    @Override
    @SuppressWarnings("unchecked")
    public List<T> sort(List<T> source) {

        Object x;
        int j;

        for (int i = 2; i <= source.size(); i++) {

            x = (Integer) source.get(i);
            j = i - 1;

            source.set(0, (T) x);

            while ((Integer) x < (Integer) source.get(j)) {
                source.set(j + 1, source.get(j));
                j--;
            }

            source.set(j + 1, (T) x);
        }

        return source;
    }

}
