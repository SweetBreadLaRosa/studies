/**
 * Created by john.larosa on 9/8/15.
 */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int[] grid;
    private int n;
    private WeightedQuickUnionUF uf;

    //create the grid N*N
    public Percolation(int N) {
        if (N <= 0){
            throw new java.lang.IllegalArgumentException("N needs to be larger than 0");
        }

        n = N;
        grid = new int[(n*n) + 2];
        uf = new WeightedQuickUnionUF((n*n) + 2);
    }                                                               // create N-by-N grid, with all sites blocked

    public void open(int i, int j) {
        checkInput(i, j);

        if (isOpen(i, j)) {
            return;
        }

        int currentSpotInGrid = flatten2dArray(i, j);
        grid[currentSpotInGrid] = 1;

        if (j > 1 && isOpen(i, j - 1)) {
            uf.union(currentSpotInGrid, flatten2dArray(i, j - 1));
        }

        if (j < n && isOpen(i, j + 1)) {
            uf.union(currentSpotInGrid, flatten2dArray(i, j + 1));
        }

        if (i == n ){
            uf.union((n*n) + 1, currentSpotInGrid);
        }

       if (i == 1 ){
            uf.union(n*n, flatten2dArray(i,j));
        }

        if (i > 1 && isOpen(i - 1, j)) {
            uf.union(currentSpotInGrid, flatten2dArray(i - 1, j));
        }

        if (i < n && isOpen(i + 1, j)) {
            uf.union(currentSpotInGrid, flatten2dArray(i + 1, j));
        }
    }

                                                                    // open site (row i, column j) if it is not open already

    public boolean isOpen(int i, int j) {
        checkInput(i,j);
        return grid[flatten2dArray(i,j)] == 1;
    }

    public boolean isFull(int i, int j) {
        checkInput(i,j);
        return uf.connected(n*n, flatten2dArray(i,j));
    }

    private void checkInput(int i, int j){
        if(i < 1 || j < 1){
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    private int flatten2dArray(int i, int j){
        return (n * (i - 1)) + j - 1;
    }

    public boolean percolates() {
        return uf.connected(n*n,n*n + 1);
    }
}
