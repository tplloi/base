package vn.loitp.app.activity.customviews.button.roundedbutton;

import android.os.Bundle;
import android.view.View;

import com.core.base.BaseFontActivity;
import com.views.LToast;

import loitp.basemaster.R;

public class RoundedButtonActivity extends BaseFontActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.bt_0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LToast.INSTANCE.showShort(getActivity(), "Click");
            }
        });
        findViewById(R.id.bt_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LToast.INSTANCE.showShort(getActivity(), "Click");
            }
        });
        findViewById(R.id.bt_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LToast.INSTANCE.showShort(getActivity(), "Click");
            }
        });
        findViewById(R.id.bt_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LToast.INSTANCE.showShort(getActivity(), "Click");
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
        return R.layout.activity_rounded_button;
    }
}
