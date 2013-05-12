package org.upsam.civicrm.test.fake;

import org.upsam.civicrm.dagger.di.CiviCRMSpiceRequest;

public abstract class CiviCRMAsyncRequestFake<RESULT> extends CiviCRMSpiceRequest<RESULT> {

	public CiviCRMAsyncRequestFake(Class<RESULT> clazz) {
		super(clazz);
	}

	@Override
	public String createCacheKey() {
		return "";
	}

	@Override
	public String getUriReq() {
		return "";
	}

	public abstract RESULT loadDataFromNetwork() throws Exception;

}
