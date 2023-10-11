package rachio.giftexchange.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rachio.giftexchange.model.FamilyMember;

public class GiftExchangeService {

	

	public GiftExchangeService() {
	}

	public void assignGiftGivers(List<FamilyMember> familyMembers) {
		if (familyMembers == null || familyMembers.isEmpty()) {
			return; // No family members to assign gifts to
		}

		List<FamilyMember> availableMembers = new ArrayList<>(familyMembers);
		Collections.shuffle(availableMembers);

		// Keep track of the last time each family member was assigned as a gift giver
		Map<FamilyMember, Integer> lastAssignedYear = new HashMap<>();
		int i = 0;

		while (i < familyMembers.size()) {
			FamilyMember giver = familyMembers.get(i);
			FamilyMember receiver = availableMembers.get(i);

			if (giver != receiver) {
				int currentYear = FamilyMember.getCurrentYear();
				int lastAssigned = lastAssignedYear.getOrDefault(giver, -1);

				if (lastAssigned == -1 || currentYear - lastAssigned >= 3) {
					giver.setGiftGiver(receiver);
					lastAssignedYear.put(giver, currentYear); 
					i++;
				} else {
					Collections.shuffle(availableMembers);
					lastAssignedYear.clear(); 
					i = 0; 
				}
			} else {
				Collections.shuffle(availableMembers);
				lastAssignedYear.clear(); 
				i = 0; 
			}
		}
	}

	
}
