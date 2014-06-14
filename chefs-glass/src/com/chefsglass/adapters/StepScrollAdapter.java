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

package com.chefsglass.adapters;

import java.util.List;

import com.chefsglass.R;
import com.chefsglass.resources.Step;
import com.google.android.glass.widget.CardScrollAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Adapter for the {@link CardSrollView} inside {@link SelectValueActivity}.
 */
public class StepScrollAdapter extends CardScrollAdapter {

    private final Context mContext;
    private final List<Step> mSteps;

    public StepScrollAdapter(Context context, List<Step> steps) {
        mContext = context;
        mSteps = steps;
    }

    @Override
    public int getCount() {
        return mSteps.size();
    }

    @Override
    public Object getItem(int position) {
        return mSteps.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {    	
    	
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.card_step, parent);
        }

        Step step = (Step) getItem(position); 
        
        final FrameLayout layout = (FrameLayout) convertView.findViewById(R.id.layout);
        //layout.setBackground(background); postavi sliku
        
        final TextView description = (TextView) convertView.findViewById(R.id.description);
        description.setText(step.description);
        
        final TextView footer = (TextView) convertView.findViewById(R.id.footer);
        String stepText = mContext.getResources().getString(R.string.step_number);
        footer.setText(String.format(stepText, position,getCount()));
        
        
        if(step.durationMinutes != null){
        	final TextView duration = (TextView) convertView.findViewById(R.id.duration);
        String durationText = mContext.getResources().getString(R.string.duration);
        duration.setText(String.format(durationText, step.durationMinutes));
        }

        return convertView;
    }

    @Override
    public int getPosition(Object item) {
        if (item instanceof Step) {
        	return mSteps.indexOf(item);
        }
        return AdapterView.INVALID_POSITION;
    }
}
