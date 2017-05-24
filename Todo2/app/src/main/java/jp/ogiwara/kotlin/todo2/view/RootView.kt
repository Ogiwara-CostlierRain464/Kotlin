package jp.ogiwara.kotlin.todo2.view

import android.content.Context
import jp.ogiwara.kotlin.todo2.buildMapCounterToCounterViewModel
import jp.ogiwara.kotlin.todo2.todoView
import jp.ogiwara.kotlin.todo2.view.adapter.BansaRenderableRecyclerViewAdapter
import trikita.anvil.RenderableView

class RootView(c: Context): RenderableView(c) {

    val adapter = BansaRenderableRecyclerViewAdapter(
            ::buildMapCounterToCounterViewModel,
            ::todoView,
            { models,pos ->
                models[pos].id.leastSignificantBits
            },
            true
    )

    override fun view() {

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }
}