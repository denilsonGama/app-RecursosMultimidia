package br.com.fiap.recursosmultimidia

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        14.1 Recuperando fotos com ImageView
        14.2 Utilizando mapas: MapView
        14.3 Localização do usuário com Location API
        14.4 Rotas e Pontos de Interesse
        14.5 Usando WebView em seus Apps
        14.6 Sons: AudioManager
        14.7 Vídeos: MediaPlayer
        14.8 Animação em views com View Animation
         */
    }

    /*fun animacao_em_views_com_view_animation(view: View) {
        val intent = Intent(this, Animacao_em_views_com_View_Animation::class.java)
        startActivity(intent)
    }*/

    fun localizacao_do_usuario_com_location_api(view: View) {
        val intent = Intent(this, Localizacao_do_usuario_com_Location_API::class.java)
        startActivity(intent)
    }

    fun recuperando_fotos_com_imageview(view: View) {
        val intent = Intent(this, Recuperando_Fotos_Com_ImageView::class.java)
        startActivity(intent)
    }

    fun rotas_e_pontos_de_interesse(view: View) {
        val intent = Intent(this, Rotas_e_Pontos_de_Interesse::class.java)
        startActivity(intent)
    }

    fun sons_audiomanager(view: View) {
        val intent = Intent(this, Sons_AudioManager::class.java)
        startActivity(intent)
    }

    fun usando_webview_em_seus_apps(view: View) {
        val intent = Intent(this, Usando_WebView_em_seus_Apps::class.java)
        startActivity(intent)
    }

    fun utilizando_mapas_mapview(view: View) {
        val intent = Intent(this, Utilizando_Mapas_MapView::class.java)
        startActivity(intent)
    }

    fun videos_mediaplayer(view: View) {
        val intent = Intent(this, Videos_MediaPlayer::class.java)
        startActivity(intent)
    }
}