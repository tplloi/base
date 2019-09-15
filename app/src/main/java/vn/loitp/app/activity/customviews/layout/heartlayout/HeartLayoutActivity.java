package vn.loitp.app.activity.customviews.layout.heartlayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.core.base.BaseFontActivity;
import com.views.layout.heartlayout.LHeartLayout;

import java.util.Random;

import loitp.basemaster.R;

public class HeartLayoutActivity extends BaseFontActivity {
    private Random mRandom = new Random();
    private LHeartLayout mLHeartLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLHeartLayout = (LHeartLayout) findViewById(R.id.heart_layout);
        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLHeartLayout.addHeart(randomColor());
            }
        });
    }

    @Override
    protected boolean setFullScreen() {
        return false;
    }

    @Override
    protected String setTag() {
        return getClass().getSimpleName();
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_heart_layout;
    }

    private int randomColor() {
        return Color.rgb(mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255));
    }
}
