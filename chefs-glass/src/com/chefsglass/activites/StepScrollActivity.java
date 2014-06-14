package com.chefsglass.activites;

import com.chefsglass.adapters.StepScrollAdapter;
import com.chefsglass.resources.Recipe;
import com.google.android.glass.media.Sounds;
import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.widget.CardScrollView;

public class StepScrollActivity extends BaseScrollActivity {
	
	public static final String EXTRA_RECIPE = "recipe";

	@Override
	public boolean onGesture(Gesture gesture) {
		switch (gesture) {
		
		//TODO: add more gestures
        case SWIPE_DOWN:
            setResultInternal(RESULT_CANCELED, null);
            playSoundEffect(Sounds.DISMISSED);
            finish();
            return true;

		default:
			return false;
		}				
	}

	@Override
	protected void setAdapter(CardScrollView view) {
		Recipe recipe = getIntent().getParcelableExtra(EXTRA_RECIPE);		
		StepScrollAdapter adapter = new StepScrollAdapter(this, recipe);
		view.setAdapter(adapter);
	}

}
