package jp.ogiwara.test.kotlin.todo

import android.widget.LinearLayout
import com.brianegan.bansa.Store
import trikita.anvil.DSL.*

fun todoView(model: TodoViewModel) {
    linearLayout {
        size(FILL, WRAP)
        orientation(LinearLayout.HORIZONTAL)
        margin(0, dip(10))
        onClick {
            model.clickHandler()
        }

        textView {
            size(0, WRAP)
            weight(1f)
            text(model.todo.message)
            padding(0, dip(4))
            textSize(sip(16F))
        }

        checkBox {
            size(WRAP, WRAP)
            checked(model.todo.completed)
        }
    }
}

fun buildMapCounterToCounterViewModel(store: Store<ApplicationState>): (Todo) -> TodoViewModel {
    return { todo ->
        TodoViewModel(
                todo,
                { store.dispatch(TOGGLE_TODO(todo.id, !todo.completed)) }
        )
    }
}

data class TodoViewModel(val todo: Todo, val clickHandler: () -> Unit)