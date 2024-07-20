public class KthLargestElement {

    public static int findKthLargest(int[] nums, int k) {
        // Adjust k to 0-based index
        int targetIndex = nums.length - k;
        return quickselect(nums, 0, nums.length - 1, targetIndex);
    }

    private static int quickselect(int[] nums, int left, int right, int k) {
        // Perform partition using Hoare's partitioning scheme
        int pivotIndex = partition(nums, left, right);

        if (pivotIndex == k) {
            return nums[pivotIndex];
        } else if (pivotIndex < k) {
            return quickselect(nums, pivotIndex + 1, right, k);
        } else {
            return quickselect(nums, left, pivotIndex - 1, k);
        }
    }

    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[left]; // Choose first element as pivot
        int i = left;
        int j = right;

        while (i <= j) {
            while (nums[i] < pivot) {
                i++;
            }
            while (nums[j] > pivot) {
                j--;
            }

            if (i <= j) {
                // Swap nums[i] and nums[j]
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }

        return i - 1;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 3;
        System.out.println(findKthLargest(nums, k)); // Output: 4
    }
}