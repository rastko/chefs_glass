/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.chefsglass;

import org.springframework.http.ResponseEntity;

import com.chefsglass.http.AsyncHttpTask;
import com.chefsglass.http.HttpService;
import com.chefsglass.http.requests.GetRecipeRequest;
import com.chefsglass.resources.Recipe;
import com.chefsglass.views.RecipeDrawer;
import com.google.android.glass.timeline.LiveCard;
import com.google.android.glass.timeline.LiveCard.PublishMode;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Service owning the LiveCard living in the timeline.
 */
public class StopwatchService extends Service {

	private static final String LIVE_CARD_TAG = "recipe";

	private RecipeDrawer mCallback;

	private LiveCard mLiveCard;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (mLiveCard == null) {
			mLiveCard = new LiveCard(this, LIVE_CARD_TAG);
			new GetRecipeTask(new GetRecipeRequest(1l)).execute();

			// Keep track of the callback to remove it before unpublishing.
			mCallback = new RecipeDrawer(this);
			mLiveCard.setDirectRenderingEnabled(true).getSurfaceHolder().addCallback(mCallback);

			Intent menuIntent = new Intent(this, MenuActivity.class);
			menuIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
			mLiveCard.setAction(PendingIntent.getActivity(this, 0, menuIntent, 0));
			mLiveCard.attach(this);
			mLiveCard.publish(PublishMode.REVEAL);
		} else {
			mLiveCard.navigate();
		}

		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		if (mLiveCard != null && mLiveCard.isPublished()) {
			mLiveCard.unpublish();
			mLiveCard = null;
		}
		super.onDestroy();
	}

	public class GetRecipeTask extends AsyncHttpTask<Recipe, GetRecipeRequest> {

		public GetRecipeTask(GetRecipeRequest request) {
			super(request);
		}

		@Override
		protected void onSuccess(Recipe result) {
			Log.i("Recipe id: %s", result.id);
		}

		@Override
		protected void onFailure(ResponseEntity<Recipe> result) {
			Log.e("Error occured", "something");
		}

	}
}
