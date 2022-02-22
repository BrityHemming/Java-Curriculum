import java.util.Random;

public class Exercise07 {

    public static void main(String[] args) {
        int[] values = makeRandomArray();

        // The makeRandomArray method returns an int[] of a length between 50 and 150, whose elements are randomly
        // generated between -500 and 500.
        // 1. Create a loop to calculate the sum of elements in `values`.
        // 2. Print the result.
        // Since the array is random, your result will vary during each run.
        // 3. Print the length of the array.
    }

    public static int[] makeRandomArray() {
        Random random = new Random();
        int[] result = new int[random.nextInt(100) + 50];
        for (int i = 0; i < result.length; i++) {
            result[i] = random.nextInt(1000) - 500;
        }
        return result;
    }
}

