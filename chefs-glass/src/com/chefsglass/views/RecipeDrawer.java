package com.chefsglass.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;

import com.google.android.glass.timeline.DirectRenderingCallback;
import com.google.android.glass.timeline.LiveCard;

public class RecipeDrawer implements DirectRenderingCallback {

	private static final String TAG = RecipeDrawer.class.getSimpleName();

	private SurfaceHolder mHolder;
	private boolean mRenderingPaused;

	private final RecipeView mRecipeView;

	private final RecipeView.Listener mRecipeListener = new RecipeView.Listener() {

		@Override
		public void onChange() {
			if (mHolder != null) {
				draw(mRecipeView);
			}
		}
	};

	public RecipeDrawer(Context context) {
		Log.i(TAG, "Constructor - RecipeDrawer");
		this.mRecipeView = new RecipeView(context);
		this.mRecipeView.setListener(mRecipeListener);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// The creation of a new Surface implicitly resumes the rendering.
		Log.i(TAG, "surfaceCreated - RecipeDrawer");
		mRenderingPaused = false;
		mHolder = holder;
		updateRenderingState();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// Measure and layout the view with the canvas dimensions.
		Log.i(TAG, "surfaceChanged - RecipeDrawer");
		int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
		int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);

		mRecipeView.measure(measuredWidth, measuredHeight);
		mRecipeView.layout(0, 0, mRecipeView.getMeasuredWidth(), mRecipeView.getMeasuredHeight());
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mHolder = null;
		updateRenderingState();
	}

	@Override
	public void renderingPaused(SurfaceHolder arg0, boolean paused) {
		mRenderingPaused = paused;
		updateRenderingState();
	}

	/**
	 * Starts or stops rendering according to the {@link LiveCard}'s state.
	 */
	private void updateRenderingState() {
		if (mHolder != null && !mRenderingPaused) {
			Log.i(TAG, "updateRenderingState - RecipeDrawer");
			mRecipeView.start();
		} else {
			// TODO: do something?
		}
	}

	/**
	 * Draws the view in the SurfaceHolder's canvas.
	 */
	private void draw(View view) {
		Canvas canvas;
		try {
			canvas = mHolder.lockCanvas();
		} catch (Exception e) {
			Log.e(TAG, "Unable to lock canvas: " + e);
			return;
		}
		if (canvas != null) {
			view.draw(canvas);
			mHolder.unlockCanvasAndPost(canvas);
		}
	}

}
