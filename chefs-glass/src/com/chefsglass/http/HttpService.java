package com.chefsglass.http;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author Marko Pandurovic
 * 
 */
public class HttpService {

	private RestTemplate restTemplate;

	public HttpService() {
		restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
	}

	/**
	 * Sends POST request.
	 * 
	 * @param responseBody
	 *            expected body structure
	 * @param request
	 * @return
	 */
	public <T> ResponseEntity<T> post(Class<T> responseBody, BaseHttpRequest<T> request) {
		HttpEntity<BaseHttpRequest<T>> entity = new HttpEntity<BaseHttpRequest<T>>(request, request.headers());
		ResponseEntity<T> response = restTemplate.postForEntity(request.url(), entity, request.getResponseBody());
		return response;
	}

	/**
	 * Sends GET request.
	 * 
	 * @param responseBody
	 *            expected body structure
	 * @param request
	 * @return
	 */
	public <T> ResponseEntity<T> get(Class<T> responseBody, BaseHttpRequest<T> request) {
		ResponseEntity<T> response = restTemplate.getForEntity(request.url(), request.getResponseBody());
		return response;
	}

}
