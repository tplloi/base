package vn.loitp.up.a.picker.unicornFile

import abhishekti7.unicorn.filepicker.UnicornFilePicker
import abhishekti7.unicorn.filepicker.utils.Constants
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import androidx.core.app.ActivityCompat
import com.loitp.annotation.IsFullScreen
import com.loitp.annotation.LogTag
import com.loitp.core.base.BaseActivityFont
import com.loitp.core.ext.setSafeOnClickListener
import com.loitp.core.ext.setSafeOnClickListenerElastic
import vn.loitp.R
import vn.loitp.databinding.AUnicornFilePickerBinding

@LogTag("UnicornFilePickerActivity")
@IsFullScreen(false)
class UnicornFilePickerActivity : BaseActivityFont() {

    companion object {
        private const val REQUEST_CODE = 1
        private val PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    private lateinit var binding: AUnicornFilePickerBinding

//    override fun setLayoutResourceId(): Int {
//        return NOT_FOUND
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = AUnicornFilePickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    override fun onResume() {
        super.onResume()
        if (!hasPermissions(*PERMISSIONS)) {
            ActivityCompat.requestPermissions(
                this,
                PERMISSIONS,
                REQUEST_CODE
            )
        }
    }

    private fun setupViews() {
        binding.lActionBar.apply {
            this.ivIconLeft.setSafeOnClickListenerElastic(
                runnable = {
                    onBaseBackPressed()
                }
            )
            this.ivIconRight?.setImageResource(R.color.transparent)
            this.tvTitle?.text = UnicornFilePickerActivity::class.java.simpleName
        }

        binding.btPhoto.setSafeOnClickListener {
            if (hasPermissions(*PERMISSIONS)) {
                UnicornFilePicker.from(this)
                    .addConfigBuilder()
                    .selectMultipleFiles(true)
                    .showOnlyDirectory(false)
                    .setRootDirectory(Environment.getExternalStorageDirectory().absolutePath)
                    .showHiddenFiles(false)
                    .setFilters(arrayOf("png", "jpg", "jpeg"))
                    .addItemDivider(true)
                    .theme(abhishekti7.unicorn.filepicker.R.style.UnicornFilePicker_Dracula)
                    .build()
                    .forResult(Constants.REQ_UNICORN_FILE)
            } else {
                showPermissionsErrorAndRequest()
            }
        }
        binding.btVideo.setSafeOnClickListener {
            if (hasPermissions(*PERMISSIONS)) {
                UnicornFilePicker.from(this)
                    .addConfigBuilder()
                    .selectMultipleFiles(true)
                    .showOnlyDirectory(false)
                    .setRootDirectory(Environment.getExternalStorageDirectory().absolutePath)
                    .showHiddenFiles(false)
                    .setFilters(arrayOf("mp4", "mp3"))
                    .addItemDivider(true)
                    .theme(abhishekti7.unicorn.filepicker.R.style.UnicornFilePicker_Default)
                    .build()
                    .forResult(Constants.REQ_UNICORN_FILE)
            } else {
                showPermissionsErrorAndRequest()
            }
        }
    }

    private fun showPermissionsErrorAndRequest() {
        showSnackBarInfor("You need permissions before")
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivity(intent)
    }

    private fun hasPermissions(vararg permissions: String): Boolean {
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Constants.REQ_UNICORN_FILE && resultCode == RESULT_OK) {
            val files = data?.getStringArrayListExtra("filePaths")
            var s = ""
            files?.forEach {
                s = s + "\n" + it
            }
            binding.tv.text = s
        }
    }
}
