// Prompt: Write a function 'canSum(targetSum, numbers)' that takes in a targetSum and an array of numbers as arguments.
//         The function should return a boolean indicating whether or not it is possible to generate the targetSum using numbers from the array.
//         You may use an element of the array as many times as needed.
//         You may assume that all input numbers are nonnegative.
//         Negative target sums are permitted but should return false.

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // Test Case with Empty Array
        System.out.println("\nArray 1: []\nTarget: 7\nExpected Result: false\nActual Result: " + improvedCanSum(7, new int[] {}, new HashMap<Integer, Boolean>()));

        // Test Case with Zero Target
        System.out.println("\nArray 2: [1, 2, 3]\nTarget: 0\nExpected Result: true\nActual Result: " + improvedCanSum(0, new int[] {1, 2, 3}, new HashMap<Integer, Boolean>()));

        // Test Case with All Elements Greater than Target
        System.out.println("\nArray 3: [8, 9, 10]\nTarget: 7\nExpected Result: false\nActual Result: " + improvedCanSum(7, new int[] {8, 9, 10}, new HashMap<Integer, Boolean>()));

        // Test Case with Exact Match in Array
        System.out.println("\nArray 4: [1, 2, 7, 4]\nTarget: 7\nExpected Result: true\nActual Result: " + improvedCanSum(7, new int[] {1, 2, 7, 4}, new HashMap<Integer, Boolean>()));

        // Test Case with All Large Numbers
        System.out.println("\nArray 5: [1000, 2000, 3000]\nTarget: 5000\nExpected Result: true\nActual Result: " + improvedCanSum(5000, new int[] {1000, 2000, 3000}, new HashMap<Integer, Boolean>()));

        // Test Case with Duplicates in Array
        System.out.println("\nArray 6: [1, 2, 2, 3]\nTarget: 4\nExpected Result: true\nActual Result: " + improvedCanSum(4, new int[] {1, 2, 2, 3}, new HashMap<Integer, Boolean>()));

        // Test Case with All Negative Target
        System.out.println("\nArray 7: [1, 2, 3]\nTarget: -5\nExpected Result: false\nActual Result: " + improvedCanSum(-5, new int[] {1, 2, 3}, new HashMap<Integer, Boolean>()));

        // Test Case with Large Tree
        System.out.println("\nArray 8: [7, 14]\nTarget: 300\nExpected Result: false\nActual Result: " + improvedCanSum(300, new int[] {7, 14}, new HashMap<Integer, Boolean>()));
    
        // Large Array, Large Target Sum
        System.out.println("\nArray 9: [2, 3, 5, 7, 18, 15, 19, 17, 33]\nTarget: 10173\nExpected Result: true\nActual Result: " + improvedCanSum(10173, new int[] {2, 3, 5, 7, 18, 15, 19, 17, 33}, new HashMap<Integer, Boolean>()));
    }

    // Simple recursive method
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
