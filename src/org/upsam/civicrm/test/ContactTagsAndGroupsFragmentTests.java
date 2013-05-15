package org.upsam.civicrm.test;

import static org.fest.assertions.api.ANDROID.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.times;

import org.mockito.InOrder;
import org.mockito.Mockito;
import org.upsam.civicrm.R;
import org.upsam.civicrm.contact.detail.fragments.ContactTagsAndGroupsFragment;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.octo.android.robospice.request.SpiceRequest;
import com.octo.android.robospice.request.listener.RequestListener;

public class ContactTagsAndGroupsFragmentTests extends
		AbstractCiviCRMContactFragmentTestCase<ContactTagsAndGroupsFragment> {

	/**
	 * 
	 */
	public ContactTagsAndGroupsFragmentTests() {
		super("ContactTagsAndGroupsFragmentTests");
	}

	@SuppressWarnings("unchecked")
	public void testAllRequestAreExecutedInCorrectOrder() {
		InOrder inOrder = Mockito.inOrder(spyRequestBuilder, spySpiceManager);

		inOrder.verify(spyRequestBuilder).requestGroupByContactId(
				TEST_CONTACT_ID);
		inOrder.verify(spyRequestBuilder).requestTagsByContactId(
				TEST_CONTACT_ID);
		inOrder.verify(spySpiceManager, times(2)).execute(
				notNull(SpiceRequest.class), notNull(String.class), anyLong(),
				notNull(RequestListener.class));

		inOrder.verify(spyRequestBuilder).requestTagById(1);
		inOrder.verify(spySpiceManager).execute(notNull(SpiceRequest.class),
				notNull(String.class), anyLong(),
				notNull(RequestListener.class));

		inOrder.verify(spyRequestBuilder).requestTagById(2);
		inOrder.verify(spySpiceManager).execute(notNull(SpiceRequest.class),
				notNull(String.class), anyLong(),
				notNull(RequestListener.class));
	}

	public void testPrincipalLayoutHasOneChildForTagAndOtherForGroups() {
		RelativeLayout tagsAndGroupsLayout = (RelativeLayout) fragmentLayout
				.findViewById(R.id.tags_groups);
		assertThat(tagsAndGroupsLayout) //
				.isVisible() //
				.hasChildCount(2); //
	}

	public void testContactGroupsArePaintedCorrectly() {
		RelativeLayout tagsAndGroupsLayout = (RelativeLayout) fragmentLayout
				.findViewById(R.id.tags_groups);
		LinearLayout groupsLayout = (LinearLayout) tagsAndGroupsLayout
				.findViewById(R.id.groupList);

		TextView title = (TextView) groupsLayout.getChildAt(0);
		ViewGroup row1 = (ViewGroup) groupsLayout.getChildAt(1);
		ViewGroup row2 = (ViewGroup) groupsLayout.getChildAt(2);
		assertThat(groupsLayout) //
				.isVisible() //
				.hasChildCount(rowsForTestGroupsPlusTitle()); //
		assertThat(title) //
				.isVisible().containsText(R.string.groups_literal);
		assertThat(row1) //
				.isVisible() //
				.hasChildCount(groupsInfoPlusIcon()); //
		assertThat(row2) //
				.isVisible() //
				.hasChildCount(groupsInfoPlusIcon()); //

	}

	public void testContactTagsArePaintedCorrectly() {
		RelativeLayout tagsAndGroupsLayout = (RelativeLayout) fragmentLayout
				.findViewById(R.id.tags_groups);
		LinearLayout tagsLayout = (LinearLayout) tagsAndGroupsLayout
				.findViewById(R.id.tagsList);

		TextView title = (TextView) tagsLayout.getChildAt(0);
		ViewGroup row1 = (ViewGroup) tagsLayout.getChildAt(1);
		ViewGroup row2 = (ViewGroup) tagsLayout.getChildAt(2);
		assertThat(tagsLayout) //
				.isVisible() //
				.hasChildCount(rowsForTestTagsPlusTitle()); //
		assertThat(title) //
				.isVisible().containsText(R.string.tags_literal);
		assertThat(row1) //
				.isVisible() //
				.hasChildCount(tagsInfoPlusIcon()); //
		assertThat(row2) //
				.isVisible() //
				.hasChildCount(tagsInfoPlusIcon()); //

	}

	private int tagsInfoPlusIcon() {
		return 3;
	}

	private int rowsForTestTagsPlusTitle() {
		return 3;
	}

	private int groupsInfoPlusIcon() {
		return 3;
	}

	private int rowsForTestGroupsPlusTitle() {
		return 3;
	}

	@Override
	protected boolean createFragment() {
		return true;
	}

	@Override
	protected String getFragmentTagName() {
		return "contactTagsAndGroups";
	}

	@Override
	protected ContactTagsAndGroupsFragment newFragmentInstance() {
		return new ContactTagsAndGroupsFragment();
	}
}
