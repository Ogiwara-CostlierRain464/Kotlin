package jp.ogiwara.kotlin.todo2.action

import com.brianegan.bansa.Action
import jp.ogiwara.kotlin.todo2.state.TodoApplicationState
import java.util.*


data class INIT(val state: TodoApplicationState = TodoApplicationState()): Action
data class ADD(val message: String): Action
data class REMOVE(val id: UUID): Action
data class TOGGLE_TODO(val id: UUID,val completed: Boolean): Action
data class UPDATE_TODO_MESSAGE(val message: String): Action