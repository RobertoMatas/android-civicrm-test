package org.upsam.civicrm.test;

import static org.fest.assertions.api.ANDROID.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.notNull;

import org.mockito.InOrder;
import org.mockito.Mockito;
import org.upsam.civicrm.R;
import org.upsam.civicrm.contact.detail.fragments.ContactAddressFragment;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.octo.android.robospice.request.SpiceRequest;
import com.octo.android.robospice.request.listener.RequestListener;

public class ContactAddressFragmentTests extends
		AbstractCiviCRMContactFragmentTestCase<ContactAddressFragment> {

	public ContactAddressFragmentTests() {
		super("ContactAddressFragmentTests");
	}

	@SuppressWarnings("unchecked")
	public void testAllRequestAreExecutedInCorrectOrder() {
		InOrder inOrder = Mockito.inOrder(spyRequestBuilder, spySpiceManager);
		inOrder.verify(spyRequestBuilder).requestCountries();
		inOrder.verify(spySpiceManager).execute(notNull(SpiceRequest.class),
				notNull(String.class), anyLong(),
				notNull(RequestListener.class));

		inOrder.verify(spyRequestBuilder).requestLocationTypes();
		inOrder.verify(spySpiceManager).execute(notNull(SpiceRequest.class),
				notNull(String.class), anyLong(),
				notNull(RequestListener.class));

		inOrder.verify(spyRequestBuilder).requestContactAddresses(
				TEST_CONTACT_ID);
		inOrder.verify(spySpiceManager).execute(notNull(SpiceRequest.class),
				notNull(String.class), anyLong(),
				notNull(RequestListener.class));
	}

	public void testContactAddressesArePaintedCorrectly() {
		LinearLayout addressListLayout = (LinearLayout) fragmentLayout
				.findViewById(R.id.addressList);
		ViewGroup row1 = (ViewGroup) addressListLayout.getChildAt(0);
		ViewGroup row2 = (ViewGroup) addressListLayout.getChildAt(1);
		assertThat(addressListLayout) //
				.isVisible() //
				.hasChildCount(emailsForContactUnderTRest()); //
		assertThat(row1) //
				.isVisible() //
				.hasChildCount(numberOfAddresDataPainted()); //
		assertThat(row2) //
				.isVisible() //
				.hasChildCount(numberOfAddresDataPainted()); //

	}

	private int numberOfAddresDataPainted() {
		return 6;
	}

	private int emailsForContactUnderTRest() {
		return 2;
	}

	@Override
	protected String getFragmentTagName() {
		return "contactAddress";
	}

	@Override
	protected ContactAddressFragment newFragmentInstance() {
		return new ContactAddressFragment();
	}

	@Override
	protected boolean createFragment() {
		return true;
	}
}
