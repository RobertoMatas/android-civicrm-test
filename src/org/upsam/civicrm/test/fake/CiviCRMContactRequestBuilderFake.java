package org.upsam.civicrm.test.fake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.upsam.civicrm.activity.model.ActivitySummary;
import org.upsam.civicrm.activity.model.ListActivtiesSummary;
import org.upsam.civicrm.contact.model.address.Address;
import org.upsam.civicrm.contact.model.address.ListAddresses;
import org.upsam.civicrm.contact.model.constant.Constant;
import org.upsam.civicrm.contact.model.contact.Contact;
import org.upsam.civicrm.contact.model.contact.ContactSummary;
import org.upsam.civicrm.contact.model.contact.ContactType;
import org.upsam.civicrm.contact.model.contact.Individual;
import org.upsam.civicrm.contact.model.contact.ListContactType;
import org.upsam.civicrm.contact.model.contact.ListContacts;
import org.upsam.civicrm.contact.model.custom.CustomField;
import org.upsam.civicrm.contact.model.custom.CustomValue;
import org.upsam.civicrm.contact.model.custom.HumanReadableValue;
import org.upsam.civicrm.contact.model.custom.ListCustomFields;
import org.upsam.civicrm.contact.model.custom.ListCustomValues;
import org.upsam.civicrm.contact.model.email.Email;
import org.upsam.civicrm.contact.model.email.ListEmails;
import org.upsam.civicrm.contact.model.groups.Group;
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

	private static final int CONTACT_ID = 1;
	public static final int CONTACT_CREATED_ID = 100;

	@Override
	public CiviCRMSpiceRequest<Contact> requestContactById(int contactId) {
		return new CiviCRMAsyncRequestFake<Contact>(Contact.class) {

			@Override
			public Contact loadDataFromNetwork() throws Exception {
				Contact contact = new Individual();
				contact.setId(CONTACT_ID);
				contact.setName("Test Name");
				contact.setDoNotEmail('1');
				contact.setDoNotPhone('1');
				contact.setBirthDate("10-10-1951 00:00:00");
				contact.setIsDeceased('1');
				contact.setDeceasedDate("10-10-2013 00:00:00");
				contact.setGender("Male");
				return contact;
			}

		};
	}

	@Override
	public CiviCRMSpiceRequest<ListEmails> requestEmailsByContactId(
			int contactId) {
		return new CiviCRMAsyncRequestFake<ListEmails>(ListEmails.class) {

			@Override
			public ListEmails loadDataFromNetwork() throws Exception {
				ListEmails listEmails = new ListEmails();
				List<Email> emails = new ArrayList<Email>(2);
				Email email = new Email();
				email.setEmail("test1@email.com");
				email.setPrimaryStr("1");
				emails.add(email);
				email = new Email();
				email.setEmail("test2@email.com");
				email.setPrimaryStr("0");
				emails.add(email);
				listEmails.setValues(emails);
				return listEmails;
			}

		};
	}

	@Override
	public CiviCRMSpiceRequest<PreferredLanguage> requestCommunicationPreferencesByContactId(
			int contactId) {
		return new CiviCRMAsyncRequestFake<PreferredLanguage>(
				PreferredLanguage.class) {

			@Override
			public PreferredLanguage loadDataFromNetwork() throws Exception {
				PreferredLanguage pl = new PreferredLanguage();
				pl.setContactId(CONTACT_ID);
				pl.setPreferredLanguage("es_ES");
				return pl;
			}

		};
	}

	@Override
	public CiviCRMSpiceRequest<ListTags> requestTagsByContactId(int contactId) {
		return new CiviCRMAsyncRequestFake<ListTags>(ListTags.class) {

			@Override
			public ListTags loadDataFromNetwork() throws Exception {
				ListTags listTags = new ListTags();
				List<Tag> tags = new ArrayList<Tag>(2);
				Tag tag = null;
				for (int i = 1; i < 3; i++) {
					tag = new Tag();
					tag.setId(i);
					tag.setName("Tag " + i);
					tag.setTagId(i);
					tag.setDescription("Tag Description " + i);
					tags.add(tag);
				}
				listTags.setValues(tags);
				listTags.setCount(2);
				return listTags;
			}

		};
	}

	@Override
	public CiviCRMSpiceRequest<Tag> requestTagById(final int tagId) {
		return new CiviCRMAsyncRequestFake<Tag>(Tag.class) {

			@Override
			public Tag loadDataFromNetwork() throws Exception {
				Tag tag = new Tag();
				tag.setId(tagId);
				tag.setTagId(tagId);
				tag.setDescription("Tag Description " + tagId);
				tag.setName("Tag " + tagId);
				return tag;
			}

		};
	}

	@Override
	public CiviCRMSpiceRequest<Constant> requestCountries() {
		return new CiviCRMAsyncRequestFake<Constant>(Constant.class) {

			@Override
			public Constant loadDataFromNetwork() throws Exception {
				Constant countries = new Constant();
				Map<String, String> values = new HashMap<String, String>(3);
				values.put("1", "Spain");
				values.put("2", "EEUU");
				values.put("3", "Cuba");
				countries.setValues(values);
				return countries;
			}

		};
	}

	@Override
	public CiviCRMSpiceRequest<Constant> requestLocationTypes() {
		return new CiviCRMAsyncRequestFake<Constant>(Constant.class) {

			@Override
			public Constant loadDataFromNetwork() throws Exception {
				Constant locations = new Constant();
				Map<String, String> values = new HashMap<String, String>(2);
				values.put("1", "Home");
				values.put("2", "Billing");
				locations.setValues(values);
				return locations;
			}

		};
	}

	@Override
	public CiviCRMSpiceRequest<ListAddresses> requestContactAddresses(
			int contactId) {
		return new CiviCRMAsyncRequestFake<ListAddresses>(ListAddresses.class) {

			@Override
			public ListAddresses loadDataFromNetwork() throws Exception {
				ListAddresses listAddresses = new ListAddresses();
				List<Address> addresses = new ArrayList<Address>(2);
				Address address = null;
				for (int i = 0; i < 2; i++) {
					address = new Address();
					address.setCity("City " + i);
					address.setAddress("My address " + i);
					address.setCountryId("1");
					address.setLocationTypeId("1");
					address.setSupplementalAddress("Suppl. address " + i);
					address.setZipCode("5845 " + i);
					addresses.add(address);
				}
				listAddresses.setValues(addresses);
				return listAddresses;
			}

		};
	}

	@Override
	public CiviCRMSpiceRequest<ListCustomFields> requestCustomFields() {
		return new CiviCRMAsyncRequestFake<ListCustomFields>(
				ListCustomFields.class) {

			@Override
			public ListCustomFields loadDataFromNetwork() throws Exception {
				ListCustomFields listCustomFields = new ListCustomFields();
				List<CustomField> customFields = new ArrayList<CustomField>(2);
				CustomField customField = null;
				for (int i = 1; i < 3; i++) {
					customField = new CustomField();
					customField.setId(i);
					customField.setLabel("Label " + i);
					customField.setOptionGroupId(i);
					customFields.add(customField);
				}
				listCustomFields.setValues(customFields);
				return listCustomFields;
			}

		};
	}

	@Override
	public CiviCRMSpiceRequest<ListCustomValues> requestCustomValuesByContactId(
			int contactId) {
		return new CiviCRMAsyncRequestFake<ListCustomValues>(
				ListCustomValues.class) {

			@Override
			public ListCustomValues loadDataFromNetwork() throws Exception {
				ListCustomValues listCustomValues = new ListCustomValues();
				List<CustomValue> customValues = new ArrayList<CustomValue>(2);
				CustomValue customValue = null;
				for (int i = 1; i < 3; i++) {
					customValue = new CustomValue();
					customValue.setId(i);
					customValue.setValue("Value " + i);
					customValues.add(customValue);
				}
				listCustomValues.setValues(customValues);
				return listCustomValues;
			}

		};
	}

	@Override
	public CiviCRMSpiceRequest<HumanReadableValue> requestHumanReadableValue(
			int optionGroupId, String value) {
		return new CiviCRMAsyncRequestFake<HumanReadableValue>(
				HumanReadableValue.class) {

			@Override
			public HumanReadableValue loadDataFromNetwork() throws Exception {
				HumanReadableValue humanReadableValue = new HumanReadableValue();
				humanReadableValue.setLabel("Label human readable");
				return humanReadableValue;
			}

		};
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
		return new CiviCRMAsyncRequestFake<ListPhones>(ListPhones.class) {

			@Override
			public ListPhones loadDataFromNetwork() throws Exception {
				ListPhones listPhones = new ListPhones();
				List<Phone> phones = new ArrayList<Phone>(2);
				Phone phone = new Phone();
				phone.setPhone("66589512");
				phone.setPrimaryStr("1");
				phones.add(phone);
				phone = new Phone();
				phone.setPhone("6549846");
				phone.setPrimaryStr("0");
				phones.add(phone);
				listPhones.setValues(phones);
				return listPhones;
			}

		};
	}

	@Override
	public CiviCRMSpiceRequest<ListGroups> requestGroupByContactId(int contactId) {
		return new CiviCRMAsyncRequestFake<ListGroups>(ListGroups.class) {

			@Override
			public ListGroups loadDataFromNetwork() throws Exception {
				ListGroups listGroups = new ListGroups();
				List<Group> groups = new ArrayList<Group>(2);
				Group group = null;
				for (int i = 1; i < 3; i++) {
					group = new Group();
					group.setTitle("Grupo " + i);
					group.setInDate("12-12-2012 00:00:00");
					group.setInMethod("Admin");
					groups.add(group);
				}
				listGroups.setValues(groups);
				return listGroups;
			}

		};
	}

	@Override
	public CiviCRMSpiceRequest<ListActivtiesSummary> requestActivitiesForContact(
			int id) {
		return new CiviCRMAsyncRequestFake<ListActivtiesSummary>(
				ListActivtiesSummary.class) {

			@Override
			public ListActivtiesSummary loadDataFromNetwork() throws Exception {
				ListActivtiesSummary listActivtiesSummary = new ListActivtiesSummary();
				listActivtiesSummary.setCount(3);
				List<ActivitySummary> activities = new ArrayList<ActivitySummary>(
						3);
				listActivtiesSummary.setValues(activities);
				ActivitySummary activitySummary = null;
				for (int i = 1; i < 4; i++) {
					activitySummary = new ActivitySummary();
					activitySummary.setDateTime("12-12-2012 00:00:00");
					activitySummary.setName("Activity " + i);
					activitySummary.setSubject("Activity Subject " + i);
					activities.add(activitySummary);
				}
				listActivtiesSummary.setValues(activities);
				return listActivtiesSummary;
			}

		};
	}

}
