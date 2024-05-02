import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    private boolean[][] grid;
    private int OpenSiteNum;
    private int rowLength;

    private WeightedQuickUnionUF uf;
    private int vts;    // virtual top site
    private int vbs;    // virtual bottom site

    private WeightedQuickUnionUF fixIsFullUf;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        grid = new boolean[N][N];
        OpenSiteNum = 0;
        rowLength = N;

        uf = new WeightedQuickUnionUF(N*N + 2);  // 多了一个vts和vbs，所以加2
        fixIsFullUf = new WeightedQuickUnionUF(N*N + 1); //  不需要vbs了，会导致isFull存在问题
        vts = N*N;
        vbs = N*N + 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;           // false 表示为block状态
            }
        }

    }

    private int xyTo1D(int r, int c) {
        return r * rowLength + c;
    }

    private void upperUnion(int row, int col) {   // row 要大于0,不然可能出现上面没有，导致越界的情况
        if (row > 0 && isOpen(row - 1,col)) {
            uf.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            fixIsFullUf.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        }
    }

    private void downUnion(int row, int col) {   // row 要小于N-1,不然可能出现下面没有，导致越界的情况
        if (row < rowLength - 1 && isOpen(row + 1, col)) {
            uf.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            fixIsFullUf.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }
    }

    private void leftUnion(int row, int col) {   // col 要大于0, 不然可能出现左边没有的情况
        if (col > 0 && isOpen(row, col - 1)) {
            uf.union(xyTo1D(row,col), xyTo1D(row, col-1));
            fixIsFullUf.union(xyTo1D(row, col), xyTo1D(row, col-1));
        }
    }

    private void rightUnion(int row, int col) {  // col 要小于N-1, 不然可能出现右边没有的情况
        if (col < rowLength - 1 && isOpen(row, col+1)) {
            uf.union(xyTo1D(row,col), xyTo1D(row, col+1));
            fixIsFullUf.union(xyTo1D(row, col), xyTo1D(row, col+1));
        }
    }

    private void vtsUnion(int row, int col) {   // 处于0行的，并且没有和vts连接的，就union起来
        int index = xyTo1D(row,col);
        if(row == 0) {
            uf.union(index, vts);
            fixIsFullUf.union(index,vts);
        }
    }

    private void vbsUnion(int row, int col) {    // 处于N-1行的，并且没有和vbs连接的，就union起来
        int index = xyTo1D(row,col);            // union函数里已经有if (rootP == rootQ) return
        if (row == rowLength-1) {               // 这行代码做判断是否连接过了，所以不需要再写connected函数判断
            uf.union(index, vbs);
        }
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        grid[row][col] = true;
        OpenSiteNum++;

        upperUnion(row, col);
        downUnion(row, col);
        leftUnion(row,col);
        rightUnion(row,col);
        vtsUnion(row,col);
        vbsUnion(row,col);

    }



    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        if (grid[row][col]) {
            return true;
        }
        return false;
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        int index = xyTo1D(row,col);
        if (fixIsFullUf.connected(index, vts)) {
            return true;
        }
        return false;
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return OpenSiteNum;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        if (uf.connected(vts, vbs)) {
            return true;
        }
        return false;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
