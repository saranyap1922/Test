package rachio.giftexchange.model;

import java.time.Year;
import java.util.HashMap;
import java.util.Map;

public class FamilyMember {
    private String name;
    private FamilyMember giftGiver;
    private Map<Integer, FamilyMember> giftGiversByYear;

    public FamilyMember(String name) {
        this.name = name;
        this.giftGiversByYear = new HashMap<>();
    }

    public FamilyMember getGiftGiver() {
        return giftGiver;
    }

    public void setGiftGiver(FamilyMember giftGiver) {
        this.giftGiver = giftGiver;
    }

    public boolean hasGiftedInLastNYears(FamilyMember receiver, int years) {
        int currentYear = getCurrentYear(); // Replace with your logic to get the current year
        for (int year = currentYear - 1; year >= currentYear - years; year--) {
            FamilyMember giver = giftGiversByYear.get(year);
            if (giver != null && giver.equals(receiver)) {
                return true;
            }
        }
        return false;
    }

    public void assignGiftGiverForYear(int year, FamilyMember giver) {
        giftGiversByYear.put(year, giver);
    }

    public static int getCurrentYear() {
        return Year.now().getValue();
    }

    public FamilyMember getGiftGiverInYear(int year) {
        return giftGiversByYear.get(year);
    }

   
}
