import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiceCombinations {

    private static final int totalSpots = 42;
    private static final int[] possibleDieFacesA = {0, 1, 2, 3, 4};
    private static final int[] possibleDieFacesB = {1, 2, 3, 4, 5, 6, 7, 8};
    private static final Map<Integer, Integer> originalSumProbabilities;

    static {
        originalSumProbabilities = new HashMap<>();
        originalSumProbabilities.put(2, 1);
        originalSumProbabilities.put(4, 3);
        originalSumProbabilities.put(5, 4);
        originalSumProbabilities.put(6, 5);
        originalSumProbabilities.put(7, 6);
        originalSumProbabilities.put(9, 4);
        originalSumProbabilities.put(3, 2);
        originalSumProbabilities.put(8, 5);
        originalSumProbabilities.put(10, 3);
        originalSumProbabilities.put(11, 2);
        originalSumProbabilities.put(12, 1);
    }

    public static void main(String[] args) {
        int[] dieA = {1, 2, 3, 4, 5, 6};
        int[] dieB = {1, 2, 3, 4, 5, 6};
        undoomDice(dieA, dieB);
    }

    private static void dieACombinations(List<Integer> currentDie, List<List<Integer>> validCombosDieA) {
        if (currentDie.size() == 6) {
            validCombosDieA.add(new ArrayList<>(currentDie));
            return;
        }

        for (int face : possibleDieFacesA) {
            currentDie.add(face);
            dieACombinations(currentDie, validCombosDieA);
            currentDie.remove(currentDie.size() - 1);
        }
    }

    private static void dieBCombinations(int previousFaceIndex, List<Integer> currentDie,
                                         int currentSpotCount, List<List<Integer>> validCombosDieB) {
        if (currentSpotCount > totalSpots) {
            return;
        }

        if (currentDie.size() == 6) {
            if (currentSpotCount == totalSpots) {
                validCombosDieB.add(new ArrayList<>(currentDie));
            }
            return;
        }

        for (int i = previousFaceIndex + 1; i < possibleDieFacesB.length; i++) {
            currentDie.add(possibleDieFacesB[i]);
            dieBCombinations(i, currentDie, currentSpotCount + possibleDieFacesB[i], validCombosDieB);
            currentDie.remove(currentDie.size() - 1);
        }
    }

    private static Map<Integer, Integer> calculateDiceProbabilities(List<Integer> dieA, List<Integer> dieB) {
        Map<Integer, Integer> prob = new HashMap<>();
        for (int dieFaceA : dieA) {
            for (int dieFaceB : dieB) {
                int sum = dieFaceA + dieFaceB;
                prob.put(sum, prob.getOrDefault(sum, 0) + 1);
            }
        }
        return prob;
    }

    private static boolean isValidDice(List<Integer> dieA, List<Integer> dieB) {
        Map<Integer, Integer> newProbabilities = calculateDiceProbabilities(dieA, dieB);
        return newProbabilities.equals(originalSumProbabilities);
    }

    private static void undoomDice(int[] dieA, int[] dieB) {
        List<List<Integer>> validCombosDieA = new ArrayList<>();
        dieACombinations(new ArrayList<>(), validCombosDieA);

        for (List<Integer> dieCombinationA : validCombosDieA) {
            List<List<Integer>> validCombosDieB = new ArrayList<>();
            dieBCombinations(-1, new ArrayList<>(), sumList(dieCombinationA), validCombosDieB);
            for (List<Integer> dieCombinationB : validCombosDieB) {
                if (isValidDice(dieCombinationA, dieCombinationB)) {
                    System.out.println("New_Die_A = " + dieCombinationA);
                    System.out.println("New_Die_B = " + dieCombinationB);
                    return;
                }
            }
        }

        System.out.println("No valid dice combination exists");
    }

    private static int sumList(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).sum();
    }
}