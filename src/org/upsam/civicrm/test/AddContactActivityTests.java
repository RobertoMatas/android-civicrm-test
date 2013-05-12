/**
 * 
 */
package org.upsam.civicrm.test;

import static android.test.ViewAsserts.assertOnScreen;
import static org.fest.assertions.api.ANDROID.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.upsam.civicrm.test.fake.CiviCRMContactRequestBuilderFake.CONTACT_CREATED_ID;

import javax.inject.Inject;

import org.mockito.InOrder;
import org.mockito.Mockito;
import org.upsam.civicrm.R;
import org.upsam.civicrm.contact.add.AddContactActivity;
import org.upsam.civicrm.contact.add.AddContactValidator;
import org.upsam.civicrm.contact.model.contact.ContactType;
import org.upsam.civicrm.dagger.utilities.ProgressDialogUtilities;
import org.upsam.civicrm.rest.req.CiviCRMContactRequestBuilder;

import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.request.SpiceRequest;
import com.octo.android.robospice.request.listener.RequestListener;

/**
 * @author pepin_000
 * 
 */
public class AddContactActivityTests extends
		AbstractCiviCRMTestCase<AddContactActivity> {
	private static final String TEXT_NAME = "Test Name";
	private static final String NO_INPUT = "";
	private static final String TEXT_EMAIL = "test@email.com";
	private static final String TEXT_PHONE = "65874215";
	/**
	 * Spinner with list of contact types
	 */
	Spinner contactTypeSpinner;
	/**
	 * For input contact name
	 */
	TextView contactNameTextView;
	/**
	 * For input contact phone
	 */
	TextView contactPhoneTextView;
	/**
	 * For input contact mail
	 */
	TextView contactEmailTextView;
	/**
	 * Button for submit
	 */
	Button button;

	@Inject
	AddContactValidator validator;

	@Inject
	ProgressDialogUtilities dialogUtilities;

	@Inject
	CiviCRMContactRequestBuilder spyReqBuilder;

	@Inject
	SpiceManager spySpiceManager;

	/**
	 * 
	 */
	public AddContactActivityTests() {
		this("AddContactActivityTests");
	}

	/**
	 * @param name
	 */
	public AddContactActivityTests(String name) {
		super(AddContactActivity.class);
		setName(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.upsam.civicrm.test.AbstractCiviCRMTestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.contactTypeSpinner = (Spinner) mActivity
				.findViewById(R.id.contact_type);
		this.contactNameTextView = (TextView) mActivity
				.findViewById(R.id.contact_name);
		this.contactPhoneTextView = (TextView) mActivity
				.findViewById(R.id.contact_phone);
		this.contactEmailTextView = (TextView) mActivity
				.findViewById(R.id.contact_email);
		this.button = (Button) mActivity.findViewById(R.id.add_contact_button);
		mActivity.onStart();
	}

	public void testViewsAreOnScreen() {
		final View origin = mActivity.getWindow().getDecorView();
		assertOnScreen(origin, contactNameTextView);
		assertOnScreen(origin, contactEmailTextView);
		assertOnScreen(origin, contactPhoneTextView);
		assertOnScreen(origin, contactTypeSpinner);
		assertOnScreen(origin, button);
	}

	public void testSpinnerShowContactTypes() {
		assertThat(contactTypeSpinner).hasCount(3);
	}

	public void testThatEmailAndPhoneDoesntValidateIfNoNameIntroduced() {
		this.contactNameTextView.setText(NO_INPUT);

		this.button.performClick();

		verify(validator).isValidName(NO_INPUT);
	}

	public void testAllValidationAreExecutedWhenNameIsIntroduced() {
		this.contactNameTextView.setText(TEXT_NAME);
		this.contactEmailTextView.setText(NO_INPUT);
		this.contactPhoneTextView.setText(NO_INPUT);

		this.button.performClick();

		verify(validator).isValidName(TEXT_NAME);
		verify(validator).isValidEmail(NO_INPUT);
		verify(validator).isValidPhone(NO_INPUT);
	}

	public void testUserIsNotifiedForInvalidEmail() {
		this.contactNameTextView.setText(TEXT_NAME);
		this.contactEmailTextView.setText("invalid_email");

		this.button.performClick();

		assertThat(this.contactEmailTextView).hasError(R.string.mail_no_valido);
	}

	public void testUserIsNotifiedForInvalidPhone() {
		this.contactNameTextView.setText(TEXT_NAME);
		this.contactPhoneTextView.setText("invalid_phone");

		this.button.performClick();

		assertThat(this.contactPhoneTextView).hasError(
				R.string.telefono_invalido);
	}

	public void testWhenContactNameIntroducedCreateContactIsCalled() {
		this.contactNameTextView.setText(TEXT_NAME);

		this.button.performClick();

		assertCreatedContactIsCalled();
	}

	@SuppressWarnings("unchecked")
	private void assertCreatedContactIsCalled() {
		InOrder inOrder = Mockito.inOrder(spyReqBuilder, spySpiceManager);
		verify(spyReqBuilder).postRequestCreateContact(
				eq(getContactTypeSelected()), eq(TEXT_NAME));
		inOrder.verify(spySpiceManager).execute(notNull(SpiceRequest.class),
				notNull(RequestListener.class));
	}

	public void testWhenEmaiIsIntroducedCreateMailIsCalled() {
		this.contactNameTextView.setText(TEXT_NAME);
		this.contactEmailTextView.setText(TEXT_EMAIL);

		this.button.performClick();

		assertCreateEmailIsCalled();
	}

	@SuppressWarnings("unchecked")
	private void assertCreateEmailIsCalled() {
		InOrder inOrder = Mockito.inOrder(spyReqBuilder, spySpiceManager);
		inOrder.verify(spyReqBuilder).postRequestCreateEmail(
				CONTACT_CREATED_ID, TEXT_EMAIL);
		inOrder.verify(spySpiceManager).execute(notNull(SpiceRequest.class),
				notNull(RequestListener.class));
	}

	public void testWhenPhoneIsIntroducedCreatePhoneIsCalled() {
		this.contactNameTextView.setText(TEXT_NAME);
		this.contactPhoneTextView.setText(TEXT_PHONE);

		this.button.performClick();

		assertCreatePhoneIsCalled();
	}

	@SuppressWarnings("unchecked")
	private void assertCreatePhoneIsCalled() {
		InOrder inOrder = Mockito.inOrder(spyReqBuilder, spySpiceManager);
		inOrder.verify(spyReqBuilder).postRequestCreatePhone(
				CONTACT_CREATED_ID, TEXT_PHONE);
		inOrder.verify(spySpiceManager).execute(notNull(SpiceRequest.class),
				notNull(RequestListener.class));
	}

	@SuppressWarnings("unchecked")
	public void testWhenAllInputThreeRequestAreSended() {
		this.contactNameTextView.setText(TEXT_NAME);
		this.contactEmailTextView.setText(TEXT_EMAIL);
		this.contactPhoneTextView.setText(TEXT_PHONE);

		this.button.performClick();

		verify(spyReqBuilder)
				.postRequestCreateContact(anyString(), anyString());
		verify(spyReqBuilder).postRequestCreateEmail(anyInt(), anyString());
		verify(spyReqBuilder).postRequestCreatePhone(anyInt(), anyString());

		verify(spySpiceManager, times(3)).execute(notNull(SpiceRequest.class),
				notNull(RequestListener.class));
	}

	private String getContactTypeSelected() {
		return ((ContactType) this.contactTypeSpinner.getAdapter().getItem(0))
				.getName();
	}

}
