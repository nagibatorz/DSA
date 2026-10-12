class UndergroundSystem {

    private Map<Integer, Entry> checkIn; // id -> (startStation, startTime)
    private Map<String, RouteTotal> totalTime; // (startStation, endStation) -> (totaltime, count of times)

    public UndergroundSystem() {
        checkIn = new HashMap<>();
        totalTime = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        Entry entry = new Entry(stationName, t);
        checkIn.put(id, entry);
    }
    
    public void checkOut(int id, String stationName, int t) {

        // get data about the starting point and clear out check in map since customer checked out
        Entry startPoint = checkIn.remove(id);

        // create data for route entry
        String route = startPoint.station + "->" + stationName;
        int timeTraveled = t - startPoint.time;

        // update route total info
        RouteTotal stats = totalTime.computeIfAbsent(route, k -> new RouteTotal(0, 0));
        stats.totalTime += timeTraveled;
        stats.totalTravels += 1;
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String routeKey = startStation + "->" + endStation;
        RouteTotal route = totalTime.get(routeKey);
        return (double) route.totalTime / route.totalTravels;
    }


    class Entry{
       String station;
       int time;

       public Entry(String stationName, int time){
            this.station = stationName;
            this.time = time;
       } 
    }

    class RouteTotal{
       long totalTime;
       int totalTravels;

       public RouteTotal(long totalTime, int totalTravels){
            this.totalTime = totalTime;
            this.totalTravels = totalTravels;
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