// Time Complexity : O (n x l) where n is number of words and l is avg length of a word
// Space Complexity : O (n x l)
// Did this code successfully run on Leetcode : yes

// Your code here along with comments explaining your approach
public class LongestWord {
    static class TrieNode {
        TrieNode[] children;
        boolean end;

        TrieNode() {
            children = new TrieNode[26];
            end = false;
        }
    }

    TrieNode root = new TrieNode();

    // insert a word into the Trie
    void insert(String word) {
        TrieNode node = root;
        for(char c: word.toCharArray()) {
            if(node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.end = true;
    }

    boolean search(String word) {
        TrieNode node = root;
        for(char c: word.toCharArray()) {
            if(node.children[c - 'a'] == null) {
                return false;
            }
            node = node.children[c - 'a'];
        }
        return node.end;
    }

    String result = "";
    void dfs(TrieNode node, StringBuilder word) {
        //if at end, compare the built word with the result
        //take the longer one, or, if equal, the lexicographically smaller one
        if(node.end) {
            if(word.length() > result.length()) {
                result = word.toString();
            } else if(word.length() == result.length() && word.toString().compareTo(result) < 0) {
                result = word.toString();
            }
        }

        for(char c = 'a'; c <= 'z'; c++) {
            TrieNode child = node.children[c - 'a'];
            if(child != null && child.end) {
                //action and recurse
                dfs(child, word.append(c));
                //backtrack
                word.deleteCharAt(word.length() - 1);
            }
        }
    }

    public String longestWord(String[] words) {
        for(String word: words) insert(word);
        dfs(root, new StringBuilder());
        return result;
    }
}
