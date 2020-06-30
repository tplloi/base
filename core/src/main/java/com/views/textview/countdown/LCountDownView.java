package com.views.textview.countdown;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.R;
import com.core.utilities.LAnimationUtil;
import com.core.utilities.LLog;
import com.daimajia.androidanimations.library.Techniques;

public class LCountDownView extends RelativeLayout {
    private final String TAG = getClass().getSimpleName();
    private TextView tvCountDown;

    public LCountDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LCountDownView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_l_count_down, this);
        this.tvCountDown = findViewById(R.id.tv_count_down);
    }

    public void start(int number) {
        this.number = number;
        doPerSec();
    }

    public void setShowOrHide(boolean isShow) {
        if (tvCountDown != null) {
            tvCountDown.setVisibility(isShow ? VISIBLE : INVISIBLE);
        }
    }

    private int number;

    public interface Callback {
        void onTick();

        void onEnd();
    }

    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    private void doPerSec() {
        if (tvCountDown != null) {
            tvCountDown.setText(String.valueOf(number));
            LAnimationUtil.Companion.playDuration(tvCountDown, Techniques.FlipInX, 1000, new LAnimationUtil.Callback() {
                @Override
                public void onCancel() {
                    //do nothing
                }

                @Override
                public void onEnd() {
                    number--;
                    //LLog.d(TAG, "number " + number);
                    if (number == 0) {
                        tvCountDown.setText("GO");
                        LAnimationUtil.Companion.play(tvCountDown, Techniques.Flash, new LAnimationUtil.Callback() {
                            @Override
                            public void onCancel() {
                                //do nothing
                            }

                            @Override
                            public void onEnd() {
                                tvCountDown.setVisibility(GONE);
                                //tvCountDown = null;
                                LLog.d(TAG, "number == 0 -> STOP");
                                if (callback != null) {
                                    callback.onEnd();
                                }
                            }

                            @Override
                            public void onRepeat() {
                                //do nothing
                            }

                            @Override
                            public void onStart() {
                                //do nothing
                            }
                        });
                        return;
                    }
                    doPerSec();
                }

                @Override
                public void onRepeat() {
                    //do nothing
                }

                @Override
                public void onStart() {
                    //do nothing
                    if (callback != null) {
                        callback.onTick();
                    }
                }
            });
        }
    }
}