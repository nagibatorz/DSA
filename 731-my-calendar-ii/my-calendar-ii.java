class MyCalendarTwo {
    List<Entry> initialIntervals;
    List<Entry> sharedIntervals;

    public MyCalendarTwo() {
        this.initialIntervals = new ArrayList<>();
        this.sharedIntervals = new ArrayList<>();
    }
    
    public boolean book(int startTime, int endTime) {
        for(Entry meeting : sharedIntervals){
            if(!(startTime >= meeting.end || endTime <= meeting.start)){
                return false;
            }
        }
        for(Entry meeting : initialIntervals){
            if(!(startTime >= meeting.end || endTime <= meeting.start)){
                sharedIntervals.add(new Entry(Math.max(startTime, meeting.start), Math.min(endTime, meeting.end)));
            }
        }
        initialIntervals.add(new Entry(startTime, endTime));
        return true;
    }

    class Entry{
        int start;
        int end;

        public Entry(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(startTime,endTime);
 */