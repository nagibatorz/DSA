import java.util.*;
import java.math.*;

class Solution {
    public int findMaxTradeShares(List<List<String>> orders) {
        // TODO: Implement findMaxTradeShares logic

        // maxHeap to always have the highest buy
        PriorityQueue<int[]> buy = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));

        // minHeap to always have the lowest sell
        PriorityQueue<int[]> sell = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        int total = 0;  // total number of shares executed
        for(List<String> order : orders){
            int price = Integer.parseInt(order.get(0));
            int qnt = Integer.parseInt(order.get(1));
            int initQnt = qnt; //keep track of how many shares were executed in the order
            String type = order.get(2);
            if(type.equals("buy")){
                // find lowest possible sell order
                while(qnt > 0 && !sell.isEmpty()){
                    int[] sellOrder = sell.peek();
                    int minSellPrice = sellOrder[0];
                    int minSellShares = sellOrder[1];

                    // valid sell order
                    if(minSellPrice <= price){
                        sell.poll();
                        if(minSellShares > qnt){
                            // push remaninding sell shares
                            sell.offer(new int[]{minSellPrice, minSellShares - qnt});
                            qnt = 0; // we bough all shared we needed
                        } else{
                            // execute the entire sell
                            qnt -= minSellShares;
                        }
                    } else{
                        break;
                    }
                }

                // add to buy if partial order
                if(qnt > 0){
                    buy.offer(new int[]{price, qnt});
                }
            } else{
                while(qnt > 0 && !buy.isEmpty()){
                    int[] buyOrder = buy.peek();
                    int maxBuyPrice = buyOrder[0];
                    int minBuyShares = buyOrder[1];
                    if(maxBuyPrice >= price){
                        buy.poll();
                        if(minBuyShares > qnt){
                            //push remaninding buy shares
                            buy.offer(new int[]{maxBuyPrice, minBuyShares - qnt});
                            qnt = 0;
                        } else{
                            // execute the entire buy
                            qnt -= minBuyShares;
                        }
                    } else{
                        break;
                    }
                }

                // add to sell if partial order
                if(qnt > 0){
                    sell.offer(new int[]{price, qnt});
                }
            }

            //increment how many total shares were executed after this order
            total += (initQnt -qnt);
        }
        return total;
     }
}
