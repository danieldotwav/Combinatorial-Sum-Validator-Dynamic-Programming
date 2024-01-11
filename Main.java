// Prompt: Write a function 'canSum(targetSum, numbers)' that takes in a targetSum and an array of numbers as arguments.
//         The function should return a boolean indicating whether or not it is possible to generate the targetSum using numbers from the array.
//         You may use an element of the array as many times as needed.
//         You may assume that all input numbers are nonnegative.
//         Negative target sums are permitted but should return false.

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // Test Case with Empty Array
        System.out.println("\nArray 1: []\nTarget: 7\nExpected Result: false\nActual Result: " + canSum(7, new int[] {}));

        // Test Case with Zero Target
        System.out.println("\nArray 2: [1, 2, 3]\nTarget: 0\nExpected Result: true\nActual Result: " + canSum(0, new int[] {1, 2, 3}));

        // Test Case with All Elements Greater than Target
        System.out.println("\nArray 3: [8, 9, 10]\nTarget: 7\nExpected Result: false\nActual Result: " + canSum(7, new int[] {8, 9, 10}));

        // Test Case with Exact Match in Array
        System.out.println("\nArray 4: [1, 2, 7, 4]\nTarget: 7\nExpected Result: true\nActual Result: " + canSum(7, new int[] {1, 2, 7, 4}));

        // Test Case with All Large Numbers
        System.out.println("\nArray 5: [1000, 2000, 3000]\nTarget: 5000\nExpected Result: true\nActual Result: " + canSum(5000, new int[] {1000, 2000, 3000}));

        // Test Case with Duplicates in Array
        System.out.println("\nArray 6: [1, 2, 2, 3]\nTarget: 4\nExpected Result: true\nActual Result: " + canSum(4, new int[] {1, 2, 2, 3}));

        // Test Case with All Negative Target
        System.out.println("\nArray 7: [1, 2, 3]\nTarget: -5\nExpected Result: false\nActual Result: " + canSum(-5, new int[] {1, 2, 3}));

        // Test Case with Large Tree -> here we begin using memoization for duplicate subtrees
        HashMap<Integer, Boolean> memoizationCache = new HashMap<Integer, Boolean>();
        System.out.println("\nArray 8: [7, 14]\nTarget: 300\nExpected Result: false\nActual Result: " + improvedCanSum(300, new int[] {7, 14}, memoizationCache));
    }

    // Recursive method without dynamic programming
    static boolean canSum(int targetSum, int[] numbers) {
        // Base cases
        if (targetSum == 0) {
            return true;
        }

        if (targetSum < 0) {
            return false;
        }

        // Loop through each element in the array
        for (int element : numbers) {
            // Calculate the updated sum by subtracting the targetSum by each element
            int updatedSum = targetSum - element;

            // If the sum is greater than 0, pass the updatedSum to the next recursive call
            if (canSum(updatedSum, numbers) == true) {
                return true;
            }
        }

        // Attempt all possibilities before returning false
        return false;
    }

    // Recursive method with memoization
    static boolean improvedCanSum(int targetSum, int[] numbers, HashMap<Integer, Boolean> memoizationCache) {
        // Base cases
        if (memoizationCache.containsKey(targetSum)) {
            return memoizationCache.get(targetSum);
        }
        if (targetSum == 0) {
            return true;
        }
        if (targetSum < 0) {
            return false;
        }

        for (int element : numbers) {
            // Calculate the updated sum by subtracting the targetSum by each element
            int updatedSum = targetSum - element;

            // If the sum is greater than 0, pass the updatedSum to the next recursive call
            if (improvedCanSum(updatedSum, numbers, memoizationCache) == true) {
                memoizationCache.put(targetSum, true);
                return true;
            }
        }

        // Attempt all possibilities before returning false
        memoizationCache.put(targetSum, false);
        return false;
    }
}
