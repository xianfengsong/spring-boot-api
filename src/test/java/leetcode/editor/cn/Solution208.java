package leetcode.editor.cn;//Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
// 这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
//
// 请你实现 Trie 类： 
//
// 
// Trie() 初始化前缀树对象。 
// void insert(String word) 向前缀树中插入字符串 word 。 
// boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false
// 。 
// boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否
//则，返回 false 。 
// 
//
// 
//
// 示例： 
//
// 
//输入
//["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
//[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
//输出
//[null, null, true, false, true, null, true]
//
//解释
//Trie trie = new Trie();
//trie.insert("apple");
//trie.search("apple");   // 返回 True
//trie.search("app");     // 返回 False
//trie.startsWith("app"); // 返回 True
//trie.insert("app");
//trie.search("app");     // 返回 True
// 
//
// 
//
// 提示： 
//
// 
// 1 <= word.length, prefix.length <= 2000 
// word 和 prefix 仅由小写英文字母组成 
// insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次 
// 
// Related Topics 设计 字典树 
// 👍 657 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Trie {
    Node root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new Node();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        insert(word, 0, root);
    }

    private void insert(String word, int index, Node node) {
        if (index >= word.length()) {
            return;
        }
        boolean isWord = index == word.length() - 1;
        String val = word.substring(0, index + 1);
        if (node.children.containsKey(val)) {
            Node child = node.children.get(val);
            if (isWord && !child.isWorld) {
                child.isWorld = true;
            }
            insert(word, index + 1, child);
        } else {

            Node n = new Node(val, isWord);
            node.children.put(val, n);
            insert(word, index + 1, n);
        }
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        return search(word, 0, root);
    }

    private boolean search(String word, int index, Node node) {
        if (index >= word.length()) {
            return false;
        }
        Node child = node.children.get(word.substring(0, index + 1));
        if (child != null) {
            if (index == word.length() - 1 && child.isWorld) {
                return true;
            } else {
                return search(word, index + 1, child);
            }
        }
        return false;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return startsWith(prefix, 0, root);
    }

    private boolean startsWith(String word, int index, Node node) {
        if (index >= word.length()) {
            return false;
        }
        Node child = node.children.get(word.substring(0, index + 1));
        if (child != null) {
            if (index == word.length() - 1) {
                return true;
            } else {
                return startsWith(word, index + 1, child);
            }
        }
        return false;
    }

    class Node {
        public Map<String, Node> children;
        public String val;
        public boolean isWorld;

        public Node() {
            this.children = new HashMap<>();
        }

        public Node(String val, boolean isWorld) {
            this.val = val;
            this.isWorld = isWorld;
            this.children = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.search("apple");   // 返回 True
        trie.search("app");     // 返回 False
        trie.startsWith("app"); // 返回 True
        trie.insert("app");
        System.out.println(trie.search("app"));     // 返回 True

    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)
