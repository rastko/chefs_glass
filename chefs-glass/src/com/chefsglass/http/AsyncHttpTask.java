package com.chefsglass.http;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import android.os.AsyncTask;

/**
 * AsyncTask which does an http request
 * 
 * @author Marko Pandurovic
 * 
 * @param <U>
 * @param <T>
 */
public abstract class AsyncHttpTask<T, U extends BaseHttpRequest<T>> extends AsyncTask<Void, Void, ResponseEntity<T>> {

	private HttpService httpService;
	private U request;

	public AsyncHttpTask(U request) {
		super();
		this.request = request;
		this.httpService = new HttpService();
	}

	@Override
	protected ResponseEntity<T> doInBackground(Void... params) {
		ResponseEntity<T> response = null;
		if (request.httpMethod() == HttpMethod.POST) {
			response = httpService.post(request.getResponseBody(), request);
		} else {
			response = httpService.get(request.getResponseBody(), request);
		}
		return response;
	}

	@Override
	protected final void onPostExecute(ResponseEntity<T> result) {
		super.onPostExecute(result);
		if (isSuccessfulCode(result.getStatusCode())) {
			onSuccess(result.getBody());
		} else {
			onFailure(result);
		}
	}

	private boolean isSuccessfulCode(HttpStatus httpStatus) {
		return HttpStatus.OK.equals(httpStatus) || HttpStatus.CREATED.equals(httpStatus);
	}

	protected abstract void onSuccess(T result);

	protected abstract void onFailure(ResponseEntity<T> result);

}
