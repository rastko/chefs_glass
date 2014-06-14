package com.chefsglass.resources;

import android.os.Parcel;
import android.os.Parcelable;


public class Ingredient implements Parcelable{
	
	public String id;
	public String description;
	public String amount;
	
	public Ingredient(Parcel in) {
		readFromParcel(in);
	}

	@Override
	public int describeContents() {
		//ignore
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(id);
		out.writeString(description);
		out.writeString(amount);
	}

	private void readFromParcel(Parcel in) { 
		id = in.readString();
		description = in.readString();
		amount = in.readString();
	}

	public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {
		public Ingredient createFromParcel(Parcel in) {
			return new Ingredient(in);
		}
		
		public Ingredient[] newArray(int size) {
			return new Ingredient[size];
		}
	};
	
}
