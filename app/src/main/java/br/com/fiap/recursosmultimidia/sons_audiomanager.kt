package br.com.fiap.recursosmultimidia

import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class Sons_AudioManager : AppCompatActivity() {

    var cow: MediaPlayer? = null
    var dog: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sons_audiomanager)

        cow = MediaPlayer.create(this, R.raw.cow)
        dog = MediaPlayer.create(this, R.raw.dog)
    }

    fun soundCow(view: View) {

       /* if(dog != null) {
            dog!!.stop()
        }*/
        cow!!.start()
    }

    fun soundDog(view: View) {

      /*  if(cow != null) {
            cow!!.stop()
        }*/
        dog!!.start()
    }
}




