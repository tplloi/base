package vn.loitp.views.uizavideo.view.rl;

/**
 * Created by www.muathu@gmail.com on 12/24/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Arrays;
import java.util.List;

import loitp.core.R;
import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.common.Constants;
import vn.loitp.core.utilities.LActivityUtil;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LScreenUtil;
import vn.loitp.core.utilities.LUIUtil;
import vn.loitp.restapi.uiza.model.v2.listallentity.Subtitle;
import vn.loitp.views.LToast;
import vn.loitp.views.seekbar.verticalseekbar.VerticalSeekBar;
import vn.loitp.views.uizavideo.UizaPlayerManager;
import vn.loitp.views.uizavideo.listerner.ProgressCallback;
import vn.loitp.views.uizavideo.view.floatview.FloatingUizaVideoService;
import vn.loitp.views.uizavideo.view.util.UizaUtil;

/**
 * Created by www.muathu@gmail.com on 7/26/2017.
 */

public class SimpleUizaIMAVideo extends RelativeLayout implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private final String TAG = getClass().getSimpleName();
    private Gson gson = new Gson();//TODO remove
    private PlayerView playerView;
    private UizaPlayerManager uizaPlayerManager;

    //play controller
    private RelativeLayout llMid;
    private ImageButton exoFullscreenIcon;
    private ProgressBar progressBar;
    private TextView tvTitle;
    private ImageButton exoBackScreen;
    private ImageButton exoVolume;
    private ImageButton exoSetting;
    private ImageButton exoCc;
    private ImageButton exoPlaylist;
    private ImageButton exoHearing;
    private ImageButton exoPictureInPicture;
    private VerticalSeekBar seekbarVolume;
    private ImageView ivVolumeSeekbar;
    private VerticalSeekBar seekbarBirghtness;
    private ImageView ivBirghtnessSeekbar;

    private LinearLayout debugRootView;
    private int firstBrightness;

    public SimpleUizaIMAVideo(Context context) {
        super(context);
        onCreate();
    }

    public SimpleUizaIMAVideo(Context context, AttributeSet attrs) {
        super(context, attrs);
        onCreate();
    }

    public SimpleUizaIMAVideo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onCreate();
    }

    public SimpleUizaIMAVideo(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        onCreate();
    }

    private void onCreate() {
        inflate(getContext(), R.layout.uiza_simple_ima_video_core_frm, this);
        findViews();
        UizaUtil.resizeLayout(playerView, llMid);
        initUI();
    }

    private void findViews() {
        llMid = (RelativeLayout) findViewById(R.id.ll_mid);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        LUIUtil.setColorProgressBar(progressBar, ContextCompat.getColor(progressBar.getContext(), R.color.White));
        playerView = findViewById(R.id.player_view);
        exoFullscreenIcon = (ImageButton) playerView.findViewById(R.id.exo_fullscreen_toggle_icon);
        tvTitle = (TextView) playerView.findViewById(R.id.tv_title);
        exoBackScreen = (ImageButton) playerView.findViewById(R.id.exo_back_screen);
        exoVolume = (ImageButton) playerView.findViewById(R.id.exo_volume);
        exoSetting = (ImageButton) playerView.findViewById(R.id.exo_setting);
        exoCc = (ImageButton) playerView.findViewById(R.id.exo_cc);
        exoPlaylist = (ImageButton) playerView.findViewById(R.id.exo_playlist);
        exoHearing = (ImageButton) playerView.findViewById(R.id.exo_hearing);
        exoPictureInPicture = (ImageButton) playerView.findViewById(R.id.exo_picture_in_picture);

        seekbarVolume = (VerticalSeekBar) playerView.findViewById(R.id.seekbar_volume);
        seekbarBirghtness = (VerticalSeekBar) playerView.findViewById(R.id.seekbar_birghtness);
        LUIUtil.setColorSeekBar(seekbarVolume, ContextCompat.getColor(getContext(), R.color.White));
        LUIUtil.setColorSeekBar(seekbarBirghtness, ContextCompat.getColor(getContext(), R.color.White));
        ivVolumeSeekbar = (ImageView) playerView.findViewById(R.id.exo_volume_seekbar);
        ivBirghtnessSeekbar = (ImageView) playerView.findViewById(R.id.exo_birghtness_seekbar);

        debugRootView = findViewById(R.id.controls_root);
        if (Constants.IS_DEBUG) {
            debugRootView.setVisibility(View.GONE);
        } else {
            debugRootView.setVisibility(View.GONE);
        }

        //onclick
        if (exoFullscreenIcon != null) {
            exoFullscreenIcon.setOnClickListener(this);
        }
        if (exoFullscreenIcon != null) {
            exoFullscreenIcon.setOnClickListener(this);
        }
        if (exoVolume != null) {
            exoVolume.setOnClickListener(this);
        }
        if (exoSetting != null) {
            exoSetting.setOnClickListener(this);
        }
        if (exoCc != null) {
            exoCc.setOnClickListener(this);
        }
        if (exoPlaylist != null) {
            exoPlaylist.setOnClickListener(this);
        }
        if (exoHearing != null) {
            exoHearing.setOnClickListener(this);
        }
        if (exoPictureInPicture != null) {
            exoPictureInPicture.setOnClickListener(this);
        }

        //seekbar change
        if (seekbarVolume != null) {
            seekbarVolume.setOnSeekBarChangeListener(this);
        }
        if (seekbarBirghtness != null) {
            seekbarBirghtness.setOnSeekBarChangeListener(this);
        }
    }

    private List<Subtitle> createDummySubtitle() {
        String json = "[\n" +
                "                {\n" +
                "                    \"id\": \"18414566-c0c8-4a51-9d60-03f825bb64a9\",\n" +
                "                    \"name\": \"\",\n" +
                "                    \"type\": \"subtitle\",\n" +
                "                    \"url\": \"//dev-static.uiza.io/subtitle_56a4f990-17e6-473c-8434-ef6c7e40bba1_en_1522812430080.vtt\",\n" +
                "                    \"mine\": \"vtt\",\n" +
                "                    \"language\": \"en\",\n" +
                "                    \"isDefault\": \"0\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"271787a0-5d23-4a35-a10a-5c43fdcb71a8\",\n" +
                "                    \"name\": \"\",\n" +
                "                    \"type\": \"subtitle\",\n" +
                "                    \"url\": \"//dev-static.uiza.io/subtitle_56a4f990-17e6-473c-8434-ef6c7e40bba1_vi_1522812445904.vtt\",\n" +
                "                    \"mine\": \"vtt\",\n" +
                "                    \"language\": \"vi\",\n" +
                "                    \"isDefault\": \"0\"\n" +
                "                }\n" +
                "            ]";
        Subtitle[] subtitles = gson.fromJson(json, new TypeToken<Subtitle[]>() {
        }.getType());
        LLog.d(TAG, "createDummySubtitle subtitles " + gson.toJson(subtitles));
        List subtitleList = Arrays.asList(subtitles);
        LLog.d(TAG, "createDummySubtitle subtitleList " + gson.toJson(subtitleList));
        return subtitleList;
    }

    public void initData(String linkPlay, String urlIMAAd, String urlThumnailsPreviewSeekbar) {
        List<Subtitle> subtitleList = createDummySubtitle();

        uizaPlayerManager = new UizaPlayerManager(playerView, progressBar, null, null, linkPlay, urlIMAAd, urlThumnailsPreviewSeekbar, subtitleList);
        uizaPlayerManager.setProgressCallback(new ProgressCallback() {
            @Override
            public void onAdProgress(float currentMls, float duration, int percent) {
                LLog.d(TAG, "ad progress: " + currentMls + "/" + duration + " -> " + percent + "%");
            }

            @Override
            public void onVideoProgress(float currentMls, float duration, int percent) {
                LLog.d(TAG, "video progress: " + currentMls + "/" + duration + " -> " + percent + "%");
            }
        });
        uizaPlayerManager.setDebugCallback(new UizaPlayerManager.DebugCallback() {
            @Override
            public void onUpdateButtonVisibilities() {
                LLog.d(TAG, "onUpdateButtonVisibilities");
                updateButtonVisibilities();
            }
        });

        //set volume max in first play
        if (seekbarVolume != null) {
            seekbarVolume.setMax(100);
            setProgressSeekbar(seekbarVolume, 100);
            uizaPlayerManager.setVolume(100f);
        }

        //set bightness max in first play
        firstBrightness = LScreenUtil.getCurrentBrightness(getContext()) * 100 / 255 + 1;
        if (seekbarBirghtness != null) {
            LLog.d(TAG, "firstBrightness " + firstBrightness);
            seekbarBirghtness.setMax(100);
            setProgressSeekbar(seekbarBirghtness, firstBrightness);
        }
    }

    private void setProgressSeekbar(SeekBar seekbar, int progressSeekbar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            seekbar.setProgress(progressSeekbar, true);
        } else {
            seekbar.setProgress(progressSeekbar);
        }
    }

    public void onDestroy() {
        LScreenUtil.setBrightness(getContext(), firstBrightness);
        if (uizaPlayerManager != null) {
            uizaPlayerManager.release();
        }
    }

    public void onResume() {
        if (uizaPlayerManager != null) {
            uizaPlayerManager.init();
        }
    }

    public void onPause() {
        if (uizaPlayerManager != null) {
            uizaPlayerManager.reset();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == exoFullscreenIcon) {
            UizaUtil.setUIFullScreenIcon(getContext(), exoFullscreenIcon);
            LActivityUtil.toggleScreenOritation((BaseActivity) getContext());
        } else if (v == exoBackScreen) {
            if (isLandscape) {
                exoFullscreenIcon.performClick();
            } else {
                if ((BaseActivity) getContext() != null) {
                    ((BaseActivity) getContext()).onBackPressed();
                }
            }
        } else if (v == exoVolume) {
            uizaPlayerManager.toggleVolumeMute(exoVolume);
        } else if (v == exoSetting) {
            View view = UizaUtil.getBtVideo(debugRootView);
            if (view != null) {
                UizaUtil.getBtVideo(debugRootView).performClick();
            }
        } else if (v == exoCc) {
            View view = UizaUtil.getBtText(debugRootView);
            if (view != null) {
                UizaUtil.getBtText(debugRootView).performClick();
            }
        } else if (v == exoPlaylist) {
            //TODO
            LToast.show(getContext(), "Click exoPlaylist");
        } else if (v == exoHearing) {
            View view = UizaUtil.getBtAudio(debugRootView);
            if (view != null) {
                UizaUtil.getBtAudio(debugRootView).performClick();
            }
        } else if (v == exoPictureInPicture) {
            clickPiP();
        } else if (v.getParent() == debugRootView) {
            MappingTrackSelector.MappedTrackInfo mappedTrackInfo = uizaPlayerManager.getTrackSelector().getCurrentMappedTrackInfo();
            if (mappedTrackInfo != null) {
                uizaPlayerManager.getTrackSelectionHelper().showSelectionDialog((BaseActivity) getContext(), ((Button) v).getText(), mappedTrackInfo, (int) v.getTag());
            }
        }
    }

    private boolean isLandscape;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if ((BaseActivity) getContext() != null) {
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                LScreenUtil.hideDefaultControls((BaseActivity) getContext());
                isLandscape = true;
            } else {
                LScreenUtil.showDefaultControls((BaseActivity) getContext());
                isLandscape = false;
            }
        }
        UizaUtil.resizeLayout(playerView, llMid);
    }

    public void initUI() {
        String title = "Dummy Video";
        updateUIPlayController(title);
    }

    private void updateUIPlayController(String title) {
        if (tvTitle != null) {
            tvTitle.setText(title);
            LUIUtil.setTextShadow(tvTitle);
        }
    }

    public void updateButtonVisibilities() {
        debugRootView.removeAllViews();
        if (uizaPlayerManager.getPlayer() == null) {
            return;
        }
        MappingTrackSelector.MappedTrackInfo mappedTrackInfo = uizaPlayerManager.getTrackSelector().getCurrentMappedTrackInfo();
        if (mappedTrackInfo == null) {
            return;
        }
        for (int i = 0; i < mappedTrackInfo.length; i++) {
            TrackGroupArray trackGroups = mappedTrackInfo.getTrackGroups(i);
            if (trackGroups.length != 0) {
                Button button = new Button(getContext());
                int label;
                switch (uizaPlayerManager.getPlayer().getRendererType(i)) {
                    case C.TRACK_TYPE_AUDIO:
                        label = R.string.audio;
                        break;
                    case C.TRACK_TYPE_VIDEO:
                        label = R.string.video;
                        break;
                    case C.TRACK_TYPE_TEXT:
                        label = R.string.text;
                        break;
                    default:
                        continue;
                }
                button.setText(label);
                button.setTag(i);
                button.setOnClickListener(this);
                debugRootView.addView(button);
            }
        }
    }

    //on seekbar change
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar == seekbarVolume) {
            //LLog.d(TAG, "seekbarVolume onProgressChanged " + progress);
            if (progress >= 66) {
                ivVolumeSeekbar.setImageResource(R.drawable.ic_volume_up_black_48dp);
            } else if (progress >= 33) {
                ivVolumeSeekbar.setImageResource(R.drawable.ic_volume_down_black_48dp);
            } else {
                ivVolumeSeekbar.setImageResource(R.drawable.ic_volume_mute_black_48dp);
            }
            LLog.d(TAG, "seekbarVolume onProgressChanged " + progress + " -> " + ((float) progress / 100));
            uizaPlayerManager.setVolume(((float) progress / 100));
        } else if (seekBar == seekbarBirghtness) {
            LLog.d(TAG, "seekbarBirghtness onProgressChanged " + progress);
            if (progress >= 85) {
                ivBirghtnessSeekbar.setImageResource(R.drawable.ic_brightness_7_black_48dp);
            } else if (progress >= 71) {
                ivBirghtnessSeekbar.setImageResource(R.drawable.ic_brightness_6_black_48dp);
            } else if (progress >= 57) {
                ivBirghtnessSeekbar.setImageResource(R.drawable.ic_brightness_5_black_48dp);
            } else if (progress >= 42) {
                ivBirghtnessSeekbar.setImageResource(R.drawable.ic_brightness_4_black_48dp);
            } else if (progress >= 28) {
                ivBirghtnessSeekbar.setImageResource(R.drawable.ic_brightness_3_black_48dp);
            } else if (progress >= 14) {
                ivBirghtnessSeekbar.setImageResource(R.drawable.ic_brightness_2_black_48dp);
            } else {
                ivBirghtnessSeekbar.setImageResource(R.drawable.ic_brightness_1_black_48dp);
            }
            LScreenUtil.setBrightness(getContext(), progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        LLog.d(TAG, "onStartTrackingTouch");
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        LLog.d(TAG, "onStopTrackingTouch");
    }
    //end on seekbar change

    public static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 6969;

    private void clickPiP() {
        LLog.d(TAG, "clickPiP");
        if (getContext() == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(getContext())) {
            //If the draw over permission is not available open the settings screen
            //to grant the permission.
            LLog.d(TAG, "clickPiP if");
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getContext().getPackageName()));
            ((BaseActivity) getContext()).startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION);
        } else {
            LLog.d(TAG, "clickPiP else");
            initializePiP();
        }
    }

    public void initializePiP() {
        if (getContext() == null) {
            return;
        }
        LToast.show(getContext(), "initializePiP");
        getContext().startService(new Intent(getContext(), FloatingUizaVideoService.class));
        ((BaseActivity) getContext()).onBackPressed();
    }
}