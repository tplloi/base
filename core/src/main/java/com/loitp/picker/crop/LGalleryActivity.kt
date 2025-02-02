package com.loitp.picker.crop

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.loitp.R
import com.loitp.annotation.IsFullScreen
import com.loitp.annotation.LogTag
import com.loitp.core.base.BaseActivityFont
import com.loitp.core.ext.loadGlide
import com.loitp.core.ext.tranOut
import com.loitp.core.utils.ConvertUtils
import com.loitp.databinding.LALGalleryBinding
import java.io.File

/**
 * Created by Loitp on 04,August,2022
 * Galaxy One company,
 * Vietnam
 * +840766040293
 * freuss47@gmail.com
 */
@LogTag("LGalleryActivity")
@IsFullScreen(false)
class LGalleryActivity : BaseActivityFont(), View.OnClickListener {
    private lateinit var binding: LALGalleryBinding

    companion object {
        const val RETURN_VALUE = "RETURN_VALUE"
    }

    private val listThumbsData = ArrayList<String>()

//    override fun setLayoutResourceId(): Int {
//        return NOT_FOUND
//    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LALGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storeImage
        val galleryAdapter = GalleryAdapter()
        galleryAdapter.addAllDataList(listThumbsData)
        val gridLayoutManager = GridLayoutManager(this, 3)
        binding.galleryList.layoutManager = gridLayoutManager
        binding.galleryList.adapter = galleryAdapter
        binding.btClose.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view == binding.btClose) {
            onBaseBackPressed()
        }
    }

    private var imageCursor: Cursor? = null

    private val storeImage: Unit
        get() {
            val proj = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.SIZE
            )
            imageCursor =
                managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, proj, null, null, null)
            if (imageCursor != null && imageCursor?.moveToLast() == true) {
                var title: String
                var thumbsID: String? = null
                var thumbsImageID: String? = null
                var thumbsData: String? = null
                var imgSize: String? = null
                val thumbsIDCol = imageCursor?.getColumnIndex(MediaStore.Images.Media._ID)
                val thumbsDataCol = imageCursor?.getColumnIndex(MediaStore.Images.Media.DATA)
                val thumbsImageIDCol =
                    imageCursor?.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)
                val thumbsSizeCol = imageCursor?.getColumnIndex(MediaStore.Images.Media.SIZE)
                var num = 0
                do {
                    imageCursor?.let { cs ->
                        thumbsIDCol?.let {
                            thumbsID = cs.getString(it)
                        }
                        thumbsDataCol?.let {
                            thumbsData = cs.getString(it)
                        }
                        thumbsImageIDCol?.let {
                            thumbsImageID = cs.getString(it)
                        }
                        thumbsSizeCol?.let {
                            imgSize = cs.getString(it)
                        }
                    }

                    num++
                    thumbsData?.let {
                        listThumbsData.add(it)
                    }
                } while (imageCursor?.moveToPrevious() == true)
            }
        }

    override fun onDestroy() {
        super.onDestroy()
        imageCursor?.let {
            if (!it.isClosed) {
                it.close()
            }
        }
    }

    /**
     * GalleryAdapter
     */
    inner class GalleryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        private val mDataList = ArrayList<Any>()

        fun addAllDataList(list: Collection<Any>) {
            mDataList.addAll(list)
        }

        override fun getItemCount(): Int {
            return mDataList.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val itemView = LayoutInflater.from(this@LGalleryActivity)
                .inflate(R.layout.l_v_l_gallery, parent, false)
            return PhotoItemHolder(itemView)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val listHolder = holder as PhotoItemHolder
            val metrics = resources.displayMetrics
            val padding = 5
            val imgW = (metrics.widthPixels - ConvertUtils.dp2px(padding.toFloat())) / 3.toFloat()
            val params =
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, imgW.toInt())
            listHolder.layoutPhoto.layoutParams = params
            val file = File(listThumbsData[position])
            listHolder.ivPhoto.loadGlide(
                any = file,
                resPlaceHolder = R.color.colorPrimary,
                resError = R.color.red,
                transformation = null,
                drawableRequestListener = null
            )
            listHolder.ivPhoto.setOnClickListener {
                val intent = Intent()
                intent.putExtra(RETURN_VALUE, file.path)
                setResult(RESULT_OK, intent)
                finish()//correct
                this@LGalleryActivity.tranOut()
            }
        }
    }

    class PhotoItemHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var layoutPhoto: RelativeLayout = itemView.findViewById(R.id.layoutPhoto)
        var ivPhoto: ImageView = itemView.findViewById(R.id.ivPhoto)
    }
}
