import java.util.*;
import java.math.*;


/**
 * Solution

data

scan data rows, except the header
rule 4: count valid words in col2, check if half of those are in column 4 and 5 combined

Maintain a list of results

rule 3: matching is case insensitive, commas not allowed 

iterate through input data -> add result to a corrsponding index in result list
generate string to be added to result once a certain rules fails -> continue once one rule fails to prevent duplicates

perform computations -> apply rules

rule 1: trim -> check length == 6

rule2: check length after trimming

rule 3: check content of column 2 on any forbidden words

rule 4: 
use set to count valid words in col2 and another set in col 4 and 5 -> iterate thru both with a count and find whether it meets the threshold.
 */
class Solution {
    public List<String> validateDatasetRows(List<String> data, List<String> forbiddenWords, List<String> ignoreWords) {
        // TODO: Implement validateDatasetRows logic
        
        Set<String> forbidden = new HashSet<>();
        Set<String> ignore = new HashSet<>();
        List<String> res = new ArrayList<>();

        // Convert forbidden words to a set
        for(String s : forbiddenWords){
            forbidden.add(s.toLowerCase());
        }

        // Convert ignored words to a set
        for(String s : ignoreWords){
            ignore.add(s.toLowerCase());
        }

        // validate rules and build out resulting list
        for(int i = 1; i < data.size(); i++){
            String[] row = data.get(i).split(",");
            boolean invalid = false;
            if (row.length != 6) { 
                res.add("INVALID:RULE_1"); 
                continue;
            }
            //check rule 1 and trim each column
            for(int j = 0; j < 6; j++){
                String trimmed = row[j].strip();
                if(trimmed.isEmpty()){
                    
                    invalid = true;
                }
                row[j] = trimmed;
            }
            if(invalid){
                res.add("INVALID:RULE_1");
                continue;
            }
            //check rule 2
            if(row[4].length() < 5 || row[4].length() > 31){
                res.add("INVALID:RULE_2");
                continue;
            }

            //check rule 3 and populate set of unique valid words for col4 (set up for rule 4 check)
            String[] col2 = row[1].split("\\s+");
            Set<String> uniqueValid2 = new HashSet<>();
            for(String token : col2){
                String toCheck = token.toLowerCase();
                if(forbidden.contains(toCheck)){
                   invalid = true; 
                }
                if(!ignore.contains(toCheck)){
                    uniqueValid2.add(toCheck);
                }
            }
            if(invalid){
                res.add("INVALID:RULE_3");
                continue;
            }

            //check rule4: populate set of unique words for col4 and col5
            String[] col4 = row[3].toLowerCase().split("\\s+");
            String[] col5 = row[4].toLowerCase().split("\\s+");
            Set<String> unique4 = new HashSet<>();
            Set<String> unique5 = new HashSet<>();
            for(String s : col4){
                unique4.add(s);
            }
            for(String s : col5){
                unique5.add(s);
            }
            double count = 0;
            for(String s : uniqueValid2){
                if(unique4.contains(s) || unique5.contains(s)){
                    count++;
                }
            }
            double threshold = count / (double) uniqueValid2.size();
            // handle division by zero -> NaN -> NaN <= 0.5 case
            if(uniqueValid2.size() == 0 || threshold < 0.5){
                res.add("INVALID:RULE_4");
                continue; 
            }
            res.add("VALID");
        }
        return res;
    }
}
