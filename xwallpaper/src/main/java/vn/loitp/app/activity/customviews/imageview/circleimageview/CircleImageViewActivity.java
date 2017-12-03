package vn.loitp.app.activity.customviews.imageview.circleimageview;

import android.app.Activity;
import android.os.Bundle;

import vn.loitp.core.base.BaseActivity;

import com.loitp.xwallpaper.R;

public class CircleImageViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    protected Activity setActivity() {
        return this;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_circle_imageview;
    }
}
