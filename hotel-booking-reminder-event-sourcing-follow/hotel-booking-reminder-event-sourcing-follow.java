import java.util.*;

public class Solution {

    private static class State{
        int startDay;
        int endDay;
        String roomType;
        int checkOut;

        State(int startDay, int endDay, String roomType, int checkout){
            this.startDay = startDay;
            this.endDay = endDay;
            this.roomType = roomType;
            this.checkOut = checkout;
        }
    }
    public List<String> scheduleBookingEmails(
        List<Map<String, Object>> reservations,
        Map<String, String> emailTouchpoints,
        List<Map<String, Object>> modifications
    ) {
        // TODO: Implement scheduleBookingEmails logic.

        Map<Integer, List<String>> emails = new TreeMap<>();

        // guest name -> list of modifications
        Map<String, List<Map<String, Object>>> mods = new HashMap<>();
        if(modifications != null){
            for(Map<String, Object> mod : modifications){
                String name = String.valueOf(mod.get("guest_name"));
                mods.computeIfAbsent(name, k -> new ArrayList<>()).add(mod);
            }

            for(List<Map<String, Object>> modList : mods.values()){
                modList.sort(Comparator.comparingInt(m -> (Integer) m.get("modification_date")));
            }
        }

        for(Map<String, Object> res : reservations){
            String name = String.valueOf(res.get("guest_name"));
            String currRoom = String.valueOf(res.get("room_type"));
            int checkIn = (Integer) res.get("check_in_day");
            int currCheckout = checkIn + (Integer) res.get("stay_length");

            List<State> timeline = new ArrayList<>();
            int currValidFrom = 0;

            List<Map<String, Object>> guestMod = mods.getOrDefault(name, Collections.emptyList());
            for(Map<String, Object> mod : guestMod){
                int modDate = (Integer) mod.get("modification_date");

                timeline.add(new State(currValidFrom, modDate, currRoom, currCheckout));
                currValidFrom = modDate;
                if(mod.containsKey("new_room_type")){
                    currRoom = String.valueOf(mod.get("new_room_type"));
                    String msg = ": [Room Change] Booking: " + name + " (" + currRoom + ")";
                    emails.computeIfAbsent(modDate, k -> new ArrayList<>()).add(msg);
                } else if(mod.containsKey("extension_days")){
                    currCheckout += (Integer) mod.get("extension_days");
                    String msg = ": [Extended] Booking: " + name + " (" + currRoom + ")";
                    emails.computeIfAbsent(modDate, k -> new ArrayList<>()).add(msg);
                }
            }
            timeline.add(new State(currValidFrom, Integer.MAX_VALUE, currRoom, currCheckout));
            for(State state : timeline){
                if(state.startDay == state.endDay) continue;
                for(Map.Entry<String, String> entry : emailTouchpoints.entrySet()){
                    String key = entry.getKey();
                    int scheduledDay;

                    if (key.equals("start")) {
                        scheduledDay = checkIn;
                    } else if (key.equals("end")) {
                        scheduledDay = state.checkOut;
                    } else {
                        scheduledDay = state.checkOut + Integer.parseInt(key);
                    }

                    if(scheduledDay >= checkIn && scheduledDay >= state.startDay && scheduledDay < state.endDay){
                        String msg = ": [" + entry.getValue() + "] Booking: " + name + " (" + state.roomType + ")";
                        emails.computeIfAbsent(scheduledDay, k -> new ArrayList<>()).add(msg);
                    }
                }
            }
        }
        // 4. Output Formatting
        List<String> result = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : emails.entrySet()) {
            for (String m : entry.getValue()) {
                result.add(entry.getKey() + m);
            }
        }
        return result;
    }

    public static void test1() {
        System.out.println("===== Test 1 =====");
        Solution solution = new Solution();
        List<Map<String, Object>> reservations = new ArrayList<>();
        Map<String, Object> res1 = new HashMap<>();
        res1.put("guest_name", "A");
        res1.put("room_type", "Standard");
        res1.put("check_in_day", 0);
        res1.put("stay_length", 30);
        reservations.add(res1);
        Map<String, Object> res2 = new HashMap<>();
        res2.put("guest_name", "B");
        res2.put("room_type", "Suite");
        res2.put("check_in_day", 1);
        res2.put("stay_length", 15);
        reservations.add(res2);

        Map<String, String> emailTouchpoints = new HashMap<>();
        emailTouchpoints.put("start", "Welcome");
        emailTouchpoints.put("-15", "Upcoming Checkout");
        emailTouchpoints.put("end", "Thank You");

        List<Map<String, Object>> modifications = new ArrayList<>();
        Map<String, Object> mod1 = new HashMap<>();
        mod1.put("guest_name", "A");
        mod1.put("new_room_type", "Suite");
        mod1.put("modification_date", 5);
        modifications.add(mod1);
        Map<String, Object> mod2 = new HashMap<>();
        mod2.put("guest_name", "B");
        mod2.put("extension_days", 15);
        mod2.put("modification_date", 3);
        modifications.add(mod2);

        List<String> res = solution.scheduleBookingEmails(reservations, emailTouchpoints, modifications);
        System.out.println(res);
        // Expected:
        // [
        //     "0: [Welcome] Booking: A (Standard)",
        //     "1: [Welcome] Booking: B (Suite)",
        //     "1: [Upcoming Checkout] Booking: B (Suite)",
        //     "3: [Extended] Booking: B (Suite)",
        //     "5: [Room Change] Booking: A (Suite)",
        //     "15: [Upcoming Checkout] Booking: A (Suite)",
        //     "16: [Upcoming Checkout] Booking: B (Suite)",
        //     "30: [Thank You] Booking: A (Suite)",
        //     "31: [Thank You] Booking: B (Suite)",
        // ]
    }

    public static void test2() {
        System.out.println("\n===== Test 2 =====");
        Solution solution = new Solution();
        List<Map<String, Object>> reservations = new ArrayList<>();
        Map<String, Object> res1 = new HashMap<>();
        res1.put("guest_name", "C");
        res1.put("room_type", "Economy");
        res1.put("check_in_day", 0);
        res1.put("stay_length", 20);
        reservations.add(res1);

        Map<String, String> emailTouchpoints = new HashMap<>();
        emailTouchpoints.put("start", "Welcome");
        emailTouchpoints.put("-10", "Reminder");
        emailTouchpoints.put("end", "Thank You");

        List<Map<String, Object>> modifications = new ArrayList<>();
        Map<String, Object> mod1 = new HashMap<>();
        mod1.put("guest_name", "C");
        mod1.put("extension_days", 10);
        mod1.put("modification_date", 10);
        modifications.add(mod1);

        List<String> res = solution.scheduleBookingEmails(reservations, emailTouchpoints, modifications);
        System.out.println(res);
        // Expected:
        // [
        //     "0: [Welcome] Booking: C (Economy)",
        //     "10: [Extended] Booking: C (Economy)",
        //     "20: [Reminder] Booking: C (Economy)",
        //     "30: [Thank You] Booking: C (Economy)",
        // ]
    }

    public static void test3() {
        System.out.println("\n===== Test 3 =====");
        Solution solution = new Solution();
        List<Map<String, Object>> reservations = new ArrayList<>();
        Map<String, Object> res1 = new HashMap<>();
        res1.put("guest_name", "D");
        res1.put("room_type", "Gold");
        res1.put("check_in_day", 5);
        res1.put("stay_length", 20);
        reservations.add(res1);

        Map<String, String> emailTouchpoints = new HashMap<>();
        emailTouchpoints.put("start", "Welcome");
        emailTouchpoints.put("-10", "Reminder");
        emailTouchpoints.put("end", "Thank You");

        List<Map<String, Object>> modifications = new ArrayList<>();
        Map<String, Object> mod1 = new HashMap<>();
        mod1.put("guest_name", "D");
        mod1.put("new_room_type", "Platinum");
        mod1.put("modification_date", 15);
        modifications.add(mod1);

        List<String> res = solution.scheduleBookingEmails(reservations, emailTouchpoints, modifications);
        System.out.println(res);
        // Expected:
        // [
        //     "5: [Welcome] Booking: D (Gold)",
        //     "15: [Reminder] Booking: D (Gold)",
        //     "15: [Reminder] Booking: D (Platinum)",
        //     "15: [Room Change] Booking: D (Platinum)",
        //     "25: [Thank You] Booking: D (Platinum)",
        // ]
    }

    public static void test4() {
        System.out.println("\n===== Test 4 =====");
        Solution solution = new Solution();
        List<Map<String, Object>> reservations = new ArrayList<>();
        Map<String, Object> res1 = new HashMap<>();
        res1.put("guest_name", "E");
        res1.put("room_type", "Bronze");
        res1.put("check_in_day", 0);
        res1.put("stay_length", 10);
        reservations.add(res1);

        Map<String, String> emailTouchpoints = new HashMap<>();
        emailTouchpoints.put("start", "Welcome");
        emailTouchpoints.put("-5", "First Reminder");
        emailTouchpoints.put("-2", "Second Reminder");
        emailTouchpoints.put("end", "Thank You");

        List<Map<String, Object>> modifications = new ArrayList<>();
        Map<String, Object> mod1 = new HashMap<>();
        mod1.put("guest_name", "E");
        mod1.put("extension_days", 10);
        mod1.put("modification_date", 5);
        modifications.add(mod1);
        Map<String, Object> mod2 = new HashMap<>();
        mod2.put("guest_name", "E");
        mod2.put("extension_days", 5);
        mod2.put("modification_date", 15);
        modifications.add(mod2);

        List<String> res = solution.scheduleBookingEmails(reservations, emailTouchpoints, modifications);
        System.out.println(res);
        // Expected:
        // [
        //     "0: [Welcome] Booking: E (Bronze)",
        //     "5: [Extended] Booking: E (Bronze)",
        //     "15: [Extended] Booking: E (Bronze)",
        //     "20: [First Reminder] Booking: E (Bronze)",
        //     "23: [Second Reminder] Booking: E (Bronze)",
        //     "25: [Thank You] Booking: E (Bronze)",
        // ]
    }

    public static void test5() {
        System.out.println("\n===== Test 5 =====");
        Solution solution = new Solution();
        List<Map<String, Object>> reservations = new ArrayList<>();
        Map<String, Object> res1 = new HashMap<>();
        res1.put("guest_name", "F");
        res1.put("room_type", "Basic");
        res1.put("check_in_day", 0);
        res1.put("stay_length", 20);
        reservations.add(res1);
        Map<String, Object> res2 = new HashMap<>();
        res2.put("guest_name", "G");
        res2.put("room_type", "Pro");
        res2.put("check_in_day", 5);
        res2.put("stay_length", 15);
        reservations.add(res2);

        Map<String, String> emailTouchpoints = new HashMap<>();
        emailTouchpoints.put("start", "Welcome");
        emailTouchpoints.put("-7", "Week Reminder");
        emailTouchpoints.put("-1", "Final Reminder");
        emailTouchpoints.put("end", "Thank You");

        List<Map<String, Object>> modifications = new ArrayList<>();
        Map<String, Object> mod1 = new HashMap<>();
        mod1.put("guest_name", "F");
        mod1.put("new_room_type", "Premium");
        mod1.put("modification_date", 10);
        modifications.add(mod1);
        Map<String, Object> mod2 = new HashMap<>();
        mod2.put("guest_name", "F");
        mod2.put("extension_days", 10);
        mod2.put("modification_date", 13);
        modifications.add(mod2);

        List<String> res = solution.scheduleBookingEmails(reservations, emailTouchpoints, modifications);
        System.out.println(res);
        // Expected:
        // [
        //     "0: [Welcome] Booking: F (Basic)",
        //     "5: [Welcome] Booking: G (Pro)",
        //     "10: [Room Change] Booking: F (Premium)",
        //     "13: [Extended] Booking: F (Premium)",
        //     "13: [Week Reminder] Booking: G (Pro)",
        //     "19: [Final Reminder] Booking: G (Pro)",
        //     "20: [Thank You] Booking: G (Pro)",
        //     "23: [Week Reminder] Booking: F (Premium)",
        //     "29: [Final Reminder] Booking: F (Premium)",
        //     "30: [Thank You] Booking: F (Premium)",
        // ] 
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }
}
