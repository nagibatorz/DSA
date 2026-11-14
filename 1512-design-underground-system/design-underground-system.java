class UndergroundSystem {

    Map<Integer, CheckIn> checkInMap;
    Map<String, List<Double>> averages;

    public UndergroundSystem(){
        this.checkInMap = new HashMap<>();
        this.averages = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t){
        if(!checkInMap.containsKey(id)){
            checkInMap.put(id, new CheckIn((double)t, stationName));
        }
    }

    public void checkOut(int id, String stationName, int t){
        if(checkInMap.containsKey(id)){
            CheckIn checkIn = checkInMap.get(id);
            String key = checkIn.stationName + "=" + stationName;
            double travelTime = (double) t - checkIn.t;
            averages.computeIfAbsent(key, k -> new ArrayList<>()).add(travelTime);
            checkInMap.remove(id);
        }

    }

    public double getAverageTime(String startStation, String endStation){
        String key = startStation + "=" + endStation;
        if(!averages.containsKey(key)) return 0.0;
        List<Double> travelTimes = averages.get(key);
        double sum = 0;
        for(Double time : travelTimes){
            sum += time;
        }
        return sum / (double) travelTimes.size();
    }

    class CheckIn{
        double t;
        String stationName;

        public CheckIn(double t, String stationName){
            this.t = t;
            this.stationName = stationName;
        }

    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */