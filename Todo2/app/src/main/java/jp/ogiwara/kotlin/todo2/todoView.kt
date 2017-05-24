package jp.ogiwara.kotlin.todo2

import android.widget.LinearLayout
import com.brianegan.bansa.Store
import jp.ogiwara.kotlin.todo2.action.REMOVE
import jp.ogiwara.kotlin.todo2.action.TOGGLE_TODO
import jp.ogiwara.kotlin.todo2.model.Todo
import jp.ogiwara.kotlin.todo2.state.TodoApplicationState
import trikita.anvil.BaseDSL
import trikita.anvil.DSL.*

fun todoView(model: TodoViewModel){
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
        button {
            size(WRAP,WRAP)
            text("X")
            margin(5)
            onClick {
                store.dispatch(REMOVE(model.todo.id))
            }
        }

        checkBox {
            size(WRAP, WRAP)
            checked(model.todo.completed)
        }
    }
}

fun buildMapCounterToCounterViewModel(): (Todo) -> TodoViewModel{
    return { todo ->
        TodoViewModel(
                todo,
                {store.dispatch(TOGGLE_TODO(todo.id,todo.completed))}
        )
    }
}

data class TodoViewModel(val todo: Todo,val clickHandler: () -> Unit)
