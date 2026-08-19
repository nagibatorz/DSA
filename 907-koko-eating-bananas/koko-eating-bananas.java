class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        if(piles.length > h) return -1; // check if not guaranteed
        int max = 0;
        for(int num : piles){
            max = Math.max(num, max);
        }
        int l = 1, r = max;
        int min = Integer.MAX_VALUE;
        while(l <= r){
            int mid = l + (r - l) / 2;
            //Since h can be up to and the number of piles can be large we can overflow so we use long
            long hoursTotal = 0;

            for(int i = 0; i < piles.length; i++){
                // If the pile has less than k bananas, finish eating but not eat from another pile in the same hour
                hoursTotal += (piles[i] + mid - 1) / mid; //standard integer ceiling division
                if (hoursTotal > h) break;
            }
            if(hoursTotal <= h){
                min = mid;
                r = mid - 1;
            } else{
                l = mid + 1;
            }
        }
        return min;
    }
}