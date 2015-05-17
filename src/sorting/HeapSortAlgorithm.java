package sorting;

/**
 * Created by USER on 17.05.15.
 */
public class HeapSortAlgorithm extends Thread{
    private Integer s[];

    public void run(){ this.sort(s);};

    public void setArray(Integer s[]){ this.s = s;};

    public void sort(Integer a[]) {
        Integer N = a.length;
        for (int k = N / 2; k > 0; k--) downheap(a, k, N);
        do {

            int T = a[0];
            a[0] = a[N - 1];
            a[N - 1] = T;
            N = N - 1;
            downheap(a, 1, N);

        } while (N > 1);

    }

    public void downheap(Integer a[], int k, int N) {

        int T = a[k - 1];

        while (k <= N / 2) {

            int j = k + k;
            if ((j < N) && (a[j - 1] < a[j])) j++;
            if (T >= a[j - 1]) {

                break;

            } else {

                a[k - 1] = a[j - 1];
                k = j;

            }
        }


        a[k - 1] = T;

    }

}
