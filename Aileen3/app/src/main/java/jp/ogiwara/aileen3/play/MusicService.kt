package jp.ogiwara.aileen3.play

import android.app.MediaRouteButton
import android.media.MediaDescription
import android.media.MediaPlayer
import android.media.browse.MediaBrowser
import android.media.session.MediaSession
import android.os.Bundle
import android.service.media.MediaBrowserService
import jp.ogiwara.aileen3.i

class MusicService: MediaBrowserService(), MediaPlayer.OnPreparedListener,
                    MediaPlayer.OnCompletionListener,MediaPlayer.OnErrorListener{

    /**
     * About MediaSession
     *
     * 音楽再生操作を行うため、MediaSessionクラスを使用しています。
     * MediaSessionクラスは、一連のメディア再生処理を「セッション」
     * とし、他のクラス(MediaControllerクラス、
     * MediaController.TransportControlsクラスなど)と紐づいて
     * 再生操作を一括して管理することができます。
     */

    lateinit var mediaSession: MediaSession
    var mediaPlayer: MediaPlayer? = null
    lateinit var playingQueue: MutableList<MediaSession.QueueItem>
    var currentQueueIndex: Int = 0
    val MEDIA_ID_ROOT = "__ROOT__"
    val TAG = MusicService::class

    override fun onCreate() {
        super.onCreate()

        playingQueue = ArrayList<MediaSession.QueueItem>()

        mediaSession = MediaSession(this,"MyMediaSession")
        sessionToken = mediaSession.sessionToken

        mediaSession.setCallback(MyMediaSessionCallback())

        currentQueueIndex = 0
    }

    override fun onGetRoot(clientPackageName: String?, clientUid: Int, rootHints: Bundle?): BrowserRoot {
        return BrowserRoot(MEDIA_ID_ROOT,null)
    }

    override fun onLoadChildren(parentId: String?, result: Result<MutableList<MediaBrowser.MediaItem>>?) {
        val mediaItems = ArrayList<MediaBrowser.MediaItem>()

        val mdb1 = MediaDescription.Builder()

        val mediaBundle1 = Bundle()
        mediaBundle1.putString("Path", "mnt/sdcard/M01.mp3") // データパス I'm sure that this will throw exception!
        mediaBundle1.putInt("Index", 0)                      // 曲のインデックス
        mdb1.setMediaId("MediaID01")                         // メディアID
        mdb1.setTitle("Title01")                             // タイトル
        mdb1.setSubtitle("SubTitle01")                       // サブタイトル
        mdb1.setExtras(mediaBundle1)

        val mdb2 = MediaDescription.Builder()

        val mediaBundle2 = Bundle()
        mediaBundle2.putString("Path", "mnt/sdcard/M02.mp3") // データパス
        mediaBundle2.putInt("Index", 1)                      // 曲のインデックス
        mdb2.setMediaId("MediaID02")                         // メディアID
        mdb2.setTitle("Title02")                             // タイトル
        mdb2.setSubtitle("SubTitle02")                       // サブタイトル
        mdb2.setExtras(mediaBundle2)

        mediaItems.add(MediaBrowser.MediaItem(mdb1.build(),
                MediaBrowser.MediaItem.FLAG_PLAYABLE))
        mediaItems.add(MediaBrowser.MediaItem(mdb2.build(),
                MediaBrowser.MediaItem.FLAG_PLAYABLE))

        result?.detach() //一回データをクリアする必要がある
        result?.sendResult(mediaItems)

        playingQueue.add(MediaSession.QueueItem(mdb1.build(),0))
        playingQueue.add(MediaSession.QueueItem(mdb2.build(),1))
        mediaSession.setQueue(playingQueue)
        //mediaSession.controller.queue.add なぜわざわざ保持する必要が?
        //なんでmediaItems と、 playingQueue作んなきゃいけないの???
        //reactiveでないから
    }

    fun playMusic(){
        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer()
            mediaPlayer?.setOnPreparedListener(this)
            mediaPlayer?.setOnCompletionListener(this)
            mediaPlayer?.setOnErrorListener(this)
        }else{
            mediaPlayer?.reset()
        }

        val queueItem = playingQueue.get(currentQueueIndex)
        val path: String = queueItem.description.extras.getString("Path")

        try{
            mediaPlayer?.setDataSource(path)
            mediaPlayer?.prepareAsync()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    //region callBack

    override fun onCompletion(mp: MediaPlayer?) {

    }

    override fun onPrepared(mp: MediaPlayer?) {
        mediaPlayer?.start()
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        return true
    }

    //endregion

    private inner class MyMediaSessionCallback: MediaSession.Callback(){
        override fun onPlay() {
            i("play",TAG)
            playMusic()
        }

        /*private fun testNotification(){
            val controller = mediaSession.controller
            val mediaMetadata = controller.metadata
            val description = mediaMetadata.description

            val builder = NotificationCompat.Builder(applicationContext)

            builder
                    .setContentTitle(description.title)
                    .setContentText(description.subtitle)
                    .setSubText(description.description)
                    .setContentIntent(controller.sessionActivity)
                    .setDeleteIntent(MediaButtonReceiver.buildMediaButtonPendingIntent(this@MusicService,
                            PlaybackStateCompat.ACTION_STOP))
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setSmallIcon(android.R.drawable.ic_notification_overlay)
                    .addAction(android.support.v4.app.NotificationCompat.Action(
                            android.R.drawable.ic_media_pause, "pause",
                            MediaButtonReceiver.buildMediaButtonPendingIntent(this@MusicService,
                                    PlaybackStateCompat.ACTION_PLAY_PAUSE)
                    ))
                    .setStyle(NotificationCompat.MediaStyle()
                            .setMediaSession(mediaSession.sessionToken))

        }*/

        override fun onPause() {
            i("pause",TAG)
            super.onPause()
        }

        override fun onSkipToNext() {
            i("skipNext",TAG)
            super.onSkipToNext()
        }

        override fun onSkipToPrevious() {
            i("skipPrevious",TAG)
            super.onSkipToPrevious()
        }

        override fun onStop() {
            i("stop",TAG)
            super.onStop()
        }

        override fun onPlayFromMediaId(mediaId: String?, extras: Bundle?) {
            (0..(playingQueue.size-1)).forEach {
                val queueItem = playingQueue[it]
                val md = queueItem.description
                if(mediaId == md.mediaId){
                    currentQueueIndex = md.extras.getInt("Index")
                }
            }
            playMusic()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        //ただの購読終了処理
        mediaSession.setCallback(null)
        mediaSession.release()

        if(mediaPlayer != null){
            mediaPlayer?.setOnPreparedListener(null)
            mediaPlayer?.setOnCompletionListener(null)
            mediaPlayer?.setOnErrorListener(null)
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }
}