import java.util.*;

// Only the Tier class and the inner loop need to change to reflect the new differences while computing cost
public class Solution {
    public int calculateUsageCost(Map<String, Object> usageReport, Map<String, Object> pricingTable) {
        // TODO: Implement calculateUsageCost logic.
        String spendRegion = String.valueOf(usageReport.get("region"));
        if(!pricingTable.containsKey(spendRegion)) return -1;
        // Service -> list of tiers
        Map<String, List<Tier>> costs = new HashMap<>();
        List<Map<String, Object>> currRegionCosts =  (List<Map<String, Object>>) pricingTable.get(spendRegion);
        for(Map<String, Object> entry : currRegionCosts){
            String servString = String.valueOf(entry.get("service"));
            List<Tier> tiers = new ArrayList<>();
            List<Map<String, Object>> rawTiers = (List<Map<String, Object>>) entry.get("tiers");
            for(Map<String, Object> tier : rawTiers){
                int start = (int) tier.get("minHours");
                int rate = (int) tier.get("rate");
                int end = Integer.MAX_VALUE;
                if(tier.get("maxHours") != null){
                    end = (int) tier.get("maxHours");
                }
                tiers.add(new Tier(start, end, rate));
            }
            costs.put(servString, tiers);
            
            
        }

        int runningTotal = 0;
        // 2. Add a variable name (e.g., 'serviceEntry') for the loop
        
        List<Map<String, Object>> reports = (List<Map<String, Object>>) usageReport.get("services");
        for (Map<String, Object> serviceReport : reports) {
            String name = String.valueOf(serviceReport.get("service"));
            int hours = (int) serviceReport.get("hours");
            int hoursInit = hours;
            if(!costs.containsKey(name)) return -1;
            int perService = 0;
            for(Tier tier : costs.get(name)){
                int tierHours = (tier.maxHours == Integer.MAX_VALUE)
                    ? Integer.MAX_VALUE
                    : tier.maxHours - tier.minHours;
                if(hours <= tierHours){
                    perService += hours * tier.rate;
                    hours = 0;
                    break;
                } else{
                    hours -= tierHours;
                    perService += tierHours * tier.rate;
                }
            }
            runningTotal += perService;
        }
        return runningTotal;
    }


    public class Tier{
        int minHours;
        int maxHours;
        int rate;

        public Tier(int minHours, int maxHours, int rate){
            this.minHours = minHours;
            this.maxHours = maxHours;
            this.rate = rate;
        }
    }

    public static void test1() {
        Solution solution = new Solution();

        Map<String, Object> usage1 = new HashMap<>();
        usage1.put("region", "us-east-1");
        List<Map<String, Object>> services1 = new ArrayList<>();
        services1.add(Map.of("service", "ec2.m5.large", "hours", 5));
        usage1.put("services", services1);

        Map<String, Object> pricing1 = new HashMap<>();
        List<Map<String, Object>> regionPricing1 = new ArrayList<>();
        Map<String, Object> tieredService = new HashMap<>();
        tieredService.put("service", "ec2.m5.large");
        List<Map<String, Object>> tiers = new ArrayList<>();
        tiers.add(Map.of("type", "fixed", "minHours", 0, "maxHours", 2, "rate", 1000));
        Map<String, Object> incTier = new HashMap<>();
        incTier.put("type", "incremental");
        incTier.put("minHours", 3);
        incTier.put("maxHours", null);
        incTier.put("rate", 900);
        tiers.add(incTier);
        tieredService.put("tiers", tiers);
        regionPricing1.add(tieredService);
        pricing1.put("us-east-1", regionPricing1);

        int res1 = solution.calculateUsageCost(usage1, pricing1);
        System.out.println(res1); // Expected: 3700
    }

    public static void test2() {
        Solution solution = new Solution();

        Map<String, Object> usage2 = new HashMap<>();
        usage2.put("region", "ca-central-1");
        List<Map<String, Object>> services2 = new ArrayList<>();
        services2.add(Map.of("service", "db.t3.micro", "hours", 10));
        usage2.put("services", services2);

        Map<String, Object> pricing2 = new HashMap<>();
        List<Map<String, Object>> regionPricing2 = new ArrayList<>();
        Map<String, Object> tieredService = new HashMap<>();
        tieredService.put("service", "db.t3.micro");
        List<Map<String, Object>> tiers = new ArrayList<>();
        Map<String, Object> incTier = new HashMap<>();
        incTier.put("type", "incremental");
        incTier.put("minHours", 0);
        incTier.put("maxHours", null);
        incTier.put("rate", 500);
        tiers.add(incTier);
        tieredService.put("tiers", tiers);
        regionPricing2.add(tieredService);
        pricing2.put("ca-central-1", regionPricing2);

        int res2 = solution.calculateUsageCost(usage2, pricing2);
        System.out.println(res2); // Expected: 5000
    }

    public static void test3() {
        Solution solution = new Solution();

        Map<String, Object> usage3 = new HashMap<>();
        usage3.put("region", "us-east-1");
        List<Map<String, Object>> services3 = new ArrayList<>();
        services3.add(Map.of("service", "s3.standard", "hours", 15));
        usage3.put("services", services3);

        Map<String, Object> pricing3 = new HashMap<>();
        List<Map<String, Object>> regionPricing3 = new ArrayList<>();
        Map<String, Object> tieredService = new HashMap<>();
        tieredService.put("service", "s3.standard");
        List<Map<String, Object>> tiers = new ArrayList<>();
        tiers.add(Map.of("type", "fixed", "minHours", 0, "maxHours", 4, "rate", 2000));
        tiers.add(Map.of("type", "fixed", "minHours", 5, "maxHours", 9, "rate", 1500));
        Map<String, Object> incTier = new HashMap<>();
        incTier.put("type", "incremental");
        incTier.put("minHours", 10);
        incTier.put("maxHours", null);
        incTier.put("rate", 800);
        tiers.add(incTier);
        tieredService.put("tiers", tiers);
        regionPricing3.add(tieredService);
        pricing3.put("us-east-1", regionPricing3);

        int res3 = solution.calculateUsageCost(usage3, pricing3);
        System.out.println(res3); // Expected: 9100
    }

    public static void test4() {
        Solution solution = new Solution();

        Map<String, Object> usage4 = new HashMap<>();
        usage4.put("region", "us-east-1");
        List<Map<String, Object>> services4 = new ArrayList<>();
        services4.add(Map.of("service", "ec2.m5.large", "hours", 0));
        usage4.put("services", services4);

        Map<String, Object> pricing4 = new HashMap<>();
        List<Map<String, Object>> regionPricing4 = new ArrayList<>();
        Map<String, Object> tieredService = new HashMap<>();
        tieredService.put("service", "ec2.m5.large");
        List<Map<String, Object>> tiers = new ArrayList<>();
        tiers.add(Map.of("type", "fixed", "minHours", 0, "maxHours", 2, "rate", 1000));
        tieredService.put("tiers", tiers);
        regionPricing4.add(tieredService);
        pricing4.put("us-east-1", regionPricing4);

        int res4 = solution.calculateUsageCost(usage4, pricing4);
        System.out.println(res4); // Expected: 0
    }

    public static void test5() {
        Solution solution = new Solution();

        Map<String, Object> usage5 = new HashMap<>();
        usage5.put("region", "eu-central-1");
        List<Map<String, Object>> services5 = new ArrayList<>();
        services5.add(Map.of("service", "ec2.m5.large", "hours", 5));
        usage5.put("services", services5);

        Map<String, Object> pricing5 = new HashMap<>();
        List<Map<String, Object>> regionPricing5 = new ArrayList<>();
        Map<String, Object> tieredService = new HashMap<>();
        tieredService.put("service", "ec2.m5.large");
        List<Map<String, Object>> tiers = new ArrayList<>();
        Map<String, Object> incTier = new HashMap<>();
        incTier.put("type", "incremental");
        incTier.put("minHours", 0);
        incTier.put("maxHours", null);
        incTier.put("rate", 1000);
        tiers.add(incTier);
        tieredService.put("tiers", tiers);
        regionPricing5.add(tieredService);
        pricing5.put("us-east-1", regionPricing5);

        int res5 = solution.calculateUsageCost(usage5, pricing5);
        System.out.println(res5); // Expected: -1
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }
}
