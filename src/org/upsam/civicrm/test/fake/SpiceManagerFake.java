package org.upsam.civicrm.test.fake;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

import android.content.Context;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.SpiceService;
import com.octo.android.robospice.persistence.exception.CacheLoadingException;
import com.octo.android.robospice.request.CachedSpiceRequest;
import com.octo.android.robospice.request.SpiceRequest;
import com.octo.android.robospice.request.listener.RequestListener;

public class SpiceManagerFake extends SpiceManager {

	public class NotImplementedException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 3501636710590447036L;
		private static final String MSG = "Esto es una implementación falsa del SpiceManager, sólo para testing.";

		/**
		 * 
		 */
		public NotImplementedException() {
			super(MSG);
		}

		/**
		 * @param message
		 */
		public NotImplementedException(String method) {
			super("El método " + method
					+ " fue invocado y no tiene una implementación\nNOTA:"
					+ MSG);
		}

	}

	private boolean isStarted = false;
	private final CountDownLatch latch;
	private boolean isAsyncMode = false;

	public SpiceManagerFake(Class<? extends SpiceService> spiceServiceClass) {
		super(spiceServiceClass);
		this.isAsyncMode = false;
		this.latch = null;
	}

	public SpiceManagerFake(Class<? extends SpiceService> spiceServiceClass,
			final CountDownLatch latch) {
		super(spiceServiceClass);
		this.latch = latch;
		this.isAsyncMode = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.octo.android.robospice.SpiceManager#start(android.content.Context)
	 */
	@Override
	public synchronized void start(Context context) {
		this.isStarted = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.octo.android.robospice.SpiceManager#isStarted()
	 */
	@Override
	public synchronized boolean isStarted() {
		return this.isStarted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.octo.android.robospice.SpiceManager#run()
	 */
	@Override
	public void run() {
		throw new NotImplementedException("run()");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.octo.android.robospice.SpiceManager#shouldStop()
	 */
	@Override
	public synchronized void shouldStop() {
		this.isStarted = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.octo.android.robospice.SpiceManager#shouldStopAndJoin(long)
	 */
	@Override
	public synchronized void shouldStopAndJoin(long timeOut)
			throws InterruptedException {
		this.isStarted = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.octo.android.robospice.SpiceManager#getFromCache(java.lang.Class,
	 * java.lang.String, long,
	 * com.octo.android.robospice.request.listener.RequestListener)
	 */
	@Override
	public <T> void getFromCache(Class<T> clazz, String requestCacheKey,
			long cacheExpiryDuration, RequestListener<T> requestListener) {
		throw new NotImplementedException(
				"getFromCache(Class<T> clazz, String requestCacheKey, long cacheExpiryDuration, RequestListener<T> requestListener)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.octo.android.robospice.SpiceManager#addListenerIfPending(java.lang
	 * .Class, java.lang.String,
	 * com.octo.android.robospice.request.listener.RequestListener)
	 */
	@Override
	public <T> void addListenerIfPending(Class<T> clazz,
			String requestCacheKey, RequestListener<T> requestListener) {
		throw new NotImplementedException(
				"addListenerIfPending(Class<T> clazz, String requestCacheKey, RequestListener<T> requestListener)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.octo.android.robospice.SpiceManager#execute(com.octo.android.robospice
	 * .request.SpiceRequest,
	 * com.octo.android.robospice.request.listener.RequestListener)
	 */
	@Override
	public <T> void execute(SpiceRequest<T> request,
			RequestListener<T> requestListener) {
		fakeExecution(request, requestListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.octo.android.robospice.SpiceManager#execute(com.octo.android.robospice
	 * .request.SpiceRequest, java.lang.Object, long,
	 * com.octo.android.robospice.request.listener.RequestListener)
	 */
	@Override
	public <T> void execute(SpiceRequest<T> request, Object requestCacheKey,
			long cacheExpiryDuration, RequestListener<T> requestListener) {
		fakeExecution(request, requestListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.octo.android.robospice.SpiceManager#execute(com.octo.android.robospice
	 * .request.CachedSpiceRequest,
	 * com.octo.android.robospice.request.listener.RequestListener)
	 */
	@Override
	public <T> void execute(CachedSpiceRequest<T> cachedSpiceRequest,
			RequestListener<T> requestListener) {
		fakeExecution(cachedSpiceRequest, requestListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.octo.android.robospice.SpiceManager#cancel(java.lang.Class,
	 * java.lang.String)
	 */
	@Override
	public <T> void cancel(Class<T> clazz, String requestCacheKey) {
		throw new NotImplementedException(
				"cancel(Class<T> clazz, String requestCacheKey)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.octo.android.robospice.SpiceManager#dontNotifyRequestListenersForRequest
	 * (com.octo.android.robospice.request.SpiceRequest)
	 */
	@Override
	public void dontNotifyRequestListenersForRequest(SpiceRequest<?> request) {
		throw new NotImplementedException(
				"dontNotifyRequestListenersForRequest(SpiceRequest<?> request)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.octo.android.robospice.SpiceManager#
	 * dontNotifyRequestListenersForRequestInternal
	 * (com.octo.android.robospice.request.SpiceRequest)
	 */
	@Override
	protected void dontNotifyRequestListenersForRequestInternal(
			SpiceRequest<?> request) {
		throw new NotImplementedException(
				"dontNotifyRequestListenersForRequestInternal(SpiceRequest<?> request)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.octo.android.robospice.SpiceManager#dontNotifyAnyRequestListeners()
	 */
	@Override
	public void dontNotifyAnyRequestListeners() {
		throw new NotImplementedException("dontNotifyAnyRequestListeners()");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.octo.android.robospice.SpiceManager#dontNotifyAnyRequestListenersInternal
	 * ()
	 */
	@Override
	protected void dontNotifyAnyRequestListenersInternal() {
		throw new NotImplementedException(
				"dontNotifyAnyRequestListenersInternal()");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.octo.android.robospice.SpiceManager#cancel(com.octo.android.robospice
	 * .request.SpiceRequest)
	 */
	@Override
	public void cancel(SpiceRequest<?> request) {
		throw new NotImplementedException("cancel(SpiceRequest<?> request)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.octo.android.robospice.SpiceManager#cancelAllRequests()
	 */
	@Override
	public void cancelAllRequests() {
		throw new NotImplementedException("cancelAllRequests()");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.octo.android.robospice.SpiceManager#getAllCacheKeys(java.lang.Class)
	 */
	@Override
	public <T> Future<List<Object>> getAllCacheKeys(Class<T> clazz) {
		throw new NotImplementedException("getAllCacheKeys(Class<T> clazz)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.octo.android.robospice.SpiceManager#getAllDataFromCache(java.lang
	 * .Class)
	 */
	@Override
	public <T> Future<List<T>> getAllDataFromCache(Class<T> clazz)
			throws CacheLoadingException {
		throw new NotImplementedException("getAllDataFromCache(Class<T> clazz)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.octo.android.robospice.SpiceManager#getDataFromCache(java.lang.Class,
	 * java.lang.String)
	 */
	@Override
	public <T> Future<T> getDataFromCache(Class<T> clazz, String cacheKey)
			throws CacheLoadingException {
		throw new NotImplementedException(
				"getDataFromCache(Class<T> clazz, String cacheKey)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.octo.android.robospice.SpiceManager#removeDataFromCache(java.lang
	 * .Class, java.lang.Object)
	 */
	@Override
	public <T> void removeDataFromCache(Class<T> clazz, Object cacheKey) {
		throw new NotImplementedException(
				"removeDataFromCache(Class<T> clazz, Object cacheKey)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.octo.android.robospice.SpiceManager#removeDataFromCache(java.lang
	 * .Class)
	 */
	@Override
	public <T> void removeDataFromCache(Class<T> clazz) {
		throw new NotImplementedException("removeDataFromCache(Class<T> clazz)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.octo.android.robospice.SpiceManager#removeAllDataFromCache()
	 */
	@Override
	public void removeAllDataFromCache() {
		throw new NotImplementedException("removeAllDataFromCache()");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.octo.android.robospice.SpiceManager#setFailOnCacheError(boolean)
	 */
	@Override
	public void setFailOnCacheError(boolean failOnCacheError) {
		throw new NotImplementedException(
				"setFailOnCacheError(boolean failOnCacheError)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.octo.android.robospice.SpiceManager#dumpState()
	 */
	@Override
	public void dumpState() {
		throw new NotImplementedException("dumpState()");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.octo.android.robospice.SpiceManager#waitForServiceToBeBound()
	 */
	@Override
	protected void waitForServiceToBeBound() throws InterruptedException {
		throw new NotImplementedException("waitForServiceToBeBound()");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.octo.android.robospice.SpiceManager#waitForServiceToBeUnbound()
	 */
	@Override
	protected void waitForServiceToBeUnbound() throws InterruptedException {
		throw new NotImplementedException("waitForServiceToBeUnbound()");
	}

	private <T> void fakeExecution(final SpiceRequest<T> request,
			final RequestListener<T> requestListener) {
		if (isAsyncMode) {
			fakeAsyncExecution(request, requestListener);
		} else {
			fakeSyncExecution(request, requestListener);
		}
	}

	private <T> void fakeSyncExecution(final SpiceRequest<T> request,
			final RequestListener<T> requestListener) {
		try {
			requestListener.onRequestSuccess(request.loadDataFromNetwork());

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private <T> void fakeAsyncExecution(final SpiceRequest<T> request,
			final RequestListener<T> requestListener) {
		if (!isStarted)
			throw new IllegalStateException("SpiceManager no esta arrancado!");
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					requestListener.onRequestSuccess(request
							.loadDataFromNetwork());
				} catch (Exception e) {
					throw new RuntimeException(e);
				} finally {
					latch.countDown();
				}

			}
		};
		new Thread(runnable).start();
	}

}
