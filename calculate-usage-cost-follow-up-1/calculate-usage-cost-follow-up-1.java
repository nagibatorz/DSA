import java.util.*;

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
        List<Map<String, Object>> tiersList1 = new ArrayList<>();
        Map<String, Object> service1 = new HashMap<>();
        service1.put("service", "ec2.m5.large");
        List<Map<String, Object>> tiers1 = new ArrayList<>();
        tiers1.add(Map.of("minHours", 0, "maxHours", 2, "rate", 1000));

        Map<String, Object> tierWithNull = new HashMap<>();
        tierWithNull.put("minHours", 3);
        tierWithNull.put("maxHours", null);
        tierWithNull.put("rate", 900);
        tiers1.add(tierWithNull);
        service1.put("tiers", tiers1);
        tiersList1.add(service1);
        pricing1.put("us-east-1", tiersList1);

        int res1 = solution.calculateUsageCost(usage1, pricing1);
        System.out.println(res1); // Expected: 4700
    }

    public static void test2() {
        Solution solution = new Solution();

        Map<String, Object> usage2 = new HashMap<>();
        usage2.put("region", "ca-central-1");
        List<Map<String, Object>> services2 = new ArrayList<>();
        services2.add(Map.of("service", "db.t3.micro", "hours", 10));
        usage2.put("services", services2);

        Map<String, Object> pricing2 = new HashMap<>();
        List<Map<String, Object>> tiersList2 = new ArrayList<>();
        Map<String, Object> service2 = new HashMap<>();
        service2.put("service", "db.t3.micro");
        List<Map<String, Object>> tiers2 = new ArrayList<>();
        Map<String, Object> tierWithNull = new HashMap<>();
        tierWithNull.put("minHours", 0);
        tierWithNull.put("maxHours", null);
        tierWithNull.put("rate", 500);
        tiers2.add(tierWithNull);
        service2.put("tiers", tiers2);
        tiersList2.add(service2);
        pricing2.put("ca-central-1", tiersList2);

        int res2 = solution.calculateUsageCost(usage2, pricing2);
        System.out.println(res2); // Expected: 5000
    }

    public static void test3() {
        Solution solution = new Solution();

        Map<String, Object> usage3 = new HashMap<>();
        usage3.put("region", "us-east-1");
        List<Map<String, Object>> services3 = new ArrayList<>();
        services3.add(Map.of("service", "db.t3.micro", "hours", 25));
        services3.add(Map.of("service", "s3.standard", "hours", 8));
        usage3.put("services", services3);

        Map<String, Object> pricing3 = new HashMap<>();
        List<Map<String, Object>> ratesList3 = new ArrayList<>();

        Map<String, Object> dbService = new HashMap<>();
        dbService.put("service", "db.t3.micro");
        List<Map<String, Object>> dbTiers = new ArrayList<>();
        dbTiers.add(Map.of("minHours", 0, "maxHours", 9, "rate", 600));
        dbTiers.add(Map.of("minHours", 10, "maxHours", 19, "rate", 550));
        Map<String, Object> dbTierWithNull = new HashMap<>();
        dbTierWithNull.put("minHours", 20);
        dbTierWithNull.put("maxHours", null);
        dbTierWithNull.put("rate", 500);
        dbTiers.add(dbTierWithNull);
        dbService.put("tiers", dbTiers);
        ratesList3.add(dbService);

        Map<String, Object> s3Service = new HashMap<>();
        s3Service.put("service", "s3.standard");
        List<Map<String, Object>> s3Tiers = new ArrayList<>();
        s3Tiers.add(Map.of("minHours", 0, "maxHours", 4, "rate", 800));
        Map<String, Object> s3TierWithNull = new HashMap<>();
        s3TierWithNull.put("minHours", 5);
        s3TierWithNull.put("maxHours", null);
        s3TierWithNull.put("rate", 750);
        s3Tiers.add(s3TierWithNull);
        s3Service.put("tiers", s3Tiers);
        ratesList3.add(s3Service);

        pricing3.put("us-east-1", ratesList3);

        int res3 = solution.calculateUsageCost(usage3, pricing3);
        System.out.println(res3); // Expected: 20050
    }

    public static void test4() {
        Solution solution = new Solution();

        Map<String, Object> usage4 = new HashMap<>();
        usage4.put("region", "us-east-1");
        List<Map<String, Object>> services4 = new ArrayList<>();
        services4.add(Map.of("service", "ec2.m5.large", "hours", 0));
        usage4.put("services", services4);

        Map<String, Object> pricing4 = new HashMap<>();
        List<Map<String, Object>> tiersList4 = new ArrayList<>();
        Map<String, Object> service4 = new HashMap<>();
        service4.put("service", "ec2.m5.large");
        List<Map<String, Object>> tiers4 = new ArrayList<>();
        tiers4.add(Map.of("minHours", 0, "maxHours", 2, "rate", 1000));
        service4.put("tiers", tiers4);
        tiersList4.add(service4);
        pricing4.put("us-east-1", tiersList4);

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
        List<Map<String, Object>> tiersList5 = new ArrayList<>();
        Map<String, Object> service5 = new HashMap<>();
        service5.put("service", "ec2.m5.large");
        List<Map<String, Object>> tiers5 = new ArrayList<>();
        Map<String, Object> tierWithNull = new HashMap<>();
        tierWithNull.put("minHours", 0);
        tierWithNull.put("maxHours", null);
        tierWithNull.put("rate", 1000);
        tiers5.add(tierWithNull);
        service5.put("tiers", tiers5);
        tiersList5.add(service5);
        pricing5.put("us-east-1", tiersList5);

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
};
