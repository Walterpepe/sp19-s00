public class Palindrome {
    // Given a String, wordToDeque should return a Deque where
    // the characters appear in the same order as in the String.
    // For example, if the word is “persiflage”,
    // then the returned Deque should have ‘p’ at the front,
    // followed by ‘e’, and so forth.
    public Deque<Character> wordToDeque(String word) {

        Deque<Character> lld = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            lld.addLast(word.charAt(i));
        }

        return lld;
    }

    // The isPalindrome method should return true if the given
    // word is a palindrome, and false otherwise.
    // A palindrome is defined as a word that is the same whether
    // it is read forwards or backwards. For example “a”,
    // “racecar”, and “noon” are all palindromes. “horse”,
    // “rancor”, and “aaaaab” are not palindromes.
    // Any word of length 1 or 0 is a palindrome.

    //Tip: Consider recursion. There’s a really beautiful solution
    // that uses recursion. You’ll need to create a private helper
    // method for this to work.
    public boolean isPalindrome(String word) {
        return isPalindrome(wordToDeque(word));
    }

    public boolean isPalindrome(Deque d) {

        if (d.size() == 0 || d.size() == 1) {
            return true;
        }

        if (d.removeFirst() != d.removeLast()) {
            return false;
        }

        return isPalindrome(d);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindromeCC(wordToDeque(word), cc);
    }

    public boolean isPalindromeCC(Deque d, CharacterComparator cc) {

        if (d.size() == 0 || d.size() == 1) {
            return true;
        }

        if (! cc.equalChars((char)d.removeFirst(),(char)d.removeLast()) ) {
            return false;
        }

        return isPalindromeCC(d, cc);
    }


// 这个方法不用
//    public boolean isPalindrome(String word){
//
//        for (int i =0; i<word.length(); i++){
//            if (word.charAt(i) != word.charAt(word.length()-i-1)){
//                return false;
//            }
//        }
//
//        return true;
//    }


}
