package vn.loitp.app.activity.customviews.recyclerview.fastscroll

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.reddit.indicatorfastscroll.FastScrollItemIndicator
import kotlinx.android.synthetic.main.layout_fast_scroll_sample_basic.*
import vn.loitp.app.R
import vn.loitp.app.activity.customviews.recyclerview.fastscroll.adapter.SampleAdapter
import vn.loitp.app.activity.customviews.recyclerview.fastscroll.db.ListItem
import vn.loitp.app.activity.customviews.recyclerview.fastscroll.db.SAMPLE_DATA_TEXT_AND_HEADERS
import java.util.*

class TextWithIconFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.layout_fast_scroll_sample_basic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = SAMPLE_DATA_TEXT_AND_HEADERS

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(HeaderSpacerItemDecoration(data::get))
            adapter = SampleAdapter(data)
        }

        fastScrollerView.apply {
            setupWithRecyclerView(
                    recyclerView,
                    { position ->
                        data[position]
                                .takeIf(ListItem::showInFastScroll)
                                ?.let { item ->
                                    when (item) {
                                        is ListItem.HeaderItem -> FastScrollItemIndicator.Icon(item.iconRes)
                                        is ListItem.DataItem ->
                                            FastScrollItemIndicator.Text(
                                                    item
                                                            .title
                                                            .substring(0, 1)
                                                            .toUpperCase(Locale.getDefault())
                                            )
                                    }
                                }
                    }
            )
        }

        fastScrollerThumbView.apply {
            setupWithFastScroller(fastScrollerView = fastScrollerView)
        }
    }
}
