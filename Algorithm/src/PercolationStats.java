public class PercolationStats {
    private int N;
    private int T;
    private double[] xts;
    private double sum = 0;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException();
        this.N = N;
        this.T = T;
        xts = new double[T];

        // calculate
        percolate();

    }

    private void percolate() {

        for (int i = 0; i < T; i++) {
            // get count
            Percolation per = new Percolation(N);
            int count = 0;

            while (!per.percolates()) {
                int p = StdRandom.uniform(1, N + 1);
                int q = StdRandom.uniform(1, N + 1);
                if (!per.isOpen(p, q)) {
                    count += 1;
                    per.open(p, q);
                }

            }
            // calc xt
            double xt = ((double) count) / (N * N);
            xts[i] = xt;
            sum += xt;

        }

    }

    public double mean() {
        return sum / T;
    }

    public double stddev() {
        double theta = 0;
        double mean = mean();
        for (int i = 0; i < xts.length; i++) {
            theta += (xts[i] - mean) * (xts[i] - mean);
        }

        return Math.sqrt(theta / (T - 1));
    }

    public double confidenceLo() {
        double mean = mean();
        double lo = mean - (1.96 * stddev() / (Math.sqrt(T)));
        return lo;
    }

    public double confidenceHi() {
        double mean = mean();
        double lo = mean + (1.96 * stddev() / (Math.sqrt(T - 1)));
        return lo;
    }

    public static void main(String[] args) {
        // Stopwatch watch =new Stopwatch();

        int N = StdIn.readInt();
        int T = StdIn.readInt();
        PercolationStats stats = new PercolationStats(N, T);
        StdOut.print("mean                    = " + stats.mean() + "\n");
        StdOut.print("stddev                  = " + stats.stddev() + "\n");
        StdOut.print("95% confidence interval = " + stats.confidenceLo());
        StdOut.print(", " + stats.confidenceHi());

        // System.out.println("\n time "+watch.elapsedTime());
    }

}
