package vn.loitp.app.activity.customviews.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import loitp.basemaster.R;
import vn.loitp.app.activity.customviews.recyclerview.gallerylayoutmanager.GalleryLayoutManagerActivity;
import vn.loitp.app.activity.customviews.recyclerview.normalrecyclerview.RecyclerViewActivity;
import vn.loitp.app.activity.customviews.recyclerview.normalrecyclerviewwithsingletondata.RecyclerViewWithSingletonDataActivity;
import vn.loitp.app.activity.customviews.recyclerview.normalwithspansize.RecyclerViewWithSpanSizeActivity;
import vn.loitp.app.activity.customviews.recyclerview.overflyingrecyclerview.OverFlyingRecyclerViewActivity;
import vn.loitp.app.activity.customviews.recyclerview.parallaxrecyclerview.ParallaxRecyclerViewActivity;
import vn.loitp.app.activity.customviews.recyclerview.parallaxrecyclerviewyayandroid.ParallaxYayandroidRecyclerViewActivity;
import vn.loitp.app.activity.customviews.recyclerview.recyclerbanner.RecyclerBannerActivity;
import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.utilities.LActivityUtil;

public class RecyclerViewMenuActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.bt_parallax_recyclerview).setOnClickListener(this);
        findViewById(R.id.bt_normal_recyclerview).setOnClickListener(this);
        findViewById(R.id.bt_normal_recyclerview_with_spansize).setOnClickListener(this);
        findViewById(R.id.bt_parallax_recyclerview_yayandroid).setOnClickListener(this);
        findViewById(R.id.bt_normal_recyclerview_with_singleton_data).setOnClickListener(this);
        findViewById(R.id.bt_recyclerview_banner).setOnClickListener(this);
        findViewById(R.id.bt_overflying_recyclerview).setOnClickListener(this);
        findViewById(R.id.bt_gallery_layout_manager).setOnClickListener(this);
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
        return R.layout.activity_menu_recyclerview;
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.bt_parallax_recyclerview:
                intent = new Intent(activity, ParallaxRecyclerViewActivity.class);
                break;
            case R.id.bt_normal_recyclerview:
                intent = new Intent(activity, RecyclerViewActivity.class);
                break;
            case R.id.bt_normal_recyclerview_with_spansize:
                intent = new Intent(activity, RecyclerViewWithSpanSizeActivity.class);
                break;
            case R.id.bt_parallax_recyclerview_yayandroid:
                intent = new Intent(activity, ParallaxYayandroidRecyclerViewActivity.class);
                break;
            case R.id.bt_normal_recyclerview_with_singleton_data:
                intent = new Intent(activity, RecyclerViewWithSingletonDataActivity.class);
                break;
            case R.id.bt_recyclerview_banner:
                intent = new Intent(activity, RecyclerBannerActivity.class);
                break;
            case R.id.bt_overflying_recyclerview:
                intent = new Intent(activity, OverFlyingRecyclerViewActivity.class);
                break;
            case R.id.bt_gallery_layout_manager:
                intent = new Intent(activity, GalleryLayoutManagerActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
            LActivityUtil.tranIn(activity);
        }
    }
}
