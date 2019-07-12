package com.core.loitp.more;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

import com.core.base.BaseFragment;
import com.core.loitp.adhelper.AdHelperActivity;
import com.core.utilities.LActivityUtil;
import com.core.utilities.LSocialUtil;
import com.core.utilities.LUIUtil;

import loitp.core.R;

/**
 * Created by www.muathu@gmail.com on 7/26/2017.
 */

public class FrmMore extends BaseFragment implements View.OnClickListener {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.bt_rate_app).setOnClickListener(this);
        view.findViewById(R.id.bt_more_app).setOnClickListener(this);
        view.findViewById(R.id.bt_share_app).setOnClickListener(this);
        view.findViewById(R.id.bt_like_fb_fanpage).setOnClickListener(this);
        view.findViewById(R.id.bt_support).setOnClickListener(this);
        view.findViewById(R.id.bt_ad_helper).setOnClickListener(this);

        NestedScrollView nestedScrollView = (NestedScrollView) view.findViewById(R.id.sv);
        LUIUtil.setPullLikeIOSVertical(nestedScrollView);
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.frm_more;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bt_rate_app) {
            LSocialUtil.INSTANCE.rateApp(getActivity(), getActivity().getPackageName());
        } else if (id == R.id.bt_more_app) {
            LSocialUtil.INSTANCE.moreApp(getActivity());
        } else if (id == R.id.bt_share_app) {
            LSocialUtil.INSTANCE.shareApp(getActivity());
        } else if (id == R.id.bt_like_fb_fanpage) {
            LSocialUtil.INSTANCE.likeFacebookFanpage(getActivity());
        } else if (id == R.id.bt_support) {
            LSocialUtil.INSTANCE.chatMessenger(getActivity());
        } else if (id == R.id.bt_ad_helper) {
            Intent intent = new Intent(getActivity(), AdHelperActivity.class);
            startActivity(intent);
            LActivityUtil.tranIn(getActivity());
        }
    }
}