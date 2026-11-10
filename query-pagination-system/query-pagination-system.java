import java.util.*;

import java.math.*;

class QueryPaginationSystem {
    private List<List<String>> records;
    private List<List<String>> filtered;
    private Integer startTime = null;
    private Integer endTime = null;
    private Integer rangeStart = null;
    private Integer rangeEnd = null;
    private String userId = null;
    private String currency = null;
    private Integer size = Integer.MAX_VALUE;
    private boolean isSorted = false;
    private Integer currenIdx = 0;

    public QueryPaginationSystem(List<List<String>> records) {
        // TODO: Initialize QueryPaginationSystem
        this.records = new ArrayList<>(records);
        applyFilters();
    }

    public void setPageSize(int size) {
        // TODO: Implement setPageSize logic
        this.size = size;
        this.currenIdx = 0;


     }

    public void setTimeRange(int start, int end) {
        // TODO: Implement setTimeRange logic
        this.startTime = start;
        this.endTime = end;
        applyFilters();
     }

    public void setAmountRange(int start, int end) {
        // TODO: Implement setAmountRange logic
        this.rangeStart = start;
        this.rangeEnd = end;
        applyFilters();
     }

    public void setUserId(String id) {
        // TODO: Implement setUserId logic
        this.userId = id;
        applyFilters();

     }

    public void setCurrency(String currency) {
        // TODO: Implement setCurrency logic
        this.currency = currency;
        applyFilters();

     }

    public List<List<String>> nextPage() {
        // TODO: Implement nextPage logic
        if(!isSorted){
            this.filtered.sort((a, b) -> Integer.parseInt(a.get(0)) - Integer.parseInt(b.get(0)));
            this.isSorted = true;
        }
        List<List<String>> page = new ArrayList<>();
        for(int i = currenIdx; i < Math.min(size + currenIdx, filtered.size()); i++){
            page.add(filtered.get(i));
        }
        currenIdx += size;
        return page;

     }

    private void applyFilters(){
        filtered = new ArrayList<>();
        for(List<String> record : records){
            int timestamp = Integer.parseInt(record.get(0));
            int amount = Integer.parseInt(record.get(4));
            if(startTime != null && timestamp < startTime) continue;
            if(endTime != null && timestamp > endTime) continue;
            if(rangeStart != null && amount < rangeStart) continue;
            if(rangeEnd != null && amount > rangeEnd) continue;
            if(userId != null && !record.get(2).equals(userId)) continue;
            if(currency != null && !record.get(3).equals(currency)) continue;
            filtered.add(record);
        }
        currenIdx = 0;
    }
}
