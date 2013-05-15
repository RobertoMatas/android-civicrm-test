package org.upsam.civicrm.test;

import static org.fest.assertions.api.ANDROID.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.notNull;

import org.mockito.InOrder;
import org.mockito.Mockito;
import org.upsam.civicrm.R;
import org.upsam.civicrm.contact.detail.fragments.ListContactsActivitiesFragment;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.octo.android.robospice.request.SpiceRequest;
import com.octo.android.robospice.request.listener.RequestListener;

public class ContactActivitiesFragmentTests extends
		AbstractCiviCRMContactFragmentTestCase<ListContactsActivitiesFragment> {

	/**
	 * 
	 */
	public ContactActivitiesFragmentTests() {
		super("ContactActivitiesFragmentTests");
	}

	@SuppressWarnings("unchecked")
	public void testAllRequestAreExecutedInCorrectOrder() {
		InOrder inOrder = Mockito.inOrder(spyRequestBuilder, spySpiceManager);
		inOrder.verify(spyRequestBuilder).requestActivitiesForContact(
				TEST_CONTACT_ID);
		inOrder.verify(spySpiceManager).execute(notNull(SpiceRequest.class),
				notNull(String.class), anyLong(),
				notNull(RequestListener.class));
	}

	public void testContactActivitiesArePaintedCorrectly() {
		LinearLayout activitiesListLayout = (LinearLayout) fragmentLayout
				.findViewById(R.id.addressList);
		assertThat(activitiesListLayout) //
				.isVisible() //
				.hasChildCount(activitiesForContactUnderTest()); //
		for (int i = 0; i < activitiesForContactUnderTest(); i++) {
			assertThat((ViewGroup) activitiesListLayout.getChildAt(i)) //
					.isVisible() //
					.hasChildCount(numberOfLinesforActivityPainted()); //
		}
	}

	private int numberOfLinesforActivityPainted() {
		return 6;
	}

	private int activitiesForContactUnderTest() {
		return 3;
	}

	@Override
	protected boolean createFragment() {
		return true;
	}

	@Override
	protected String getFragmentTagName() {
		return "contactActivities";
	}

	@Override
	protected ListContactsActivitiesFragment newFragmentInstance() {
		return new ListContactsActivitiesFragment();
	}
}
