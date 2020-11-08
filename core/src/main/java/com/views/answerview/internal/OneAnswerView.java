package com.views.answerview.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.R;
import com.views.answerview.LAnswerView;

/**
 * Created by buraktamturk on 21/04/15.
 */

//TODO convert kotlin
public class OneAnswerView extends LinearLayout {
    public OneAnswerView(Context context) {
        super(context);
        init();
    }

    public OneAnswerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OneAnswerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public OneAnswerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public boolean active;

    protected void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.l_answer_view, this, true);
    }

    public void setActive(boolean active, LAnswerView aw) {
        if (active != this.active) {
            this.active = active;
            if (active) {
                if (aw.aw_ShowTextWhenActive) {
                    ((TextView) findViewById(R.id.chooice)).setTextColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    findViewById(R.id.chooice).setVisibility(INVISIBLE);
                }

                getChildAt(0).setBackgroundDrawable(getResources().getDrawable(R.drawable.l_answer_circle_ac));
            } else {
                if (aw.aw_ShowTextWhenActive) {
                    ((TextView) findViewById(R.id.chooice)).setTextColor(getResources().getColor(R.color.black));
                } else {
                    findViewById(R.id.chooice).setVisibility(VISIBLE);
                }

                getChildAt(0).setBackgroundDrawable(getResources().getDrawable(R.drawable.l_answer_circle));
            }
        }
    }

    public void setIndex(int index) {
        ((TextView) findViewById(R.id.chooice)).setText(String.valueOf((char) (((int) 'A') + index)));
    }
}
