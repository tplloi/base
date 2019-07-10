package vn.loitp.app.activity.customviews.placeholderview.ex.androidadvanceimagegallery;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.views.placeholderview.lib.placeholderview.Animation;
import com.views.placeholderview.lib.placeholderview.PlaceHolderView;
import com.views.placeholderview.lib.placeholderview.annotations.Animate;
import com.views.placeholderview.lib.placeholderview.annotations.Click;
import com.views.placeholderview.lib.placeholderview.annotations.Layout;
import com.views.placeholderview.lib.placeholderview.annotations.LongClick;
import com.views.placeholderview.lib.placeholderview.annotations.NonReusable;
import com.views.placeholderview.lib.placeholderview.annotations.Resolve;
import com.views.placeholderview.lib.placeholderview.annotations.View;

import loitp.basemaster.R;

/**
 * Created by www.muathu@gmail.com on 9/16/2017.
 */

@Animate(Animation.ENTER_LEFT_DESC)
@NonReusable
@Layout(R.layout.gallery_item_big)
public class ImageTypeBig {
    private final static String TAG = ImageTypeBig.class.getSimpleName();
    @View(R.id.imageView)
    private ImageView imageView;
    @View(R.id.tv)
    private TextView tv;

    private String mUlr;
    private Context mContext;
    private PlaceHolderView mPlaceHolderView;

    public ImageTypeBig(Context context, PlaceHolderView placeHolderView, String ulr) {
        mContext = context;
        mPlaceHolderView = placeHolderView;
        mUlr = ulr;
        //LLog.d(TAG, "ImageTypeBig");
    }

    @Resolve
    private void onResolved() {
        Glide.with(mContext).load(mUlr).into(imageView);
        tv.setText(mUlr);
    }

    @LongClick(R.id.imageView)
    private void onLongClick() {
        mPlaceHolderView.removeView(this);
    }

    @Click(R.id.imageView)
    private void onClick() {
        Toast.makeText(mContext, "Touch " + mUlr, Toast.LENGTH_SHORT).show();
    }
}