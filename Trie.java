// Time Complexity : O(L) where L is length of word being searched
// Space Complexity : O (n*L) where n is number of words in the dictionary and L is avg length
// Did this code successfully run on Leetcode :Yes

// Your code here along with comments explaining your approach
import java.util.HashMap;
import java.util.Map;

class TrieNode {
    Map<Character, TrieNode> links;

    private boolean end;

    public TrieNode() {
        links = new HashMap<>();
    }

    public boolean contains(char c) {
        return links.containsKey(c);
    }

    public TrieNode get(char c) {
        return links.get(c);
    }

    public void put(char c, TrieNode tNode) {
        links.put(c, tNode);
    }

    public void setEnd() {
        this.end = true;
    }

    public boolean isEnd() {
        return end;
    }
}
public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode currNode = root;
        for(char c: word.toCharArray()) {
            if(!currNode.contains(c)) {
                currNode.put(c, new TrieNode());
            }
            currNode = currNode.get(c);
        }
        currNode.setEnd();
    }

    TrieNode searchPrefix(String word) {
        TrieNode currNode = root;
        for(char c: word.toCharArray()) {
            if (currNode.contains(c)) {
                currNode = currNode.get(c);
            } else {
                return null;
            }
        }
        return currNode;
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }

}
