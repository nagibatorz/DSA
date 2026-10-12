import java.util.Map;
import java.util.HashMap;

class UndergroundSystem {
    private record CheckIn(String station, int time) {}

    private class RouteTotal {
        long totalTime = 0;
        int count = 0;
    }

    private Map<Integer, CheckIn> checkIns;
    private Map<String, Map<String, RouteTotal>> routeStats;

    public UndergroundSystem() {
        checkIns = new HashMap<>();
        routeStats = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        checkIns.put(id, new CheckIn(stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t) {
        CheckIn startPoint = checkIns.remove(id);

        // Fetch inner map for the start station, creating it if it doesn't exist
        Map<String, RouteTotal> endStationMap = routeStats.computeIfAbsent(
            startPoint.station(), k -> new HashMap<>()
        );
        
        // Fetch or create the RouteTotal for the end station
        RouteTotal stats = endStationMap.computeIfAbsent(
            stationName, k -> new RouteTotal()
        );
        
        stats.totalTime += (t - startPoint.time());
        stats.count += 1;
    }
    
    public double getAverageTime(String startStation, String endStation) {
        RouteTotal stats = routeStats.get(startStation).get(endStation);
        return (double) stats.totalTime / stats.count;
    }
}