package jp.ogiwara.kotlin.todo2.state

import com.github.andrewoma.dexx.kollection.ImmutableList
import com.github.andrewoma.dexx.kollection.immutableListOf
import jp.ogiwara.kotlin.todo2.model.Todo

data class TodoApplicationState(val newTodoMessage: String = "",
                                val todos: ImmutableList<Todo> = immutableListOf())