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

class Utilizando_Mapas_MapView : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapa: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utilizando_mapas__map_view)

        val mapFragment =
                supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    /**
     * Esta função manipula o mapa com todos os detalhes necessários
     * para a apresentação de informações, tais como, cores dos pins,
     * textos de títulos e complementos de endereços.
     *
     * Inserimos dados de latitute e lontitude fixos com as unidades da FIAP
     */
    override fun onMapReady(googleMap: GoogleMap) {

        //-- vincula os objetos
        mapa = googleMap

        //-- prepara uma coleção de informações das unidades da FIAP
        //-- A informação irá pular uma linha
        val unidades = arrayOf(
                arrayOf("FIAP Campus Vila Olimpia","Rua Olimpíadas,186\nSão Paulo - SP\nCEP: 04551-000"),
        arrayOf("FIAP Campus Paulista","Av. Paulista,1106\nSão Paulo - SP\nCEP: 01311-000"),
        arrayOf("FIAP Campus Vila Mariana","Av. Lins de Vasconcelos,1264\nSão Paulo - SP\nCEP: 01538-001")
        )

        //--Adiciona a latitude e longitude da FIAP Campus Vila Olimpia
        val fiap_campus_vila_olimpia = LatLng(-23.5955843,-46.6851937)

        //--Adiciona a latitude e longitude da FIAP Campus Paulista
        val fiap_campus_paulista = LatLng(-23.5643721,-46.652857)

        //--Adiciona a latitude e longitude da FIAP Campus Vila Mariana
        val fiap_campus_vila_mariana = LatLng(-23.5746685,-46.6232043)

        //--Insere no objeto mapa os dados da unidade 1
        mapa.addMarker(MarkerOptions()
                .position(fiap_campus_vila_olimpia)
                .title(unidades[0][0])
                .snippet(unidades[0][1])
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        )

        //--Insere no objeto mapa os dados da unidade 2
        mapa.addMarker(MarkerOptions()
                .position(fiap_campus_paulista)
                .title(unidades[1][0])
                .snippet(unidades[1][1])
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
        )

        //--Insere no objeto mapa os dados da unidade 3
        mapa.addMarker(MarkerOptions()
                .position(fiap_campus_vila_mariana)
                .title(unidades[2][0])
                .snippet(unidades[2][1])
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
        )

        //--Movimenta a camera para que a visualização aparece o mais perto dos
        //--endereços das unidades. O valor float 12.5F indica a distância da comera
        //--que pode varia entre 0.0F e 21.0F
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(fiap_campus_paulista, 12.5F));

        //--Configura a exibição dos títulos e endereços das unidades FIAP
        //--de maneira personalizada
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