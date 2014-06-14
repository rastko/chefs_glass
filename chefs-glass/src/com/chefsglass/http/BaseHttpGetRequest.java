package com.chefsglass.http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 * 
 * @author Marko Pandurovic
 *
 * @param <T>
 */
public abstract class BaseHttpGetRequest<T> extends BaseHttpRequest<T> {

	public BaseHttpGetRequest(Class<T> responseBody) {
		super(responseBody);
	}

	public HttpHeaders headers() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

	public HttpMethod httpMethod() {
		return HttpMethod.GET;
	}

}
