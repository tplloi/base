package com.views.recyclerview.parallax;

/**
 * Created by www.muathu@gmail.com on 11/4/2017.
 */

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by SouthernBox on 2017/2/4.
 * 带视差折叠效果的列表控件
 */
//TODO convert kotlin
public class LParallaxRecyclerView extends RecyclerView {

    public LParallaxRecyclerView(Context context) {
        this(context, null);
    }

    public LParallaxRecyclerView(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        if (isInEditMode()) {
            return;
        }

        setLayoutManager(new LinearLayoutManager(context));

        addItemDecoration(new ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
                super.getItemOffsets(outRect, view, parent, state);
                //获取当前项的下标
                final int currentPosition = parent.getChildLayoutPosition(view);
                //获取最后一项的下标
                final int lastPosition = state.getItemCount() - 1;
                if (currentPosition != lastPosition) {
                    outRect.bottom = -dp2px(context, 10);
                }
            }
        });

        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstPosition = layoutManager.findFirstVisibleItemPosition();
                int lastPosition = layoutManager.findLastVisibleItemPosition();
                int visibleCount = lastPosition - firstPosition;
                //重置控件的位置及高度
                int elevation = 1;
                for (int i = firstPosition - 1; i <= (firstPosition + visibleCount) + 1; i++) {
                    View view = layoutManager.findViewByPosition(i);
                    if (view != null) {
                        if (view instanceof CardView) {
                            ((CardView) view).setCardElevation(dp2px(context, elevation));
                            elevation += 5;
                        }
                        float translationY = view.getTranslationY();
                        if (i > firstPosition && translationY != 0) {
                            view.setTranslationY(0);
                        }
                    }
                }

                View firstView = layoutManager.findViewByPosition(firstPosition);
                float firstViewTop = firstView.getTop();
                firstView.setTranslationY(-firstViewTop / 2.0f);
            }
        });
    }

    private int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}