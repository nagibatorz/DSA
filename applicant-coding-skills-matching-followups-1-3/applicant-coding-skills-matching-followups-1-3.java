import java.util.*;
import java.math.*;


/*

Java-8, Python-3, Go-Lang

required skills -> hashSet for O(1) look up to match skills

trim whitespaces -> split based on "," -> iterate through String[] looking for matches -> if match add to an array list (preserves the order of applicant skills)

java -> java2 java3
python -> python2, python3

*/

class Solution {
    public List<String> matchSkills(String applicantSkills, List<String> requiredSkills) {
        // TODO: Implement matchSkills logic
        String[] skills = applicantSkills.trim().split("\\s*,\\s*");
        Map<String, List<String>> genericToSpecific = new HashMap<>();
        Set<String> reqs = new HashSet<>();

        // Populate required skill storages
        for(String requiredSkill : requiredSkills){
            int hyphenIdx = requiredSkill.indexOf("-");
            if(hyphenIdx != -1){
                // map generic skill as a prefix of specific skill
                String pre = requiredSkill.substring(0, hyphenIdx);
                genericToSpecific.computeIfAbsent(pre, k -> new ArrayList<>()).add(requiredSkill);
            }
            // add required skill
            reqs.add(requiredSkill);
        }

        Set<String> addedSkills = new HashSet<>();
        List<String> matchedSkills = new ArrayList<>();
        for(String applicantSkill : skills){
            if(applicantSkill.equals("*")){
                for(String requiredSkill : requiredSkills){
                    if(!addedSkills.contains(requiredSkill)){
                        matchedSkills.add(requiredSkill);
                    }
                }
                return matchedSkills;
            }
            if(genericToSpecific.containsKey(applicantSkill)){
                for(String fromGeneric : genericToSpecific.get(applicantSkill)){
                    if(!addedSkills.contains(fromGeneric)){
                        matchedSkills.add(fromGeneric);
                        addedSkills.add(fromGeneric);
                    }
                }
            } else{
                if(reqs.contains(applicantSkill) && !addedSkills.contains(applicantSkill)){
                    matchedSkills.add(applicantSkill);
                    addedSkills.add(applicantSkill);
                }
            }
        }
        return matchedSkills;
    }
}
