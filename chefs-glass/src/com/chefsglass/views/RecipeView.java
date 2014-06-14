package com.chefsglass.views;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chefsglass.R;

public class RecipeView extends FrameLayout {

	private static final String TAG = RecipeView.class.getSimpleName();

	/**
	 * Interface to listen for changes on the view layout.
	 */
	public interface Listener {
		/** Notified of a change in the view. */
		public void onChange();
	}

	private TextView nameView;

	private Listener mChangeListener;

	private final Handler mHandler = new Handler();
	private final Runnable mUpdateTextRunnable = new Runnable() {

		@Override
		public void run() {
			Log.i(TAG, "runnable");
			updateText();
		}
	};

	public RecipeView(Context context) {
		super(context);
		Log.i(TAG, "Constructor");
		LayoutInflater.from(context).inflate(R.layout.card_recipe, this);

		nameView = (TextView) findViewById(R.id.name);
	}

	/**
	 * Sets a {@link Listener}.
	 */
	public void setListener(Listener listener) {
		mChangeListener = listener;
	}

	/**
	 * Returns the set {@link Listener}.
	 */
	public Listener getListener() {
		return mChangeListener;
	}

	void updateText() {
		Log.i(TAG, "Update text");
		nameView.setText("");
		if (mChangeListener != null) {
			Log.i(TAG, "on change");
			mChangeListener.onChange();
		}
	}

	/**
	 * Starts the chronometer.
	 */
	public void start() {
		Log.i(TAG, "Start");
		postDelayed(mUpdateTextRunnable, 50);
	}

	@Override
	public boolean postDelayed(Runnable action, long delayMillis) {
		return mHandler.postDelayed(action, delayMillis);
	}

	@Override
	public boolean removeCallbacks(Runnable action) {
		mHandler.removeCallbacks(action);
		return true;
	}

}
