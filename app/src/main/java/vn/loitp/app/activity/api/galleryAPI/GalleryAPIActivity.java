package vn.loitp.app.activity.api.galleryAPI;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.core.base.BaseFontActivity;
import com.core.utilities.LLog;
import com.core.utilities.LUIUtil;
import com.restapi.flickr.FlickrConst;
import com.restapi.flickr.model.photosetgetlist.Photoset;
import com.restapi.flickr.model.photosetgetlist.WrapperPhotosetGetlist;
import com.restapi.flickr.service.FlickrService;
import com.restapi.restclient.RestClient;
import com.views.progressloadingview.avl.LAVLoadingIndicatorView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import vn.loitp.app.R;
import vn.loitp.app.app.LApplication;

public class GalleryAPIActivity extends BaseFontActivity {
    private LAVLoadingIndicatorView avi;
    private TextView tv;
    private Button bt2;
    private WrapperPhotosetGetlist mWrapperPhotosetGetlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        avi = findViewById(R.id.avi);
        avi.smoothToHide();
        tv = findViewById(R.id.tv);
        final Button bt1 = findViewById(R.id.bt_1);
        bt2 = findViewById(R.id.bt_2);
        bt1.setOnClickListener(v -> photosetsGetList());
        bt2.setOnClickListener(v -> showDialogSelectPhotoset());
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
        return R.layout.activity_gallery_api;
    }

    private void photosetsGetList() {
        avi.smoothToShow();
        final FlickrService service = RestClient.createService(FlickrService.class);
        final String method = FlickrConst.METHOD_PHOTOSETS_GETLIST;
        final String apiKey = FlickrConst.API_KEY;
        final String userID = FlickrConst.USER_KEY;
        final int page = 1;
        final int perPage = 500;
        final String primaryPhotoExtras = FlickrConst.PRIMARY_PHOTO_EXTRAS_0;
        final String format = FlickrConst.FORMAT;
        final int nojsoncallback = FlickrConst.NO_JSON_CALLBACK;

        getCompositeDisposable().add(service.photosetsGetList(method, apiKey, userID, page, perPage, primaryPhotoExtras, format, nojsoncallback)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wrapperPhotosetGetlist -> {
                    LLog.d(getTAG(), "onSuccess " + LApplication.Companion.getGson().toJson(wrapperPhotosetGetlist));
                    mWrapperPhotosetGetlist = wrapperPhotosetGetlist;
                    LUIUtil.INSTANCE.printBeautyJson(wrapperPhotosetGetlist, tv);
                    avi.smoothToHide();
                    bt2.setVisibility(View.VISIBLE);
                }, e -> {
                    handleException(e);
                    avi.smoothToHide();
                }));
    }

    private void showDialogSelectPhotoset() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose:");

        final List<Photoset> photosetList = mWrapperPhotosetGetlist.getPhotosets().getPhotoset();
        final String[] items = new String[photosetList.size()];
        for (int i = 0; i < photosetList.size(); i++) {
            //LLog.d(TAG, i + " : " + photosetList.get(i).getTitle().getContent());
            items[i] = photosetList.get(i).getTitle().getContent();
        }

        builder.setItems(items, (dialog, position) -> {
            LLog.d(getTAG(), "onClick " + position);
            photosetsGetPhotos(photosetList.get(position).getId());
        });
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void photosetsGetPhotos(@NonNull final String photosetID) {
        tv.setText("");
        avi.smoothToShow();
        final FlickrService service = RestClient.createService(FlickrService.class);
        final String method = FlickrConst.METHOD_PHOTOSETS_GETPHOTOS;
        final String apiKey = FlickrConst.API_KEY;
        final String userID = FlickrConst.USER_KEY;
        final int page = 1;
        final int perPage = 500;
        final String primaryPhotoExtras = FlickrConst.PRIMARY_PHOTO_EXTRAS_1;
        final String format = FlickrConst.FORMAT;
        final int nojsoncallback = FlickrConst.NO_JSON_CALLBACK;

        getCompositeDisposable().add(service.photosetsGetPhotos(method, apiKey, photosetID, userID, primaryPhotoExtras, perPage, page, format, nojsoncallback)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wrapperPhotosetGetlist -> {
                    LLog.d(getTAG(), "onSuccess " + LApplication.Companion.getGson().toJson(wrapperPhotosetGetlist));
                    LUIUtil.INSTANCE.printBeautyJson(wrapperPhotosetGetlist, tv);
                    avi.smoothToHide();
                    bt2.setVisibility(View.VISIBLE);
                }, e -> {
                    handleException(e);
                    avi.smoothToHide();
                }));
    }
}
