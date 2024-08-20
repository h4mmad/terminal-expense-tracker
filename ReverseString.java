public class ReverseString {
    public static void main(String[] args) {
        
        String string = "test";
        String reversedString = reverse(string);
        System.out.println("Reversed string: " + reversedString);
    }

    // Method to reverse a string
    public static String reverse(String str) {
        String reversed = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed += str.charAt(i);
        }
        return reversed;
    }
}
