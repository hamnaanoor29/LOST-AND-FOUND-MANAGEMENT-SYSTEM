package com.lostandfoundsystem;
import java.util.ArrayList;
import java.util.List;

public class CompareAndCheck {

    // Global Lists to store items
    public static List<LostItem> lostList = new ArrayList<>();
    public static List<FoundItem> foundList = new ArrayList<>();

    /**
     * Search for a matching found item when a lost item is reported.
     */
    public static String searchMatchForLost(LostItem lost) {
        if (lost == null || lost.getName() == null) {
            return "Invalid item details provided.";
        }

        for (FoundItem found : foundList) {
            if (found.getName() != null && lost.getName().equalsIgnoreCase(found.getName())) {
                return "🎉 Match Found!\n\n"
                        + "📍 Location: " + found.getLocation() + "\n"
                        + "📞 Contact: " + found.getPhone();
            }
        }

        return "No match found yet. Item added to records.";
    }

    /**
     * Search for a matching lost item when a found item is reported.
     */
    public static String searchMatchForFound(FoundItem found) {
        if (found == null || found.getName() == null) {
            return "Invalid item details provided.";
        }

        for (LostItem lost : lostList) {
            if (lost.getName() != null && found.getName().equalsIgnoreCase(lost.getName())) {
                return "🎉 Owner Located!\n\n"
                        + "📍 Lost Location: " + lost.getLocation() + "\n"
                        + "📞 Contact: " + lost.getPhone();
            }
        }

        return "No owner found yet. Item recorded.";
    }

    // Helper Method: Direct Check if Lost Item is Matched
    public static boolean isLostMatched(LostItem lost) {
        for (FoundItem found : foundList) {
            if (lost.getName() != null && lost.getName().equalsIgnoreCase(found.getName())) {
                return true;
            }
        }
        return false;
    }

    // Helper Method: Direct Check if Found Item is Matched
    public static boolean isFoundMatched(FoundItem found) {
        for (LostItem lost : lostList) {
            if (found.getName() != null && found.getName().equalsIgnoreCase(lost.getName())) {
                return true;
            }
        }
        return false;
    }

    // Dynamic Helper Methods for Dashboard Counter UI
    public static int getMatchedCount() {
        int count = 0;
        for (LostItem lost : lostList) {
            if (isLostMatched(lost)) {
                count++;
            }
        }
        return count;
    }

    public static int getTotalRecords() {
        return lostList.size() + foundList.size();
    }
}
