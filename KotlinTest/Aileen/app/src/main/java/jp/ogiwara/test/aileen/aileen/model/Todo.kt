package jp.ogiwara.test.aileen.aileen.model

import java.util.*

data class Todo(var message: String,
                var completed: Boolean = false,
                val id: UUID = UUID.randomUUID())