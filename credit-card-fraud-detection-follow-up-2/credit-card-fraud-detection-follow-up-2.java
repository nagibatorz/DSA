import java.util.*;
import java.math.*;

class Solution {

    class Request implements Comparable<Request>{
        int timestampSeconds;
        String cardNum;
        String amount;
        String id;
        String merchant;

        public Request(int timestampSeconds, String cardNum, String amount, String id, String merchant){
            this.timestampSeconds = timestampSeconds;
            this.cardNum = cardNum;
            this.amount = amount;
            this.id = id;
            this.merchant = merchant;
        }

        @Override
        public int compareTo(Request other){
            return Integer.compare(this.timestampSeconds, other.timestampSeconds);
        }
    }

    public double calculateFraudLoss(List<String> requests, List<String> fraudRules) {
        // TODO: Implement generateReport logic
        List<Request> approved = new ArrayList<>();
        for(String request : requests){
            String[] elements = request.split(",");
            int timestampSeconds = Integer.parseInt(elements[0]);
            String id = elements[1];
            String amount = elements[2];
            String card = elements[3];
            String merchant = elements[4];
            approved.add(new Request(timestampSeconds, card, amount, id, merchant));
        }

        Map<String, Integer> ruleValToMinTime = new HashMap<>();
        for(String rule : fraudRules){
            String[] elements = rule.split(",");
            int start = Integer.parseInt(elements[0]);
            String fraud = elements[1]+":"+elements[2];
            if(!ruleValToMinTime.containsKey(fraud) || ruleValToMinTime.get(fraud) > start){
                ruleValToMinTime.put(fraud, start);
            }
        }

        double fraudLoss = 0.0;
        for(Request r : approved){
            int timeStamp = r.timestampSeconds;
            String cardKey = "card_number:"+r.cardNum;
            String merchantKey = "merchant:"+r.merchant;
            double amount = Double.parseDouble(r.amount);
            if((ruleValToMinTime.containsKey(cardKey) && ruleValToMinTime.get(cardKey) > timeStamp) 
                || (ruleValToMinTime.containsKey(merchantKey) && ruleValToMinTime.get(merchantKey) > timeStamp)){
                    fraudLoss += amount;
                }
        }
        return fraudLoss;
    }
}
