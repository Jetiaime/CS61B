package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> cmp;

    public MaxArrayDeque(Comparator<T> cmp) {
        this.cmp = cmp;
    }

    public T max() {
        return max(cmp);
    }

    public T max(Comparator<T> c) {
        T _mx = null;
        for (T item : this) {
            if (_mx == null || c.compare(item, _mx) > 0) {
                _mx = item;
            }
        }
        return _mx;
    }
}
