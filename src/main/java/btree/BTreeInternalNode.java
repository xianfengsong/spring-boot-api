package btree;

/**
 * Created by Kali on 14-5-26.
 */
class BTreeInternalNode<K extends Comparable<K>> extends AbstractBTreeNode<K> {

    private final Object[] keys;
    private final AbstractBTreeNode<K>[] children;
    private int nkey = 0;
    private int nchild = 0;

    BTreeInternalNode(int degree) {
        super(degree);
        keys = new Object[2 * degree - 1];
        children = new AbstractBTreeNode[2 * degree];
    }

    @Override
    protected boolean isLeaf() {
        return false;
    }


    @Override
    K getKey(int idx) {
        return (K) keys[idx];
    }

    @Override
    protected K setKey(K newKey, int oldKeyIndex) {
        K old = (K) keys[oldKeyIndex];
        keys[oldKeyIndex] = newKey;
        return old;
    }

    @Override
    protected void setChild(AbstractBTreeNode<K> sub, int index) {
        children[index] = sub;
    }

    @Override
    AbstractBTreeNode<K> getChild(int index) {
        if (index >= 0 && index < children.length) {
            return children[index];
        }
        return null;
    }

    @Override
    protected int setNKey(int nkey) {
        int old = this.nkey;
        this.nkey = nkey;
        return old;
    }

    @Override
    int nkey() {
        return nkey;
    }

    @Override
    int nchild() {
        return nchild;
    }

    @Override
    protected int setNChild(int nchild) {
        int old = this.nchild;
        this.nchild = nchild;
        return old;
    }


    @Override
    K search(K key) {
        int index = indexOfKey(key);
        if (index >= 0) {
            return (K) keys[index];
        }
        index = 0;
        while (key.compareTo((K) keys[index++]) > 0) ;
        return children[index].search(key);
    }

    @Override
    void insertNotFull(K key) {
        checkNotFull();
        int i = 0;
        while (i < nkey && key.compareTo((K) keys[i]) >= 0) {
            i++;
        }
        if (this.children[i].isFull()) {
            this.splitChild(i);
            if (key.compareTo((K) this.keys[i]) > 0) {
                i++;
            }
        }

        this.children[i].insertNotFull(key);
    }

    @Override
    void deleteNotEmpty(K key) {
        //key in this node
        //在当期节点中，根据左右子节点key个数执行 提拔+递归删除 或者 合并左右+递归删除 操作
        if (this.existsKey(key)) {
            int index = indexOfKey(key);
            AbstractBTreeNode<K> node;
            //predecessor child could delete 左边子树选最大的提拔
            if ((node = children[index]).nkey() >= degree) {
                //maximum key in predecessor
                K repKey = node.getKey(node.nkey() - 1);
                //递归
                node.deleteNotEmpty(repKey);
                setKey(repKey, index);
            }
            //follow child could delete a key 右边子树选最小的提拔
            else if ((node = children[index + 1]).nkey() >= degree) {
                //minimum key in follow
                K repKey = node.getKey(0);
                node.deleteNotEmpty(repKey);
                setKey(repKey, index);
            }

            //merge predecessor with follow
            else {
                //node 是左节点 和 右节点合并
                node = children[index];
                node.merge(key, children[index + 1]);
                //本节点中删除key对应节点
                this.deleteKey(index);
                //因为合并本节点children要减少一个
                this.deleteChild(index + 1);
                //在合并得到的节点中删除key
                node.deleteNotEmpty(key);
            }
        }

        //key may exist in child
        //key在子节点中，如果子树根节点关键字不少于degree（阶m/2）执行递归删除
        //3a 如果子树根节点关键字<degree,先尝试从子树根节点的兄弟节点移除一个关键字提拔到当期节点，当期节点被替换掉的关键字给子树根节点
        //3b 如果兄弟节点关键字也都<degree，就把子树根节点和一个兄弟合并，同时当期节点去掉一个关键字，插入合并后的子节点
        else {
            int i = 0;
            //find proper child the key may exists in
            while (i < nkey) {
                if (key.compareTo((K) keys[i]) < 0)
                    break;
                i++;
            }
            AbstractBTreeNode<K> target = children[i];
            //child has enough key
            if (target.nkey() >= degree) {
                target.deleteNotEmpty(key);
            } else {
                AbstractBTreeNode<K> sibling;
                //对应情况 3a
                //先让从子节点左边兄弟取关键字
                if (i > 0 && (sibling = children[i - 1]).nkey() >= degree) {
                    //抢左边兄弟的最后一个child，放到子节点children中
                    if (!target.isLeaf()) {
                        AbstractBTreeNode<K> sub = sibling.deleteChild(nchild); //last child
                        target.insertChild(sub, 0);
                    }
                    //移除左兄弟的最大关键字
                    K repKey = sibling.deleteKey(sibling.nkey() - 1);    //maximum key
                    //把左兄弟的最大关键字提到当前节点，这里返回的repKey是被替换的旧关键字，它小于
                    repKey = setKey(repKey, i - 1);
                    //旧关键字比提拔上来的新key小，但比target中的key都大，所以插入target，这样子树关键字个数满足条件，
                    target.insertKey(repKey);
                    target.deleteNotEmpty(key);
                }
                //去右边兄弟抢节点，同上，就是改成取最小节点
                else if (i < nkey && (sibling = children[i + 1]).nkey() >= degree) {
                    if (!target.isLeaf()) {
                        AbstractBTreeNode<K> sub = sibling.deleteChild(0);  //first child
                        target.insertChild(sub, target.nchild());
                    }
                    K repKey = sibling.deleteKey(0);                    //minimum key
                    repKey = setKey(repKey, i);
                    target.insertKey(repKey);
                    target.deleteNotEmpty(key);
                }
                //左右兄弟都抢不到，只能和兄弟合并
                else {
                    //如果左边有兄弟，和它合并
                    if (i > 0) {
                        //当期节点也去掉一个关键字，下放到子节点（因为少了一个child,该去掉一个key）
                        K repKey = this.deleteKey(i - 1);
                        sibling = children[i - 1];
                        sibling.merge(repKey, target);
                        //去掉之前的子节点
                        this.deleteChild(target);
                        //在合并后的新节点删除key
                        sibling.deleteNotEmpty(key);
                    } else {
                        //和右边兄弟合并
                        K repKey = this.deleteKey(i);
                        sibling = children[i + 1];
                        target.merge(repKey, sibling);
                        deleteChild(i + 1);
                        target.deleteNotEmpty(key);
                    }
                }
            }
        }

    }

    @Override
    protected void splitChild(int child) {
        AbstractBTreeNode<K> old = children[child];
        AbstractBTreeNode<K> neo = old.isLeaf()
                ? new BTreeLeaf<>(degree)
                : new BTreeInternalNode<>(degree);
        K middle = old.splitSelf(neo);
        this.insertKey(middle);
        this.insertChild(neo, child + 1);
    }

    @Override
    protected K splitSelf(AbstractBTreeNode<K> newNode) {
        if (!(newNode instanceof BTreeInternalNode)) {
            throw new RuntimeException("Instance not match.");
        }
        if (!isFull()) {
            throw new RuntimeException("Node is not full");
        }

        K middle = (K) keys[degree - 1];
        BTreeInternalNode<K> node = (BTreeInternalNode) newNode;
        int i = 0;
        while (i < degree - 1) {
            node.keys[i] = this.keys[i + degree];
            this.keys[i + degree] = null;
            i++;
        }
        this.keys[degree - 1] = null;

        i = 0;
        while (i < degree) {
            node.children[i] = this.children[i + degree];
            this.children[i + degree] = null;
            i++;
        }

        this.nkey = degree - 1;
        node.nkey = degree - 1;
        this.nchild = degree;
        node.nchild = degree;
        return middle;
    }

    /**
     * 把关键字k和兄弟节点合入当期节点this
     *
     * @param middle  middle key of the two node
     * @param sibling sibling node to merge
     */
    @Override
    protected void merge(K middle, AbstractBTreeNode<K> sibling) {
        if (!(sibling instanceof BTreeInternalNode)) {
            throw new RuntimeException("Sibling is not leaf node");
        }
        BTreeInternalNode node = (BTreeInternalNode) sibling;
        this.insertKey(middle);
        for (int i = 0; i < node.nkey(); i++) {
            this.insertKey((K) node.keys[i]);
        }
        //兄弟节点的子节点也合入
        for (int i = 0; i < node.nchild(); i++) {
            insertChild(node.children[i], i + degree);
        }
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(" internal node ---- ").append("size: ").append(nkey).append(" keys:").append("[");
        for (int i = 0; i < nkey; i++) {
            sb.append(keys[i]).append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
