package jp.ogiwara.kotlin.todo2.reducer

import com.brianegan.bansa.Reducer
import com.github.andrewoma.dexx.kollection.toImmutableList
import jp.ogiwara.kotlin.todo2.action.*
import jp.ogiwara.kotlin.todo2.model.Todo
import jp.ogiwara.kotlin.todo2.state.TodoApplicationState

//stateとactionを受け取って、新しいstateを返す。参照等価性が求められる。
val applicationReducer = Reducer<TodoApplicationState> { state, action ->
    //Smart Cast すんばらしい!
    when(action){
        is INIT -> TodoApplicationState()
        is ADD -> {
            if(state.newTodoMessage.isNotEmpty()){
                state.copy(
                        newTodoMessage = "",
                        todos = state.todos + Todo(action.message)
                )
            }else{
                state
            }
        }
        is REMOVE -> {
            state.copy(
                    todos = state.todos.filter { it.id == action.id }.toImmutableList()
            )
        }
        is TOGGLE_TODO -> {
            state.copy(
                    todos = state.todos.map {
                        if(it.id == action.id){
                            it.copy(completed = action.completed)
                        }else{
                            it
                        }
                    }.toImmutableList()
            )
        }
        is UPDATE_TODO_MESSAGE -> state.copy(
                newTodoMessage = action.message
        )
        else -> state
    }
}
