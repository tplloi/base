package vn.loitp.app.activity.customviews.layout.scrollview2d;

import android.os.Bundle;

import com.core.base.BaseFontActivity;
import com.core.utilities.LLog;
import com.core.utilities.LUIUtil;
import com.views.scrollview.TwoDScrollView;

import loitp.basemaster.R;

public class ScrollView2DActivity extends BaseFontActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final TwoDScrollView twoDScrollView = findViewById(R.id.sv);
        twoDScrollView.setScrollChangeListner((view, x, y, oldx, oldy) -> {
            LLog.d(TAG, "setScrollChangeListner " + x + " - " + y);
        });
        LUIUtil.setDelay(2000, mls -> twoDScrollView.smoothScrollTo(300, 300));
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
        return R.layout.activity_scrollview_2d;
    }
}
