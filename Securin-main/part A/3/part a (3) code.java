public class Main {
    public static void main(String[] args) {
        int total_combo = 6 * 6;
        int[] p = new int[11];
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) {
                int v = i + j;
                p[v - 2]++;
            }
        }
        System.out.println("Probability of all possible sums:");
        for (int i = 2; i <= 12; i++) {
            int index = i - 2;
            double prob = (double) p[index] / total_combo;
            System.out.printf("P(Sum = %d) = %.4f%n", i, (prob == 0) ? 1.0 / total_combo : prob);
        }
    }
}
