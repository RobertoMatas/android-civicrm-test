package org.upsam.civicrm.test.dagger.modules;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import javax.inject.Singleton;

import org.upsam.civicrm.contact.add.AddContactActivity;
import org.upsam.civicrm.contact.add.AddContactValidator;
import org.upsam.civicrm.contact.add.AddContactValidatorImpl;
import org.upsam.civicrm.contact.detail.ContactDetailFragmentActivity;
import org.upsam.civicrm.contact.detail.fragments.ContactAddressFragment;
import org.upsam.civicrm.contact.detail.fragments.ContactDetailFragment;
import org.upsam.civicrm.contact.detail.fragments.ContactTagsAndGroupsFragment;
import org.upsam.civicrm.contact.detail.fragments.ListContactsActivitiesFragment;
import org.upsam.civicrm.contact.detail.fragments.OtherInformationFragment;
import org.upsam.civicrm.dagger.utilities.ProgressDialogUtilities;
import org.upsam.civicrm.rest.CiviCRMAndroidSpiceService;
import org.upsam.civicrm.rest.req.CiviCRMContactRequestBuilder;
import org.upsam.civicrm.test.AddContactActivityTests;
import org.upsam.civicrm.test.ContactActivitiesFragmentTests;
import org.upsam.civicrm.test.ContactAddressFragmentTests;
import org.upsam.civicrm.test.ContactDetailFragmentTests;
import org.upsam.civicrm.test.ContactOtherInfoFragmentTests;
import org.upsam.civicrm.test.ContactTagsAndGroupsFragmentTests;
import org.upsam.civicrm.test.fake.CiviCRMContactRequestBuilderFake;
import org.upsam.civicrm.test.fake.CiviMockApplication;
import org.upsam.civicrm.test.fake.SpiceManagerFake;

import com.octo.android.robospice.SpiceManager;

import dagger.Module;
import dagger.Provides;

@Module( //
injects = {
		AddContactActivityTests.class, //
		AddContactActivity.class, CiviMockApplication.class,
		ContactDetailFragmentActivity.class, ContactDetailFragmentTests.class,
		ContactDetailFragment.class, ContactAddressFragmentTests.class,
		ContactAddressFragment.class, ContactOtherInfoFragmentTests.class,
		OtherInformationFragment.class, ContactTagsAndGroupsFragment.class,
		ContactTagsAndGroupsFragmentTests.class,
		ContactActivitiesFragmentTests.class,
		ListContactsActivitiesFragment.class }, //
overrides = true //
)
public class TestModule {
	@Provides
	@Singleton
	CiviCRMContactRequestBuilder provideCiviCRMContactRequestBuilder() {
		return spy(new CiviCRMContactRequestBuilderFake());
	}

	@Provides
	@Singleton
	SpiceManager provideSpiceManager() {
		SpiceManager spiceManager = new SpiceManagerFake(
				CiviCRMAndroidSpiceService.class);
		return spy(spiceManager);
	}

	@Provides
	@Singleton
	AddContactValidator provideValidator() {
		AddContactValidator validator = new AddContactValidatorImpl();
		return spy(validator);
	}

	@Provides
	@Singleton
	ProgressDialogUtilities provideProgressDialogUtilities() {
		return mock(ProgressDialogUtilities.class);
	}
}