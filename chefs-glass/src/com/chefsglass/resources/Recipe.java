package com.chefsglass.resources;

import java.util.List;

public class Recipe {
	public String id;
	public String name;
	public String description;
	public String photoURL;
	public Integer servingsNo;
	public Integer durationMinutes;
	public Integer calories;
	public Integer stepsNo;
	
	List<Step> steps;
	List<Ingredient> ingredients;
}
