package com.chefsglass.http;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

/**
 * 
 * @author Marko Pandurovic
 * 
 * @param <T>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseHttpRequest<T> {

	/** should not be serialized */
	private final Class<T> responseBody;

	public BaseHttpRequest(Class<T> responseBody) {
		super();
		this.responseBody = responseBody;
	}

	public abstract String url();

	public abstract HttpHeaders headers();

	public abstract HttpMethod httpMethod();

	public Class<T> getResponseBody() {
		return responseBody;
	}

}