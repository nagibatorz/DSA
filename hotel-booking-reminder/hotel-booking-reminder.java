import java.util.*;

public class Solution {
    public List<String> scheduleBookingEmails(
        List<Map<String, Object>> reservations,
        Map<String, String> emailTouchpoints,
        List<Map<String, Object>> modifications
    ) {
        // TODO: Implement scheduleBookingEmails logic.
        Map<Integer, List<String>> emails = new TreeMap<>();
        List<String> res = new ArrayList<>();

        Map<String, TreeMap<Integer, String>> mods = new HashMap<>();

        for(Map<String, Object> mod : modifications){
            String name = String.valueOf(mod.get("guest_name"));
            String type = String.valueOf(mod.get("new_room_type"));
            int date = (Integer) mod.get("modification_date");
            String modMessage = ": [Room Change] Booking: " + name + " (" + type + ")";

            mods.computeIfAbsent(name, k -> new TreeMap<>()).put(date, type);
            emails.computeIfAbsent(date, k -> new ArrayList<>()).add(modMessage);
        }


        for(Map<String, Object> guest : reservations){
            // get guest info
            String name = String.valueOf(guest.get("guest_name"));
            String roomType = String.valueOf(guest.get("room_type"));
            int checkIn = (Integer) guest.get("check_in_day");
            int stayLength = (Integer) guest.get("stay_length");

            for(String key : emailTouchpoints.keySet()){

                // Compute day of communication
                int scheduledDay;
                if(key.equals("start")){
                    scheduledDay = checkIn;
                } else if(key.equals("end")){
                    scheduledDay = stayLength + checkIn;
                } else{
                    scheduledDay = stayLength + checkIn + Integer.parseInt(key);
                }

                // check if the date is valid and add to messages
                if(scheduledDay >= checkIn){
                    String actualRoom = roomType;
                    if (mods.containsKey(name)) {
                        Map.Entry<Integer, String> recentMod = mods.get(name).lowerEntry(scheduledDay);
                        if (recentMod != null) {
                            actualRoom = recentMod.getValue();
                        }
                    }
                    String message = ": [" + emailTouchpoints.get(key) + "] Booking: " + name + " (" + actualRoom + ")";
                    emails.computeIfAbsent(scheduledDay, k -> new ArrayList<>()).add(message);
                }
            }
        }

        for(int day : emails.keySet()){
            for(String m : emails.get(day)){
                String fullMessage = String.valueOf(day) + m;
                res.add(fullMessage);
            }
        }
        return res;
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

        List<String> res = solution.scheduleBookingEmails(reservations, emailTouchpoints, modifications);
        System.out.println(res);
        // Expected:
        // [
        //     "0: [Welcome] Booking: A (Standard)",
        //     "1: [Upcoming Checkout] Booking: B (Suite)",
        //     "1: [Welcome] Booking: B (Suite)",
        //     "5: [Room Change] Booking: A (Suite)",
        //     "15: [Upcoming Checkout] Booking: A (Suite)",
        //     "16: [Thank You] Booking: B (Suite)",
        //     "30: [Thank You] Booking: A (Suite)",
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
        res1.put("stay_length", 10);
        reservations.add(res1);

        Map<String, String> emailTouchpoints = new HashMap<>();
        emailTouchpoints.put("start", "Welcome");
        emailTouchpoints.put("-3", "Reminder");
        emailTouchpoints.put("end", "Thank You");

        List<Map<String, Object>> modifications = new ArrayList<>();
        Map<String, Object> mod1 = new HashMap<>();
        mod1.put("guest_name", "C");
        mod1.put("new_room_type", "Premium");
        mod1.put("modification_date", 7);
        modifications.add(mod1);

        List<String> res = solution.scheduleBookingEmails(reservations, emailTouchpoints, modifications);
        System.out.println(res);
        // Expected:
        // [
        //     "0: [Welcome] Booking: C (Economy)",
        //     "7: [Reminder] Booking: C (Economy)",
        //     "7: [Reminder] Booking: C (Premium)",
        //     "7: [Room Change] Booking: C (Premium)",
        //     "10: [Thank You] Booking: C (Premium)",
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
        res1.put("stay_length", 40);
        reservations.add(res1);

        Map<String, String> emailTouchpoints = new HashMap<>();
        emailTouchpoints.put("start", "Welcome");
        emailTouchpoints.put("-30", "First Reminder");
        emailTouchpoints.put("-15", "Second Reminder");
        emailTouchpoints.put("end", "Thank You");

        List<Map<String, Object>> modifications = new ArrayList<>();
        Map<String, Object> mod1 = new HashMap<>();
        mod1.put("guest_name", "E");
        mod1.put("new_room_type", "Silver");
        mod1.put("modification_date", 10);
        modifications.add(mod1);

        Map<String, Object> mod2 = new HashMap<>();
        mod2.put("guest_name", "E");
        mod2.put("new_room_type", "Gold");
        mod2.put("modification_date", 25);
        modifications.add(mod2);

        List<String> res = solution.scheduleBookingEmails(reservations, emailTouchpoints, modifications);
        System.out.println(res);
        // Expected:
        // [
        //     "0: [Welcome] Booking: E (Bronze)",
        //     "10: [First Reminder] Booking: E (Bronze)",
        //     "10: [First Reminder] Booking: E (Silver)",
        //     "10: [Room Change] Booking: E (Silver)",
        //     "25: [Second Reminder] Booking: E (Silver)",
        //     "25: [Second Reminder] Booking: E (Gold)",
        //     "25: [Room Change] Booking: E (Gold)",
        //     "40: [Thank You] Booking: E (Gold)",
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
        res1.put("stay_length", 30);
        reservations.add(res1);

        Map<String, Object> res2 = new HashMap<>();
        res2.put("guest_name", "G");
        res2.put("room_type", "Pro");
        res2.put("check_in_day", 5);
        res2.put("stay_length", 20);
        reservations.add(res2);

        Map<String, Object> res3 = new HashMap<>();
        res3.put("guest_name", "H");
        res3.put("room_type", "Plus");
        res3.put("check_in_day", 10);
        res3.put("stay_length", 15);
        reservations.add(res3);

        Map<String, String> emailTouchpoints = new HashMap<>();
        emailTouchpoints.put("start", "Welcome");
        emailTouchpoints.put("-10", "Reminder");
        emailTouchpoints.put("end", "Thank You");

        List<Map<String, Object>> modifications = new ArrayList<>();

        Map<String, Object> mod1 = new HashMap<>();
        mod1.put("guest_name", "F");
        mod1.put("new_room_type", "Premium");
        mod1.put("modification_date", 10);
        modifications.add(mod1);

        Map<String, Object> mod2 = new HashMap<>();
        mod2.put("guest_name", "G");
        mod2.put("new_room_type", "Enterprise");
        mod2.put("modification_date", 10);
        modifications.add(mod2);

        Map<String, Object> mod3 = new HashMap<>();
        mod3.put("guest_name", "F");
        mod3.put("new_room_type", "Ultimate");
        mod3.put("modification_date", 20);
        modifications.add(mod3);

        List<String> res = solution.scheduleBookingEmails(reservations, emailTouchpoints, modifications);
        System.out.println(res);
        // Expected:
        // [
        //     "0: [Welcome] Booking: F (Basic)",
        //     "5: [Welcome] Booking: G (Pro)",
        //     "10: [Room Change] Booking: F (Premium)",
        //     "10: [Room Change] Booking: G (Enterprise)",
        //     "10: [Welcome] Booking: H (Plus)",
        //     "15: [Reminder] Booking: G (Enterprise)",
        //     "15: [Reminder] Booking: H (Plus)",
        //     "20: [Reminder] Booking: F (Premium)",
        //     "20: [Reminder] Booking: F (Ultimate)",
        //     "20: [Room Change] Booking: F (Ultimate)",
        //     "25: [Thank You] Booking: G (Enterprise)",
        //     "25: [Thank You] Booking: H (Plus)",
        //     "30: [Thank You] Booking: F (Ultimate)",
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
