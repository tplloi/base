package vn.loitp.app.activity.demo.butterknife;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.core.base.BaseFontActivity;
import com.views.LToast;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import loitp.basemaster.R;

public class ButterKnifeActivity extends BaseFontActivity {
    @BindView(R.id.bt)
    Button bt;

    @BindView(R.id.tv123)
    TextView tv;

    @BindString(R.string.large_text)
    String largeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        tv.setText(largeText);
        bt.setText("Button");
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
        return R.layout.activity_butter_knife;
    }

    @OnClick(R.id.bt_click)
    void click() {
        LToast.showShort(getActivity(), "Click", R.drawable.bkg_horizontal);
    }
}
