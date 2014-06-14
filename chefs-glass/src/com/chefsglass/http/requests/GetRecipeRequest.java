package com.chefsglass.http.requests;

import com.chefsglass.http.BaseHttpGetRequest;
import com.chefsglass.http.Routes;
import com.chefsglass.resources.Recipe;

public class GetRecipeRequest extends BaseHttpGetRequest<Recipe> {

	public GetRecipeRequest(Class<Recipe> responseBody) {
		super(responseBody);
	}

	private Long id;

	@Override
	public String url() {
		return String.format(Routes.GET_RECIPE_URL, id);
	}

}
