package org.upsam.civicrm.test;

import android.os.Parcelable;

public class Extra {

	public final String key;

	public final Parcelable data;

	/**
	 * @param key
	 * @param data
	 */
	public Extra(String key, Parcelable data) {
		super();
		this.key = key;
		this.data = data;
	}

}
