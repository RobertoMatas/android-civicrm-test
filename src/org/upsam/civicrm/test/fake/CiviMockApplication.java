package org.upsam.civicrm.test.fake;

import java.util.Arrays;
import java.util.List;

import org.upsam.civicrm.dagger.di.AppicationInjectionAware;
import org.upsam.civicrm.test.dagger.modules.TestModule;

import android.test.mock.MockApplication;
import dagger.ObjectGraph;

public class CiviMockApplication extends MockApplication implements
		AppicationInjectionAware {

	private ObjectGraph applicationGraph;

	@Override
	public void onCreate() {
		//super.onCreate();
		applicationGraph = ObjectGraph.create(getModules().toArray());

	}

	protected List<Object> getModules() {
		return Arrays.<Object> asList(new TestModule());
	}

	@Override
	public ObjectGraph getApplicationGraph() {
		return applicationGraph;
	}

}
