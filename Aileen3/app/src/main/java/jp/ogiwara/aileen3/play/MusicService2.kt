package jp.ogiwara.aileen3.play

import android.media.MediaPlayer
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaBrowserServiceCompat
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.session.MediaButtonReceiver
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.text.TextUtils
import android.util.Log
import jp.ogiwara.aileen3.i


class MusicService2 : MediaBrowserServiceCompat(){

    val TAG = "MusicService2"
    val MEDIA_ID_ROOT = "__ROOT__"
    val NOTIFICATION_ID = 412

    lateinit var mediaSession: MediaSessionCompat


    override fun onCreate() {
        super.onCreate()

        mediaSession = MediaSessionCompat(applicationContext,TAG)

        // Enable callbacks from MediaButtons and TransportControls
        /*mediaSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or
                MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS)*/

        val stateBuilder = PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY or
                        PlaybackStateCompat.ACTION_PLAY_PAUSE)

        //mediaSession.setPlaybackState(stateBuilder.build())

        mediaSession.setCallback(MySessionCallBack())

        //内部埋め込み
        sessionToken = mediaSession.sessionToken
    }

    override fun onGetRoot(clientPackageName: String, clientUid: Int, rootHints: Bundle?): BrowserRoot? {
        return BrowserRoot(MEDIA_ID_ROOT,null)
    }

    override fun onLoadChildren(parentId: String, result: Result<MutableList<MediaBrowserCompat.MediaItem>>) {
        if(TextUtils.isEmpty(parentId)){
            result.sendResult(null)
            return
        }

        val mediaItems = ArrayList<MediaBrowserCompat.MediaItem>()

        val mdb = MediaDescriptionCompat.Builder()
        mdb.setTitle("Title")
        mdb.setSubtitle("Subtitle")
        mdb.setDescription("This is test song")
        mdb.setMediaId("MediaID01")

        mediaItems.add(MediaBrowserCompat.MediaItem(mdb.build(),MediaBrowserCompat.MediaItem.FLAG_PLAYABLE))

        result.sendResult(mediaItems)
    }

    private inner class MySessionCallBack: MediaSessionCompat.Callback(){
        override fun onPlay() {
            super.onPlay()
            Log.i("onPlay",TAG)

            val controller = mediaSession.controller
            //val mediaMetadata = controller.metadata //is null
            //val description = mediaMetadata.description

            val builder =NotificationCompat.Builder(applicationContext)

            builder.setContentTitle("Title")
                    .setContentText("Subtitle")
                    .setSubText("SubText")
                    .setContentIntent(controller.sessionActivity)
                    .setDeleteIntent(MediaButtonReceiver.buildMediaButtonPendingIntent(this@MusicService2,
                            PlaybackStateCompat.ACTION_STOP))
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .addAction(NotificationCompat.Action(
                            android.R.drawable.ic_media_pause,"Pause",
                            MediaButtonReceiver.buildMediaButtonPendingIntent(this@MusicService2,
                                    PlaybackStateCompat.ACTION_PLAY_PAUSE)
                    ))
                    .setStyle(android.support.v7.app.NotificationCompat.MediaStyle()
                            .setMediaSession(mediaSession.sessionToken)
                            .setShowActionsInCompactView(0)
                            .setShowCancelButton(true)
                            .setCancelButtonIntent(MediaButtonReceiver.buildMediaButtonPendingIntent(this@MusicService2,
                                    PlaybackStateCompat.ACTION_STOP)))

            startForeground(NOTIFICATION_ID,builder.build())

        }

        override fun onPause() {
            super.onPause()
            Log.i("onPause",TAG)
        }

        override fun onStop() {
            super.onStop()
        }

        override fun onSkipToNext() {
            super.onSkipToNext()
        }

        override fun onSkipToPrevious() {
            super.onSkipToPrevious()
        }

        override fun onPlayFromMediaId(mediaId: String?, extras: Bundle?) {
            super.onPlayFromMediaId(mediaId, extras)
        }
    }
}