public class UnionFind {
    // TODO: Instance variables

    private int[] uf;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        uf = new int[N];
        for (int i = 0; i < N; i++) {
            uf[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
//        int x = uf[v];
//        if (x >= 0) {
//            return sizeOf(x);
//        }
//        return x*(-1);      原本是这样写的，写了find函数后就可以简化了
        return -1 * uf[find(v)];
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        return uf[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (find(v1) == find(v2)) {
            return true;
        }
        return false;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        if (v < 0 || v > uf.length) {
            throw new IllegalArgumentException("The argument is less than 0 or greater than uf.length.");
        }

        if (parent(v) < 0) {
            return v;
        } else {
            return uf[v] = find(parent(v));
        }

    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        int sizeOfv1 = sizeOf(v1);
        int sizeOfv2 = sizeOf(v2);
        int rootOfv1 = find(v1);
        int rootOfv2 = find(v2);

        if (connected(v1, v2)) {
            System.out.println(v1 + " has connected with "+ v2);

        } else {
            if (sizeOfv1 > sizeOfv2) {
                uf[rootOfv2] = rootOfv1;
                uf[rootOfv1] = uf[rootOfv1] - sizeOfv2;
            } else {                   // sizeOfv1 <= sizeOfv2
                uf[rootOfv1] = rootOfv2;
                uf[rootOfv2] = uf[rootOfv2] - sizeOfv1;
            }
        }
    }

}
