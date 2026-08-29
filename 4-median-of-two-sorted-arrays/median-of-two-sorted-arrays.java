class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Always run binary search on shorter array
        if(nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);

        int n = nums1.length;
        int m = nums2.length;
        int l = 0, r = n;
        // size of the partition
        int halfSize = (n + m + 1) / 2;

        while(l <= r){
            //partition pointer
            int partitionX = l + (r - l) / 2;
            //how many elements we need from Y
            int partitionY = halfSize - partitionX;

            // assign pointers
            // edge cases: if we take all or none elements from either X or Y
            // when partition is not zero we can sti
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == n) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == m) ? Integer.MAX_VALUE : nums2[partitionY];

            if(maxLeftX <= minRightY && maxLeftY <= minRightX){ //cross compare borders of the partition
                if((n + m) % 2 == 0){ //even length case
                    return (double) (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0;
                } else{ //odd length case
                    return (double) Math.max(maxLeftY, maxLeftX);
                }
            } else if(maxLeftX > minRightY){ //we are too far right -> shift left
                r = partitionX - 1;
            } else{ //we are too far left -> shift right
                l = partitionX + 1;
            }
        }
        return -1.0;
    }
}