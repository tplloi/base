package com.core.loitp.gallery.albumonly;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.core.loitp.gallery.photos.PhotosDataCore;
import com.core.utilities.LAnimationUtil;
import com.core.utilities.LLog;
import com.core.utilities.LScreenUtil;
import com.core.utilities.LUIUtil;
import com.daimajia.androidanimations.library.Techniques;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.github.piasy.biv.loader.ImageLoader;
import com.github.piasy.biv.view.BigImageView;
import com.github.piasy.biv.view.GlideImageViewFactory;
import com.github.piasy.biv.view.ImageViewFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.restapi.flickr.model.photosetgetphotos.Photo;

import java.io.File;

import loitp.core.R;

/**
 * Created by loitp on 14/04/15.
 */
public class PhotosOnlyAdapter extends RecyclerView.Adapter<PhotosOnlyAdapter.ViewHolder> {
    private final String TAG = getClass().getSimpleName();
    private Context context;
    private LayoutInflater inflater;
    private int screenW;
    private ImageViewFactory viewFactory = new GlideImageViewFactory();


    public PhotosOnlyAdapter(Context context, Callback callback) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.callback = callback;
        screenW = LScreenUtil.getScreenWidth();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return new ViewHolder(inflater.inflate(R.layout.item_photos_core_only, viewGroup, false));
    }

    @Override
    public void onViewRecycled(@NonNull PhotosOnlyAdapter.ViewHolder holder) {
        super.onViewRecycled(holder);
        //LLog.d(TAG, "onViewRecycled");
        holder.clear();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (holder.hasNoImage()) {
            holder.rebind();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        final Photo photo = PhotosDataCore.getInstance().getPhotoList().get(position);
        viewHolder.bind(photo, position);
    }

    @Override
    public int getItemCount() {
        if (PhotosDataCore.getInstance().getPhotoList() == null) {
            return 0;
        }
        return PhotosDataCore.getInstance().getPhotoList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private BigImageView bigImageView;
        private FloatingActionButton btDownload;
        private FloatingActionButton btShare;
        private FloatingActionButton btReport;
        private FloatingActionButton btCmt;
        private ProgressBar pb;
        private Photo photo;

        public ViewHolder(View v) {
            super(v);
            tvTitle = v.findViewById(R.id.tv_title);
            bigImageView = v.findViewById(R.id.biv);
            btDownload = v.findViewById(R.id.bt_download);
            btShare = v.findViewById(R.id.bt_share);
            btReport = v.findViewById(R.id.bt_report);
            btCmt = v.findViewById(R.id.bt_cmt);
            pb = v.findViewById(R.id.pb);
            bigImageView.setTapToRetry(false);
            bigImageView.setImageViewFactory(viewFactory);
        }

        void bind(Photo p, final int position) {
            this.photo = p;
            int height = photo.getHeightO() * screenW / photo.getWidthO();
            //LLog.d(TAG, photo.getWidthO() + "x" + photo.getHeightO() + "->" + screenW + "x" + height);

            //viewHolder.rootView.setBackgroundColor(LStoreUtil.getRandomColorLight());

            bigImageView.getLayoutParams().width = screenW;
            bigImageView.getLayoutParams().height = height;
            bigImageView.requestLayout();

            bigImageView.showImage(Uri.parse(photo.getUrlS()), Uri.parse(photo.getUrlO()));
            bigImageView.setImageLoaderCallback(new ImageLoader.Callback() {
                @Override
                public void onCacheHit(int imageType, File image) {
                }

                @Override
                public void onCacheMiss(int imageType, File image) {
                }

                @Override
                public void onStart() {
                    if (pb != null) {
                        pb.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onProgress(int progress) {
                    pb.setProgress(progress);
                }

                @Override
                public void onFinish() {
                }

                @Override
                public void onSuccess(File image) {
                    if (bigImageView.getSSIV() != null) {
                        bigImageView.getSSIV().setZoomEnabled(false);
                    }
                    if (pb != null) {
                        pb.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onFail(Exception error) {
                }
            });
            if (photo.getTitle() == null || photo.getTitle().toLowerCase().startsWith("null")) {
                tvTitle.setVisibility(View.INVISIBLE);
            } else {
                tvTitle.setVisibility(View.VISIBLE);
                tvTitle.setText(photo.getTitle());
                LUIUtil.setTextShadow(tvTitle);
            }
            bigImageView.setOnClickListener(view -> {
                LLog.d(TAG, "setOnClickListener");
                LAnimationUtil.INSTANCE.play(bigImageView, Techniques.Pulse);
                if (callback != null) {
                    callback.onClick(photo, position);
                }
            });
            bigImageView.setOnLongClickListener(view -> {
                LLog.d(TAG, "onLongClick");
                LAnimationUtil.INSTANCE.play(bigImageView, Techniques.Pulse);
                if (callback != null) {
                    callback.onLongClick(photo, position);
                }
                return true;
            });
            btDownload.setOnClickListener(v -> {
                LAnimationUtil.INSTANCE.play(v, Techniques.Flash);
                if (callback != null) {
                    callback.onClickDownload(photo, position);
                }
            });
            btShare.setOnClickListener(v -> {
                LAnimationUtil.INSTANCE.play(v, Techniques.Flash);
                if (callback != null) {
                    callback.onClickShare(photo, position);
                }
            });
            btReport.setOnClickListener(v -> {
                LAnimationUtil.INSTANCE.play(v, Techniques.Flash);
                if (callback != null) {
                    callback.onClickReport(photo, position);
                }
            });
            btCmt.setOnClickListener(v -> {
                LAnimationUtil.INSTANCE.play(v, Techniques.Flash);
                if (callback != null) {
                    callback.onClickCmt(photo, position);
                }
            });
        }

        void rebind() {
            bigImageView.showImage(Uri.parse(photo.getUrlS()), Uri.parse(photo.getUrlO()));
        }

        void clear() {
            SubsamplingScaleImageView ssiv = bigImageView.getSSIV();
            if (ssiv != null) {
                ssiv.recycle();
            }
        }

        boolean hasNoImage() {
            SubsamplingScaleImageView ssiv = bigImageView.getSSIV();
            return ssiv == null || !ssiv.hasImage();
        }
    }

    public interface Callback {
        void onClick(Photo photo, int pos);

        void onLongClick(Photo photo, int pos);

        void onClickDownload(Photo photo, int pos);

        void onClickShare(Photo photo, int pos);

        void onClickReport(Photo photo, int pos);

        void onClickCmt(Photo photo, int pos);
    }

    private Callback callback;
}