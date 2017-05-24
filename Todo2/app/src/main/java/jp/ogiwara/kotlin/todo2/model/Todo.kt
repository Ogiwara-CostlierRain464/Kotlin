package jp.ogiwara.kotlin.todo2.model

import java.util.*

data class Todo(val message: String,val completed: Boolean = false,
                val id: UUID = UUID.randomUUID())