// import java.util.*;
// import java.math.*;

// class Solution {
//     public List<List<String>> fractionInvent(List<List<String>> orders, List<List<String>> inventory) {
//         // TODO: Implement fractionInvent logic
//         Map<String, Integer> inv = new TreeMap<>();
//         for(List<String> share : inventory){
//             inv.put(share.get(0), Integer.parseInt(share.get(1)));
//         }

//         for(List<String> order : orders){
//             String symbol = order.get(0);
//             String type = order.get(1);
//             String quantity = order.get(2);
//             int price = Integer.parseInt(order.get(3));
//             if(type.equals("S")){
//                 if(quantity.contains("$")){
//                     int amount = Integer.parseInt(quantity.substring(1));
//                     int shares = (amount * 100) / price;
//                     if(inv.containsKey(symbol)){
//                         inv.put(symbol, (inv.get(symbol) + shares) % 100);
//                     } else{
//                         inv.put(symbol, shares % 100);
//                     }
//                 } else{
//                     int shares = Integer.parseInt(quantity);
//                     if(inv.containsKey(symbol)){
//                         inv.put(symbol, (inv.get(symbol) + shares) % 100);
//                     } else{
//                         inv.put(symbol, shares % 100);
//                     }
//                 }
//             } else{
//                 if(quantity.contains("$")){
//                     int amount = Integer.parseInt(quantity.substring(1));
//                     int shares = (amount * 100)  / price;
//                     if(inv.containsKey(symbol)){
//                         if(shares > inv.get(symbol)){
//                             int newShares = inv.get(symbol) + (shares - (shares % 100) + 100);
//                             inv.put(symbol, newShares % 100); 
//                         }
//                     } else{
//                         int newShares = (shares - (shares % 100) + 100);
//                         inv.put(symbol, newShares % 100);
//                     }
//                 } else{
//                     int shares = Integer.parseInt(quantity);
//                     if(inv.containsKey(symbol)){
//                         if(shares > inv.get(symbol)){
//                             int newShares = (inv.get(symbol) + (shares - (shares % 100) + 100)) - shares;
//                             inv.put(symbol, newShares % 100); 
//                         } else{
//                             inv.put(symbol, inv.get(symbol) - shares);
//                         }
//                     } else{
//                         int newShares = (shares - (shares % 100) + 100);
//                         inv.put(symbol, newShares % 100);
//                     }
//                 }
//             }
//         }
//         List<List<String>> res = new ArrayList<>();
//         for(String symbol : inv.keySet()){
//             int shares = inv.get(symbol);
//             res.add(new ArrayList<>(List.of(symbol, String.valueOf(shares))));
//         }
//         return res;
//      }
// }


import java.util.*;
import java.math.*;

class Solution {
    public List<List<String>> fractionInvent(List<List<String>> orders, List<List<String>> inventory) {
        // TODO: Implement fractionInvent logic

        // populate current inventory
        Map<String, Integer> inv = new TreeMap<>();
        for(List<String> share : inventory){
            inv.put(share.get(0), Integer.parseInt(share.get(1)));
        }

        for(List<String> order : orders){
            String symbol = order.get(0);
            String type = order.get(1);
            String quantity = order.get(2);
            int price = Integer.parseInt(order.get(3));
            
            // calculate number of shares we are working with
            int sharesToProcess = 0;
            if(quantity.contains("$")){
                sharesToProcess = (Integer.parseInt(quantity.substring(1)) * 100) / price;
            } else{
                sharesToProcess = Integer.parseInt(quantity);
            }
            
            // get number of shares currently in the inventory
            // if new symbol then we will have 0 here
            int currentShares = inv.getOrDefault(symbol, 0);

            // get order type
            if(type.equals("S")){
                currentShares += sharesToProcess;
            } else{
                currentShares -= sharesToProcess;
            }

            // handle negatives and flatten to fractional shares
            /*
            order = 500; curr = 50
            curr = 50 - 500
            |
            curr = -450 % 100 (get rid of whole shares) -> 
            -50 + 100 (handle negatives) -> 50 % 100 (handle positive whole shares) -> 50
            */
            currentShares = ((currentShares % 100) + 100) % 100;
            inv.put(symbol, currentShares);
        }


        List<List<String>> res = new ArrayList<>();
        for(String symbol : inv.keySet()){
            int shares = inv.get(symbol);
            res.add(new ArrayList<>(List.of(symbol, String.valueOf(shares))));
        }
        return res;
     }
}
