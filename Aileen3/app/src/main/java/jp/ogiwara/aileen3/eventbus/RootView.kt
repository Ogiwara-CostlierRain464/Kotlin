package jp.ogiwara.aileen3.eventbus

import android.content.Context
import trikita.anvil.DSL.*
import trikita.anvil.RenderableView


class RootView(c: Context): RenderableView(c) {
    override fun view() {
        frameLayout {
            textView{
                text("w")
            }
        }
    }
}