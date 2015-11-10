import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by john.larosa on 9/10/15.
 */
public class PercolationStats {
    private int[] openSites;
    private int T;
    private int N;

    public PercolationStats(int n, final int t) {
        if (n <= 0 || t <= 0) {
            throw new IllegalArgumentException();
        }

        openSites = new int[t];
        N = n;
        T = t;

        for (int i = 0; i < t; i++) {
            int opened = 0;

            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {

                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;

                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                    opened++;
                }
            }
            this.openSites[i] = opened;
        }
    }                                        // perform T independent experiments on an N-by-N grid

    public double mean() {
        return StdStats.mean(fractionOfOpenSites());
    }                                                   // sample mean of percolation threshold

    public double stddev() {
        if (T == 1) return Double.NaN;
        return StdStats.stddev(fractionOfOpenSites());
    }                                                  // sample standard deviation of percolation threshold

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }                                                   // low  endpoint of 95% confidence interval

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

    private double[] fractionOfOpenSites() {
        double[] x = new double[openSites.length];

        for (int i = 0; i <= openSites.length - 1; i++) {
            x[i] = ((double) openSites[i] / (N * N));
        }

        return x;
    }

    public static void main(String[] args) {
        {
/*            int N = Integer.parseInt(args[0]);
            int T = Integer.parseInt(args[1]);
            PercolationStats stats = new PercolationStats(N, T);
            StdOut.printf("mean = %f\n", stats.mean());
            StdOut.printf("stddev = %f\n", stats.stddev());
            StdOut.printf("95%% confidence interval = %f, %f\n", stats.confidenceLo(), stats.confidenceHi());*/
        }

    }
}
