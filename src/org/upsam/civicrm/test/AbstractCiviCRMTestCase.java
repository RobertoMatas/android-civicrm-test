package org.upsam.civicrm.test;

import org.upsam.civicrm.test.fake.CiviMockApplication;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import dagger.ObjectGraph;

public abstract class AbstractCiviCRMTestCase<T extends Activity> extends
		ActivityUnitTestCase<T> {

	/**
	 * SUT
	 */
	protected T mActivity;

	private Class<T> activityClass;

	public AbstractCiviCRMTestCase(Class<T> activityClass) {
		super(activityClass);
		this.activityClass = activityClass;
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		startActivity();
	}

	private void startActivity() throws Exception {
		CiviMockApplication mockApplication = new CiviMockApplication();
		mockApplication.onCreate();
		setApplication(mockApplication);
		ObjectGraph objectGraph = mockApplication.getApplicationGraph();
		objectGraph.inject(this);
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				activityClass);
		mActivity = startActivity(intent, null, null);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}