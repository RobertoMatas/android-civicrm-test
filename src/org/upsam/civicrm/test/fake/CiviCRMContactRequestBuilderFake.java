package org.upsam.civicrm.test.fake;

import java.util.ArrayList;
import java.util.List;

import org.upsam.civicrm.activity.model.ListActivtiesSummary;
import org.upsam.civicrm.contact.model.address.ListAddresses;
import org.upsam.civicrm.contact.model.constant.Constant;
import org.upsam.civicrm.contact.model.contact.Contact;
import org.upsam.civicrm.contact.model.contact.ContactSummary;
import org.upsam.civicrm.contact.model.contact.ContactType;
import org.upsam.civicrm.contact.model.contact.ListContactType;
import org.upsam.civicrm.contact.model.contact.ListContacts;
import org.upsam.civicrm.contact.model.custom.HumanReadableValue;
import org.upsam.civicrm.contact.model.custom.ListCustomFields;
import org.upsam.civicrm.contact.model.custom.ListCustomValues;
import org.upsam.civicrm.contact.model.email.Email;
import org.upsam.civicrm.contact.model.email.ListEmails;
import org.upsam.civicrm.contact.model.groups.ListGroups;
import org.upsam.civicrm.contact.model.lang.PreferredLanguage;
import org.upsam.civicrm.contact.model.tags.ListTags;
import org.upsam.civicrm.contact.model.tags.Tag;
import org.upsam.civicrm.contact.model.telephone.ListPhones;
import org.upsam.civicrm.contact.model.telephone.Phone;
import org.upsam.civicrm.dagger.di.CiviCRMSpiceRequest;
import org.upsam.civicrm.rest.req.CiviCRMContactRequestBuilder;

public class CiviCRMContactRequestBuilderFake implements
		CiviCRMContactRequestBuilder {

	public static final int CONTACT_CREATED_ID = 100;

	@Override
	public CiviCRMSpiceRequest<Contact> requestContactById(int contactId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CiviCRMSpiceRequest<ListEmails> requestEmailsByContactId(
			int contactId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CiviCRMSpiceRequest<PreferredLanguage> requestCommunicationPreferencesByContactId(
			int contactId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CiviCRMSpiceRequest<ListTags> requestTagsByContactId(int contactId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CiviCRMSpiceRequest<Tag> requestTagById(int tagId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CiviCRMSpiceRequest<Constant> requestCountries() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CiviCRMSpiceRequest<Constant> requestLocationTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CiviCRMSpiceRequest<ListAddresses> requestContactAddresses(
			int contactId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CiviCRMSpiceRequest<ListCustomFields> requestCustomFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CiviCRMSpiceRequest<ListCustomValues> requestCustomValuesByContactId(
			int contactId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CiviCRMSpiceRequest<HumanReadableValue> requestHumanReadableValue(
			int optionGroupId, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CiviCRMSpiceRequest<ListContacts> requestListContact(int page,
			String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CiviCRMSpiceRequest<ListContactType> requestContactTypes() {
		final String[] types = { "Individual", "Organization", "Household" };
		final List<ContactType> values = new ArrayList<ContactType>(3);
		ContactType ct = null;
		for (int i = 0; i < types.length; i++) {
			ct = new ContactType();
			ct.setId(i + 1);
			ct.setLabel(types[i]);
			ct.setName(types[i]);
			values.add(ct);
		}
		return new CiviCRMAsyncRequestFake<ListContactType>(
				ListContactType.class) {

			@Override
			public ListContactType loadDataFromNetwork() throws Exception {
				ListContactType result = new ListContactType();
				result.setValues(values);
				return result;
			}
		};
	}

	@Override
	public CiviCRMSpiceRequest<ListContacts> postRequestCreateContact(
			String contactTypeSelected, String name) {
		return new CiviCRMAsyncRequestFake<ListContacts>(ListContacts.class) {
			@Override
			public ListContacts loadDataFromNetwork() throws Exception {
				ListContacts newContact = new ListContacts();
				List<ContactSummary> values = new ArrayList<ContactSummary>(1);
				ContactSummary contactCreated = new ContactSummary();
				contactCreated.setId(CONTACT_CREATED_ID);
				values.add(contactCreated);
				newContact.setValues(values);
				newContact.setCount(1);
				return newContact;
			}

		};
	}

	@Override
	public CiviCRMSpiceRequest<ListPhones> postRequestCreatePhone(
			int contactId, final String phone) {
		return new CiviCRMAsyncRequestFake<ListPhones>(ListPhones.class) {
			@Override
			public ListPhones loadDataFromNetwork() throws Exception {
				ListPhones phones = new ListPhones();
				List<Phone> values = new ArrayList<Phone>(1);
				Phone phoneCreated = new Phone();
				phoneCreated.setPhone(phone);
				phoneCreated.setPrimaryStr("1");
				values.add(phoneCreated);
				phones.setValues(values);
				return phones;
			}

		};
	}

	@Override
	public CiviCRMSpiceRequest<ListEmails> postRequestCreateEmail(
			int contactId, final String email) {
		return new CiviCRMAsyncRequestFake<ListEmails>(ListEmails.class) {
			@Override
			public ListEmails loadDataFromNetwork() throws Exception {
				ListEmails emails = new ListEmails();
				List<Email> values = new ArrayList<Email>(1);
				Email emailCreated = new Email();
				emailCreated.setEmail(email);
				emailCreated.setPrimaryStr("1");
				values.add(emailCreated);
				emails.setValues(values);
				return emails;
			}

		};
	}

	@Override
	public CiviCRMSpiceRequest<ListPhones> requestPhonesByContactId(
			int contactId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CiviCRMSpiceRequest<ListGroups> requestGroupByContactId(int contactId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CiviCRMSpiceRequest<ListActivtiesSummary> requestActivitiesForContact(
			int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
