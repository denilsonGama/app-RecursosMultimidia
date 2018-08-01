package br.com.fiap.recursosmultimidia

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class Rotas_e_Pontos_de_Interesse_Ponto4 : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapa: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rotas_e_pontos_de_interesse_ponto4)

        val mapFragment =
                supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    /**
     * Função para o gerenciamento de informações no mapa
     */
    override fun onMapReady(googleMap: GoogleMap) {

        //-- vincula os objetos
        mapa = googleMap

        //-- Dados do ponto de interesse
        val ponto_de_interesse_informacoes =
                arrayOf("Planetário do Ibirapuera",
                        "Parque Ibirapuera\nAv. Pedro Álvares Cabral, s/n\nSão Paulo - SP")

        //--Coordenadas do Ponto de Interesse
        val ponto_de_interesse_latitude_longitude = LatLng(-23.5877308,-46.6615389)

        //--Insere no objeto mapa os dados do ponto do interesse
        mapa.addMarker(MarkerOptions()
                .position(ponto_de_interesse_latitude_longitude)
                .title(ponto_de_interesse_informacoes[0])
                .snippet(ponto_de_interesse_informacoes[1])
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
        )

        //--Movimenta a camera para que a visualização aparece o mais perto dos
        //--endereços das unidades. O valor float 12.5F indica a distância da comera
        //--que pode varia entre 0.0F e 21.0F
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(ponto_de_interesse_latitude_longitude, 15.5F));

        //-- MAP_TYPE_NORMAL
        //-- MAP_TYPE_TERRAIN
        //-- MAP_TYPE_HYBRID
        //-- MAP_TYPE_NONE
        //-- MAP_TYPE_SATELLITE
        mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //--Configura a exibição de informações de maneira personalizada
        mapa.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {

            override fun getInfoWindow(arg0: Marker): View? {
                return null
            }

            override fun getInfoContents(marker: Marker): View {

                val info = LinearLayout(applicationContext)
                info.orientation = LinearLayout.VERTICAL

                //--Título
                val title = TextView(applicationContext)
                title.setTextColor(Color.BLACK)
                title.gravity = Gravity.LEFT
                title.setTypeface(null, Typeface.BOLD)
                title.text = marker.title

                //--Complemento
                val snippet = TextView(applicationContext)
                snippet.setTextColor(Color.GRAY)
                snippet.text = marker.snippet

                //--Adiciona o titulo e o complemento na marca
                info.addView(title)
                info.addView(snippet)

                return info
            }
        })
    }
}
