package rachio.giftexchange.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FamilyMember {
    private final String name;
    private FamilyMember giftGiver;

    public FamilyMember(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGiftGiver(FamilyMember giver) {
        this.giftGiver = giver;
    }

    public FamilyMember getGiftGiver() {
        return giftGiver;
    }

}

