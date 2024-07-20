public class PalindromeChecker {

    public static boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }
        
        str = str.toLowerCase();
        str = str.replaceAll("[^a-z0-9]", ""); 
        
        int left = 0;
        int right = str.length() - 1;
        
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        String str1 = "A man, a plan, a canal, Panama";
        String str2 = "race a car";
        
        System.out.println("\"" + str1 + "\" is palindrome? " + isPalindrome(str1)); 
System.out.println("\"" + str2 + "\" is palindrome? " + isPalindrome(str2)); 
    }
}
