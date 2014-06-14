package com.chefsglass.http.requests;

import com.chefsglass.http.BaseHttpGetRequest;
import com.chefsglass.http.Routes;
import com.chefsglass.resources.Recipe;

/**
 * 
 * @author Marko Pandurovic
 *
 */
public class GetRecipeRequest extends BaseHttpGetRequest<Recipe> {

	private Long id;

	public GetRecipeRequest(Long id) {
		super(Recipe.class);
		this.id = id;
	}

	@Override
	public String url() {
		return String.format(Routes.GET_RECIPE_URL, id);
	}

}
