package vn.loitp.app.activity.customviews.placeholderview.ex.androidexpandablenewsfeed;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.views.LToast;
import com.views.placeholderview.annotations.Click;
import com.views.placeholderview.annotations.Layout;
import com.views.placeholderview.annotations.Resolve;
import com.views.placeholderview.annotations.View;
import com.views.placeholderview.annotations.expand.ChildPosition;
import com.views.placeholderview.annotations.expand.ParentPosition;

import loitp.basemaster.R;

/**
 * Created by www.muathu@gmail.com on 9/16/2017.
 */

@Layout(R.layout.feed_item)
public class InfoView {

    @ParentPosition
    private int mParentPosition;

    @ChildPosition
    private int mChildPosition;

    @View(R.id.titleTxt)
    private TextView titleTxt;

    @View(R.id.captionTxt)
    private TextView captionTxt;

    @View(R.id.timeTxt)
    private TextView timeTxt;

    @View(R.id.imageView)
    private ImageView imageView;

    private Info mInfo;
    private Context mContext;

    public InfoView(Context context, Info info) {
        mContext = context;
        mInfo = info;
    }

    @Resolve
    private void onResolved() {
        titleTxt.setText(mInfo.getTitle());
        captionTxt.setText(mInfo.getCaption());
        timeTxt.setText(mInfo.getTime());
        Glide.with(mContext).load(mInfo.getImageUrl()).into(imageView);
    }

    @Click(R.id.titleTxt)
    private void onClickTitle() {
        LToast.showShort(mContext, titleTxt.getText().toString(), R.drawable.l_bkg_horizontal);
    }

    @Click(R.id.captionTxt)
    private void onClickCaption() {
        LToast.showShort(mContext, captionTxt.getText().toString(), R.drawable.l_bkg_horizontal);
    }

    @Click(R.id.imageView)
    private void onClickImage() {
        LToast.showShort(mContext, mInfo.getImageUrl(), R.drawable.l_bkg_horizontal);
    }
}