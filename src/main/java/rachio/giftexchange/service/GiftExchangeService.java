package rachio.giftexchange.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rachio.giftexchange.model.FamilyMember;

public class GiftExchangeService {

	// ExampleRepository is included to show how to seed with hard coded data.
	// Replace/edit it as needed.

	public GiftExchangeService() {
	}

	public void assignGiftGivers(List<FamilyMember> familyMembers) {
		if (familyMembers == null || familyMembers.isEmpty()) {
			return;// No family members to assign gifts to
		}

		List<FamilyMember> availableMembers = new ArrayList<>(familyMembers);
		Collections.shuffle(availableMembers);

		for (int i = 0; i < familyMembers.size(); i++) {
			FamilyMember giver = familyMembers.get(i);
			FamilyMember receiver = availableMembers.get(i);

			if (giver != receiver) {
				giver.setGiftGiver(receiver);
				familyMembers.set(i, giver);

			} else {
				// Handle the case where the giver and receiver are the same
				// For example, you can reshuffle the availableMembers and start over
				Collections.shuffle(availableMembers);
				i = -1; // Reset the loop
			}
		}

	}
}
