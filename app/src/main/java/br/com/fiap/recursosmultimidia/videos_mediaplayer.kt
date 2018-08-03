package br.com.fiap.recursosmultimidia

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageButton
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.VideoView

class Videos_MediaPlayer : AppCompatActivity() {

    private var btnonce: ImageButton? = null
    private var btncontinuously: ImageButton? = null
    private var btnstop: ImageButton? = null
    private var btnplay: ImageButton? = null

    private var vv: VideoView? = null

    private var mediacontroller: MediaController? = null
    private var uri: Uri? = null

    private var isContinuously = false
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videos_mediaplayer)

        progressBar = findViewById(R.id.progrss) as ProgressBar
        btnonce = findViewById(R.id.btnonce) as ImageButton
        btncontinuously = findViewById(R.id.btnconti) as ImageButton
        btnstop = findViewById(R.id.btnstop) as ImageButton
        btnplay = findViewById(R.id.btnplay) as ImageButton

        vv = findViewById(R.id.vv) as VideoView

        mediacontroller = MediaController(this)
        mediacontroller!!.setAnchorView(vv)

        val uriPath = "https://archive.org/download/WildlifeSampleVideo/Wildlife.mp4"

        uri = Uri.parse(uriPath)

        vv!!.setOnCompletionListener {
            if (isContinuously) {
                vv!!.start()
            }
        }

        btnstop!!.setOnClickListener { vv!!.pause() }

        btnplay!!.setOnClickListener { vv!!.start() }

        btnonce!!.setOnClickListener {
            isContinuously = false
            progressBar!!.visibility = View.VISIBLE
            vv!!.setMediaController(mediacontroller)
            vv!!.setVideoURI(uri)
            vv!!.requestFocus()
            vv!!.start()
        }

        btncontinuously!!.setOnClickListener {
            isContinuously = true
            progressBar!!.visibility = View.VISIBLE
            vv!!.setMediaController(mediacontroller)
            vv!!.setVideoURI(uri)
            vv!!.requestFocus()
            vv!!.start()
        }

        vv!!.setOnPreparedListener { progressBar!!.visibility = View.GONE }

    }
}