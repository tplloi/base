package vn.loitp.app.activity.demo.epubreader

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.core.base.BaseFontActivity
import com.core.utilities.LDialogUtil
import com.core.utilities.LReaderUtil
import com.core.utilities.LUIUtil
import com.function.epub.model.BookInfo
import com.function.epub.task.GetListBookAllAssetTask
import com.function.epub.task.GetListBookFromDeviceAndAssetTask
import kotlinx.android.synthetic.main.activity_demo_epub_reader.*
import vn.loitp.app.R

class EpubReaderMenuActivity : BaseFontActivity() {
    private var getListBookFromDeviceAndAssetTask: GetListBookFromDeviceAndAssetTask? = null
    private var getListBookAllAssetTask: GetListBookAllAssetTask? = null

    companion object {
        private const val MAX_BOOK_ASSET = 1
        private const val EXTENSION_EPUB = ".sqlite"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gridBookInfo.onItemClickListener = AdapterView.OnItemClickListener { adapterView: AdapterView<*>, _: View?, i: Int, _: Long ->
            val bookInfo = adapterView.adapter.getItem(i) as BookInfo
            LReaderUtil.readEpub(activity = activity, bookInfo = bookInfo, admobAdIdBanner = getString(R.string.str_b))
        }
        LUIUtil.setDelay(mls = 1000, runnable = Runnable {
            ask()
        })
    }

    private fun ask() {
        LDialogUtil.showDialog2(context = activity, title = "Chọn", msg = "Có 2 option",
                button1 = "Load tất cả epub có trong device và 1 file ở asset",
                button2 = "Load tất cả epub trong asset",
                callback2 = object : LDialogUtil.Callback2 {
                    override fun onClick1() {
                        //lấy list epub ở trên all device và 1 file ở asset folder rồi show lên UI
                        getListBookFromDeviceAndAssetTask = GetListBookFromDeviceAndAssetTask(activity, object : GetListBookFromDeviceAndAssetTask.Callback {
                            override fun onPreExecute() {
                                LDialogUtil.showProgress(progressBar)
                            }

                            override fun onPostExecute(bookInfoList: List<BookInfo>) {
                                logD("onPostExecute " + bookInfoList.size)
                                LDialogUtil.hideProgress(progressBar)
                                val adapter = BookInfoGridAdapter(activity, bookInfoList)
                                gridBookInfo.adapter = adapter
                            }
                        })
                        getListBookFromDeviceAndAssetTask?.execute()
                    }

                    override fun onClick2() {
                        getListBookAllAssetTask = GetListBookAllAssetTask(applicationContext, MAX_BOOK_ASSET, EXTENSION_EPUB, object : GetListBookAllAssetTask.Callback {
                            override fun onPreExecute() {
                                LDialogUtil.showProgress(progressBar)
                            }

                            override fun onPostExecute(bookInfoList: MutableList<BookInfo>) {
                                logD("onPostExecute " + bookInfoList.size)
                                LDialogUtil.hideProgress(progressBar)
                                bookInfoList.addAll(bookInfoList)
                                /*bookInfoList.addAll(bookInfoList);
                                        bookInfoList.addAll(bookInfoList);
                                        bookInfoList.addAll(bookInfoList);
                                        bookInfoList.addAll(bookInfoList);
                                        bookInfoList.addAll(bookInfoList);
                                        bookInfoList.addAll(bookInfoList);
                                        bookInfoList.addAll(bookInfoList);
                                        bookInfoList.addAll(bookInfoList);
                                        bookInfoList.addAll(bookInfoList);*/
                                val adapter = BookInfoGridAdapter(activity, bookInfoList)
                                gridBookInfo.adapter = adapter
                            }
                        })
                        getListBookAllAssetTask?.execute()
                    }
                })
    }

    override fun onDestroy() {
        getListBookFromDeviceAndAssetTask?.cancel(true)
        getListBookAllAssetTask?.cancel(true)
        super.onDestroy()
    }

    override fun setFullScreen(): Boolean {
        return false
    }

    override fun setTag(): String? {
        return javaClass.simpleName
    }

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_demo_epub_reader
    }
}
