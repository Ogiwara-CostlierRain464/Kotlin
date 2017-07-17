package jp.ogiwara.aileen3.other.model

data class Video(val id: String,
                 val title: String,
                 val channelName: String,
                 val thumbnailUrl: String?,
                 val duration: String,
                 val viewCount: String)