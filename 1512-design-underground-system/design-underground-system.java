class UndergroundSystem {

    Map<Integer, CheckIn> checkInMap;
    Map<String, Total> averages;

    public UndergroundSystem(){
        this.checkInMap = new HashMap<>();
        this.averages = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t){
        if(!checkInMap.containsKey(id)){
            checkInMap.put(id, new CheckIn(t, stationName));
        }
    }

    public void checkOut(int id, String stationName, int t){
        if(checkInMap.containsKey(id)){
            CheckIn checkIn = checkInMap.get(id);
            String key = checkIn.stationName + "=" + stationName;
            int travelTime = t - checkIn.t;
            if(averages.containsKey(key)){
                Total currTotal = averages.get(key);
                currTotal.totalTime+=travelTime;
                currTotal.trips+=1;
            }else{
                Total currTotal = new Total(travelTime, 1);
                averages.put(key, currTotal);
            }
            checkInMap.remove(id);
        }

    }

    public double getAverageTime(String startStation, String endStation){
        String key = startStation + "=" + endStation;
        if(!averages.containsKey(key)) return 0.0;
        Total tripTotal = averages.get(key);
        return ((double)tripTotal.totalTime / tripTotal.trips);
    }

    class CheckIn{
        int t;
        String stationName;

        public CheckIn(int t, String stationName){
            this.t = t;
            this.stationName = stationName;
        }
    }

    class Total{
        long totalTime;
        int trips;

        public Total(int totalTime, int trips){
            this.totalTime = totalTime;
            this.trips = trips;
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