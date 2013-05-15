package org.upsam.civicrm.test;

import static org.fest.assertions.api.ANDROID.assertThat;

import javax.inject.Inject;

import org.upsam.civicrm.R;
import org.upsam.civicrm.contact.detail.ContactDetailFragmentActivity;
import org.upsam.civicrm.contact.model.contact.ContactSummary;
import org.upsam.civicrm.dagger.di.fragment.SpiceDIAwareFragment;
import org.upsam.civicrm.rest.req.CiviCRMContactRequestBuilder;

import android.content.res.Resources;
import android.view.View;

import com.octo.android.robospice.SpiceManager;

public abstract class AbstractCiviCRMContactFragmentTestCase<FRAGMENT extends SpiceDIAwareFragment>
		extends AbstractCiviCRMTestCase<ContactDetailFragmentActivity> {

	protected static final String TEST_CONTACT_TYPE = "Individual";
	protected static final String TEST_CONTACT_NAME = "Test Name";
	protected static final int TEST_CONTACT_ID = 1;

	FRAGMENT fragment;

	/**
	 * 
	 */
	View fragmentLayout;

	@Inject
	SpiceManager spySpiceManager;

	@Inject
	CiviCRMContactRequestBuilder spyRequestBuilder;

	/**
	 * 
	 * @param name
	 */
	public AbstractCiviCRMContactFragmentTestCase(String name) {
		super(ContactDetailFragmentActivity.class);
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
		if (createFragment()) {
			fragment = newFragmentInstance();
			fragment.setArguments(mActivity.getIntent().getExtras());
			mActivity
					.getSupportFragmentManager()
					.beginTransaction()
					.add(R.id.FrameLayout2, fragment, getFragmentTagName())
					.commit();
			mActivity.onStart();
			fragmentLayout = fragment.getView();
		}
	}
	
	protected abstract boolean createFragment();
	
	protected abstract String getFragmentTagName();

	protected abstract FRAGMENT newFragmentInstance();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.upsam.civicrm.test.AbstractCiviCRMTestCase#getExtraForIntent()
	 */
	@Override
	protected final Extra getExtraForIntent() {
		ContactSummary data = new ContactSummary();
		data.setId(TEST_CONTACT_ID);
		data.setName(TEST_CONTACT_NAME);
		data.setType(TEST_CONTACT_TYPE);
		Extra extra = new Extra("contact", data);
		return extra;
	}

	protected final int indexForItem(int itemResource) {
		Resources resources = mActivity.getResources();
		String[] options = resources
				.getStringArray(R.array.slide_individual_menu);
		for (int i = 0; i < options.length; i++) {
			if (options[i].equals(resources.getString(itemResource))) {
				return i;
			}
		}
		return -1;
	}

	public void testFragmentPresence() {
		assertThat(fragment).isNotNull().isAdded();
	}
}
