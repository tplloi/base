package vn.loitp.app.activity.demo.firebase.databasesimple;

import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.annotation.IsFullScreen;
import com.annotation.LayoutId;
import com.annotation.LogTag;
import com.core.base.BaseFontActivity;
import com.core.utilities.LUIUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.views.recyclerview.animator.adapters.ScaleInAnimationAdapter;
import com.views.recyclerview.animator.animators.SlideInRightAnimator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import vn.loitp.app.R;
import vn.loitp.app.common.Constants;

@LayoutId(R.layout.activity_menu_firebase_simple)
@LogTag("DatabaseSimpleFirebaseActivity")
@IsFullScreen(false)
public class DatabaseSimpleFirebaseActivity extends BaseFontActivity implements View.OnClickListener {
    private final String ROOT_NODE = "loitp";
    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;
    private List<User> userList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UserAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference();

        findViewById(R.id.btAdd).setOnClickListener(this);

        setupUI();

        mFirebaseDatabase.child(ROOT_NODE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot == null || dataSnapshot.getValue() == null) {
                    logD("onDataChange null => return");
                    userList.clear();
                    if (mAdapter != null) {
                        mAdapter.notifyDataSetChanged();
                    }
                    return;
                }
                userList.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    User user = data.getValue(User.class);
                    /*if (!userList.contains(user)) {
                        userList.add(user);
                    }*/
                    userList.add(user);
                }
                logD("userList.size: " + userList.size());
                if (mAdapter != null) {
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {
                logE("Failed to read app title value " + error.toException());
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btAdd) {
            addData();
        }
    }

    private void addData() {
        User user = new User();
        user.setTimestamp(System.currentTimeMillis());
        user.setAvt(Constants.Companion.getURL_IMG());
        user.setName("loitp");
        user.setMsg("dummy msg " + user.getTimestamp());
        mFirebaseDatabase.child(ROOT_NODE).child(user.getTimestamp() + "").setValue(user)
                .addOnCompleteListener(task -> {
                    //do nothing
                });
    }

    private void setupUI() {
        recyclerView = findViewById(R.id.rv);

        SlideInRightAnimator animator = new SlideInRightAnimator(new OvershootInterpolator(1f));
        animator.setAddDuration(300);
        recyclerView.setItemAnimator(animator);
        //recyclerView.getItemAnimator().setAddDuration(1000);

        mAdapter = new UserAdapter(this, userList, new UserAdapter.Callback() {
            @Override
            public void onClick(User user, int position) {
                showShort("onClick To Edit Data: " + user.getMsg(), true);

                user.setMsg("Edited Msg " + System.currentTimeMillis());
                user.setName("Edited Name");
                user.setAvt(Constants.Companion.getURL_IMG_1());
                mFirebaseDatabase.child(ROOT_NODE).child(user.getTimestamp() + "").setValue(user);
            }

            @Override
            public void onLongClick(User user, int position) {
                showShort("onLongClick " + user.getMsg(), true);
                mFirebaseDatabase.child(ROOT_NODE).child(user.getTimestamp() + "").removeValue();
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        //recyclerView.setAdapter(mAdapter);

        //AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mAdapter);
        //alphaAdapter.setDuration(1000);
        //alphaAdapter.setInterpolator(new OvershootInterpolator());
        //alphaAdapter.setFirstOnly(true);
        //recyclerView.setAdapter(alphaAdapter);

        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(mAdapter);
        scaleAdapter.setDuration(1000);
        scaleAdapter.setInterpolator(new OvershootInterpolator());
        scaleAdapter.setFirstOnly(true);
        recyclerView.setAdapter(scaleAdapter);

        LUIUtil.Companion.setPullLikeIOSVertical(recyclerView);
    }
}
