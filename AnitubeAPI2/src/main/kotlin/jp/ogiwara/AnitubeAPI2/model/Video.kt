package jp.ogiwara.AnitubeAPI2.model

data class Video(val title: String,
                 val views: String,
                 val imgUrl: String,
                 val videoUrl: String){

    /**
     * Get Video's mp4 link
     *
     * @param quality false = 360p true = 720p
     */
    fun getMp4Url(quality: Boolean = false){

    }
}
