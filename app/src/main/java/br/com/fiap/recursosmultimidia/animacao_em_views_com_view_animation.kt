package br.com.fiap.recursosmultimidia


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class Animacao_em_views_com_View_Animation : AppCompatActivity() {

    internal lateinit var tv: TextView
    internal lateinit var lv: ListView

    internal var animations = arrayOf("Fade In", "Fade Out", "Zoom In", "Zoom Out", "Blink", "Rotate", "Move", "Slide Up", "Slide Down", "Bounce")
    internal var animationIDs = intArrayOf(R.anim.fade_in, R.anim.fade_out, R.anim.zoom_in, R.anim.zoom_out, R.anim.blink, R.anim.rotate, R.anim.move, R.anim.slide_up, R.anim.slide_down, R.anim.bounce)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animacao_em_views_com_view_animation)

        tv = findViewById(R.id.tv) as TextView
        lv = findViewById(R.id.lv) as ListView

        lv.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, animations)

        lv.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            val animation = AnimationUtils.loadAnimation(this@Animacao_em_views_com_View_Animation, animationIDs[position])

            tv.startAnimation(animation)
        }
    }
}
