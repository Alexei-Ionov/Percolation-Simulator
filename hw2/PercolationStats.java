package hw2;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] percolationValues;
    private int percolationValuesLength;
    private int numOfCells;

    private Percolation newPercolation;


    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        numOfCells = N*N;
        percolationValues = new double[T];
        for (int i = 0; i < T; i++) {
            newPercolation = pf.make(N);
            while (!newPercolation.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                newPercolation.open(row, col);
            }
            double val = (double)newPercolation.numberOfOpenSites() / numOfCells;
            percolationValues[i] = val;
        }
        percolationValuesLength = T;
    }

    public double mean() {
        return StdStats.mean(percolationValues);
    }
    public double stddev() {
        return StdStats.stddev(percolationValues);
    }
    public double confidenceLow() {
        double sqT = Math.pow(percolationValuesLength, 0.5);
        return mean() - (double)((1.96 * stddev()) / sqT);
    }
    public double confidenceHigh() {
        double sqT = Math.pow(percolationValuesLength, 0.5);
        return mean() + (double)((1.96 * stddev()) / sqT);
    }
}
