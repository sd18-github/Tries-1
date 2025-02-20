// Time Complexity : O (m x l) where m is number of words in the sentence and l is average length of prefixes
// Space Complexity : O (n x l) where n is number of words in the dictionary
// Did this code successfully run on Leetcode : Yes

// Your code here along with comments explaining your approach
import java.util.List;

public class ReplaceWords {
    // Use a Trie to store the prefixes
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

    // search for a prefix of word in the Trie
    // build it and return if present, else return an empty String
    String searchPrefix(String word) {
        TrieNode node = root;
        StringBuilder prefix = new StringBuilder();
        for(char c: word.toCharArray()) {
            // if end is true, a prefix was found: return
            if(node.end) return prefix.toString();
            // if the child is null here, return an empty string
            if(node.children[c - 'a'] == null) return "";
            prefix.append(c);
            node = node.children[c - 'a'];
        }
        // the whole string was the prefix
        return word;
    }
    public String replaceWords(List<String> dictionary, String sentence) {
        for(String word: dictionary) insert(word);
        StringBuilder result = new StringBuilder();
        for(String word: sentence.split(" ")) {
            String prefix = searchPrefix(word);
            // if a prefix is found, append that, else append the og word
            result.append(prefix.isEmpty() ? word : prefix).append(" ");
        }
        return result.toString().trim();
    }
}
