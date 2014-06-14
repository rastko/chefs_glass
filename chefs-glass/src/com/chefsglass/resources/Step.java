package com.chefsglass.resources;

import android.os.Parcel;
import android.os.Parcelable;

public class Step implements Parcelable {

	public String id;
	public Integer stepNo;
	public String description;
	public Integer durationMinutes;

	public Step(Parcel in) {
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
		out.writeInt(stepNo);
		out.writeString(description);
		out.writeInt(durationMinutes);
	}

	private void readFromParcel(Parcel in) { 
		id = in.readString();
		stepNo = in.readInt();
		description = in.readString();
		durationMinutes = in.readInt();
	}

	public static final Parcelable.Creator<Step> CREATOR = new Parcelable.Creator<Step>() {
		public Step createFromParcel(Parcel in) {
			return new Step(in);
		}

		public Step[] newArray(int size) {
			return new Step[size];
		}
	};
}
