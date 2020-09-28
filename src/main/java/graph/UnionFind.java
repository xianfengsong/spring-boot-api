package graph;

/**
 * 并查集：一种快速判断网络中节点连通性的算法
 * 名词
 * 触点：节点
 * 连接：节点连线，没有方向
 * 连通分量：连接在一起的触点集合
 * 实现
 * quick-find
 * quick-union
 * weighted-quick-union
 */
public class UnionFind {
    /**
     * 核心结构 数组，用来保存节点直接的连接关系
     */
    private int[] id;
    /**
     * 每个节点上连通触点的数量，用来表示权重
     */
    private int[] size;
    /**
     * 连通分量的个数
     */
    private int count;

    public UnionFind(int n) {
        count = n;
        id = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    public UnionFind(char[][] grid) {
        int h = grid.length, w = grid[0].length;
        id = new int[w * h];
        size = new int[w * h];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (grid[i][j] == '1') {
                    id[i * w + j] = i * w + j;
                    count++;
                }
            }
        }
    }

    /**
     * weight quick union
     * 先分别找到两个触点的root
     * 要把size小的root指向，size大的root,这样可以让节点组成的树更"平衡"
     * （可以理解为size[r]代表节点r的子元素数量，size大，说明子元素多，树相对更高）
     * （我们要把小个子的人放到高个子的人的房间里，这样房间不会很快增高，反之，每次都要加高房间）
     *
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot != qRoot) {
            if (size[pRoot] > size[qRoot]) {
                id[qRoot] = pRoot;
                size[pRoot] += size[qRoot];
            } else {
                id[pRoot] = qRoot;
                size[qRoot] += size[pRoot];
            }
            count--;
        }
    }

    public int find(int p) {
        int copy = p;
        while (id[p] != p) {
            p = id[p];
        }
        //路径压缩： 再加一个循环，让路径上所有触点直接指向他们的root（把触点到root的路径变短）
        while (id[copy] != copy) {
            copy = id[copy];
            id[copy] = p;
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }
}
