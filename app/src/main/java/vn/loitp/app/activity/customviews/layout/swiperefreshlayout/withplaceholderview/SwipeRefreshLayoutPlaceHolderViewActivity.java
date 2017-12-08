package vn.loitp.app.activity.customviews.layout.swiperefreshlayout.withplaceholderview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import loitp.basemaster.R;
import vn.loitp.app.activity.customviews.placeholderview.ex.androidadvanceimagegallery.Image;
import vn.loitp.app.activity.customviews.placeholderview.ex.androidadvanceimagegallery.ImageTypeBig;
import vn.loitp.app.activity.customviews.placeholderview.ex.androidadvanceimagegallery.ImageTypeSmallList;
import vn.loitp.app.activity.customviews.placeholderview.ex.androidadvanceimagegallery.Utils;
import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LUIUtil;
import vn.loitp.views.placeholderview.lib.placeholderview.PlaceHolderView;

public class SwipeRefreshLayoutPlaceHolderViewActivity extends BaseActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    private PlaceHolderView mGalleryView;
    private PlaceHolderView.OnScrollListener mOnScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageList = Utils.loadImages(activity);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        LUIUtil.setColorForSwipeRefreshLayout(swipeRefreshLayout);

        mGalleryView = (PlaceHolderView) findViewById(R.id.galleryView);
        setLoadMoreListener();
        //LUIUtil.setPullLikeIOSVertical(mGalleryView);

        setupGallery();
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
        return R.layout.activity_swipe_refresh_placeholder_view_layout;
    }

    private List<Image> imageList = new ArrayList<>();

    private void setupGallery() {
        mGalleryView.addView(new ImageTypeSmallList(activity, imageList));
        for (int i = 0; i < imageList.size(); i++) {
            mGalleryView.addView(new ImageTypeBig(activity, mGalleryView, imageList.get(i).getImageUrl()));
        }
        mGalleryView.addView(new ImageTypeSmallList(activity, imageList));
    }

    private void refresh() {
        mGalleryView.removeAllViews();
        mGalleryView.refresh();
        LUIUtil.setDelay(2000, new LUIUtil.DelayCallback() {
            @Override
            public void doAfter(int mls) {
                setupGallery();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void loadMore() {
        LLog.d(TAG, ">>>>loadMore");
        LUIUtil.setDelay(2000, new LUIUtil.DelayCallback() {
            @Override
            public void doAfter(int mls) {
                mGalleryView.addView(new ImageTypeSmallList(activity, imageList));
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void setLoadMoreListener() {
        mOnScrollListener =
                new PlaceHolderView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        PlaceHolderView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                        if (layoutManager instanceof LinearLayoutManager) {
                            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                            int totalItemCount = linearLayoutManager.getItemCount();
                            int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                            if (totalItemCount > 0 && totalItemCount == lastVisibleItem + 1) {
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        LLog.d(TAG, "setLoadMoreListener");
                                        if (swipeRefreshLayout.isRefreshing()) {
                                            return;
                                        }
                                        swipeRefreshLayout.setRefreshing(true);
                                        loadMore();
                                    }
                                });
                            }
                        }
                    }
                };
        mGalleryView.addOnScrollListener(mOnScrollListener);
    }
}
