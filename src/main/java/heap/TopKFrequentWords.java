package heap;

import java.util.*;

/**
 * 692. 前K个高频单词
 * 主要是练习lambda 和 comparator
 * https://leetcode-cn.com/problems/top-k-frequent-words/
 */
public class TopKFrequentWords {

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> m = new HashMap<>();
        for (String w : words) {
            m.merge(w, 1, (a, b) -> a + b);
        }
        PriorityQueue<String> q = new PriorityQueue<>((
                //注意字母是从小到大,数字从大到小
                (o1, o2) -> m.get(o1).equals(m.get(o2)) ? o2.compareTo(o1) : m.get(o1).compareTo(m.get(o2))
        ));
        for (String w : words) {
            q.offer(w);
            if (q.size() > k) {
                q.poll();
            }
        }
        List<String> r = new ArrayList<>();
        while (!q.isEmpty()) {
            r.add(q.poll());
        }
        Collections.reverse(r);
        return r;
    }
}
