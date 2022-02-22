import java.util.Scanner;

public class Exercise01 {

    public static void main(String[] args) {
        // 1. Run the code and press [Enter] without typing a value.
        // What happens?
        Scanner console = new Scanner(System.in);
        System.out.print("Enter a value: ");
        String value = console.nextLine();

        // 2. Change the if condition to check for a string length greater than 0.
        if (value.charAt(0) == '1') {
            // 3. Replace the current message with the value variable.
            // System.out.println(value);
            System.out.println("Value starts with the number 1.");
        }
    }
}
