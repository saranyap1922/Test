package rachio.giftexchange.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import rachio.giftexchange.model.FamilyMember;

@RunWith(MockitoJUnitRunner.class)
public class GiftExchangeServiceTest {

	private GiftExchangeService giftExchangeService = new GiftExchangeService();
	private List<FamilyMember> familyMembers;

	@Before
	public void initTest() {
		// Initialize the list of family members for testing
		familyMembers = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			familyMembers.add(new FamilyMember("Member " + i));
		}
	}

	@Test
	public void testNoFamilyMembers() {
		List<FamilyMember> emptyFamilyMembers = new ArrayList<>();

		giftExchangeService.assignGiftGivers(emptyFamilyMembers);

		assertEquals(0, emptyFamilyMembers.size());
	}

	@Test
	public void testGiftGiversAssigned() {
		giftExchangeService.assignGiftGivers(familyMembers);

		for (FamilyMember member : familyMembers) {
			assertNotNull(member.getGiftGiver());
		}
	}

	@Test
	public void testNoSelfAssignments() {
		giftExchangeService.assignGiftGivers(familyMembers);

		for (FamilyMember member : familyMembers) {
			assertNotSame(member, member.getGiftGiver());
		}
	}

	@Test
	public void testOrderUnchangedAfterShuffling() {
		List<FamilyMember> originalFamilyMembers = new ArrayList<>(familyMembers);

		giftExchangeService.assignGiftGivers(familyMembers);

		assertEquals(familyMembers, originalFamilyMembers);
	}

	@Test
	public void testThreeYearRestriction() {
		// Assign gift givers for four consecutive years
		giftExchangeService.assignGiftGivers(familyMembers);
		giftExchangeService.assignGiftGivers(familyMembers);
		giftExchangeService.assignGiftGivers(familyMembers);
		giftExchangeService.assignGiftGivers(familyMembers);

		for (FamilyMember member : familyMembers) {
			assertNotNull(member.getGiftGiver());
		}

		for (FamilyMember member : familyMembers) {
			assertFalse(member.hasGiftedInLastNYears(member.getGiftGiver(), 3));
		}
	}

}
