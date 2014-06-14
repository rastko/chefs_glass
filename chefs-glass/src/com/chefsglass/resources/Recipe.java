package com.chefsglass.resources;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import android.os.Parcel;
import android.os.Parcelable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe implements Parcelable {

	public String id;
	public String name;
	public String description;
	public String photoURL;
	public Integer servingsNo;
	public Integer durationMinutes;
	public Integer calories;

	public List<Step> steps;
	public List<Ingredient> ingredients;

	public Recipe(Parcel in) {
		readFromParcel(in);
	}

	@Override
	public int describeContents() {
		// ignore
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(id);
		out.writeString(name);
		out.writeString(description);
		out.writeString(photoURL);
		out.writeInt(servingsNo);
		out.writeInt(durationMinutes);
		out.writeInt(calories);
		out.writeList(steps);
		out.writeList(ingredients);
	}

	private void readFromParcel(Parcel in) {
		id = in.readString();
		name = in.readString();
		description = in.readString();
		photoURL = in.readString();
		servingsNo = in.readInt();
		durationMinutes = in.readInt();
		calories = in.readInt();

		steps = new ArrayList<Step>();
		in.readList(steps, Step.class.getClassLoader());

		ingredients = new ArrayList<Ingredient>();
		in.readList(ingredients, Ingredient.class.getClassLoader());
	}

	public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
		public Recipe createFromParcel(Parcel in) {
			return new Recipe(in);
		}

		public Recipe[] newArray(int size) {
			return new Recipe[size];
		}
	};

}
