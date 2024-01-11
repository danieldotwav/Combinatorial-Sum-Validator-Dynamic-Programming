## Introduction

This project provides a Java implementation to solve the "CanSum" problem: determining if it is possible to generate a given target sum using any combination of numbers from a given array. The elements in the array can be used multiple times. The project includes two methods: `canSum` and `improvedCanSum`. The former is a straightforward recursive solution, while the latter introduces memoization for improved efficiency.

## Algorithms

**1. Simple Recursive Method (`canSum`):**

**Logic:**
- The function `canSum` takes a target sum and an array of numbers.
- It recursively tries to find if any combination of numbers in the array can sum up to the target sum.
- Base cases are when the target sum is 0 (return true) or less than 0 (return false).

**Time Complexity:** O(n^m), where `n` is the length of the numbers array and `m` is the target sum. This is due to the recursive nature, which explores all possible combinations.

**Space Complexity:** O(m), due to the depth of the recursion stack.

**2. Dynamic Programming with Memoization (`improvedCanSum`):**

**Logic:**
- The `improvedCanSum` function also takes a target sum, an array of numbers, and an additional `HashMap` for memoization (`memoizationCache`).
- It uses the same recursive approach as `canSum` but checks the `memoizationCache` before each recursive call. If the current target sum has been computed before, it returns the stored result.
- This significantly reduces the number of calculations by storing and reusing the results of subproblems.

**Time Complexity:** O(n*m), where `n` is the length of the numbers array and `m` is the target sum. Memoization ensures each subproblem is only solved once.

**Space Complexity:** O(m), mainly due to the recursion stack and additional space for the memoization cache.

### Code Snippet

```java
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
```

## Usage and Examples

To use these methods, simply create an instance of the class containing them and call the methods with your desired target sum and array of numbers. Here are some examples:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("CanSum 7 with [2, 3]: " + canSum(7, new int[]{2, 3})); // false
        System.out.println("ImprovedCanSum 7 with [2, 3]: " + improvedCanSum(7, new int[]{2, 3}, new HashMap<>())); // false
    }
}
```
