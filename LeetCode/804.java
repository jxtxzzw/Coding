class Solution {
    
    private String[] morse = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
    
    private String transform(String word) {
        StringBuilder sb = new StringBuilder();
        for (char c: word.toLowerCase().toCharArray()) {
            sb.append(morse[c - 'a']);
        }
        return sb.toString();
    }
    
    private HashSet<String> set = new HashSet<>();
    
    
    public int uniqueMorseRepresentations(String[] words) {
        for (String word: words) {
            set.add(transform(word));
        }
        return set.size();
    }
}
