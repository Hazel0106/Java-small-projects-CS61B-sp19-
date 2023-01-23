import java.util.ArrayDeque;
import java.util.Deque;

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        while (wordDeque.size() > 1) {
            char first = wordDeque.removeFirst();
            char last = wordDeque.removeLast();
            if (first != last) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cmp) {
        Deque<Character> wordDeque = wordToDeque(word);
        while (wordDeque.size() > 1) {
            char first = wordDeque.removeFirst();
            char last = wordDeque.removeLast();
            if (!cmp.equalChars(first, last)) {
                return false;
            }
        }
        return true;
    }
}
