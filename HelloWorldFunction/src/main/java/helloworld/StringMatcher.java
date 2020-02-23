package helloworld;



import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;

public class StringMatcher {
    public static String getTheClosestMatch(List<String> collection, String target) {
        int distance = Integer.MAX_VALUE;
        String closest = null;
        for (String compareObject : collection) {
            int currentDistance = StringUtils.getLevenshteinDistance(compareObject, target);
            if(currentDistance < distance) {
                distance = currentDistance;
                closest = compareObject;
            }
        }
        return closest;
    }
}
