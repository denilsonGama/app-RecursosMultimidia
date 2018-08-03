package br.com.fiap.recursosmultimidia

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Typeface
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

@Suppress("DEPRECATION")
class Localizacao_do_usuario_com_Location_API : AppCompatActivity(),
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener,
        OnMapReadyCallback {

    private lateinit var mapa       : GoogleMap
    private var mGoogleApiClient    : GoogleApiClient? = null

    private var mLocation           : Location? = null
    private var mLocationManager    : LocationManager? = null
    private var mLocationRequest    : LocationRequest? = null
    private var locationManager     : LocationManager? = null

    private val isLocationEnabled   : Boolean
        get() {
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            return locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        }

    private val UPDATE_INTERVAL = (2 * 10000).toLong()
    private val FASTEST_INTERVAL    : Long = 2000
    //-------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_localizacao_do_usuario_com_location_api)

        val mapFragment =
                supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build()

        mLocationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        checkLocation()
    }
    //-------------------------------------------------------------------------
    @SuppressLint("MissingPermission")
    override fun onConnected(p0: Bundle?) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }

        startLocationUpdates()

        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient)

        if (mLocation == null) {
            startLocationUpdates()
        } else {
            Toast.makeText(this, "Location not Detected", Toast.LENGTH_SHORT).show()
        }
    }
    //-------------------------------------------------------------------------
    override fun onConnectionSuspended(i: Int) {
        mGoogleApiClient!!.connect()
    }
    //-------------------------------------------------------------------------
    override fun onConnectionFailed(connectionResult: ConnectionResult) {
    }
    //-------------------------------------------------------------------------
    override fun onStart() {
        super.onStart()
        if (mGoogleApiClient != null) {
            mGoogleApiClient!!.connect()
        }
    }
    //-------------------------------------------------------------------------
    override fun onStop() {
        super.onStop()
        if (mGoogleApiClient!!.isConnected()) {
            mGoogleApiClient!!.disconnect()
        }
    }
    //-------------------------------------------------------------------------
    @SuppressLint("MissingPermission")
    protected fun startLocationUpdates() {

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                mLocationRequest, this)
    }
    //-------------------------------------------------------------------------
    override fun onLocationChanged(location: Location) {

        val msg = "Updated Your Location:\n\n" +
                java.lang.Double.toString(location.latitude) + "," +
                java.lang.Double.toString(location.longitude)

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

        val latLng = LatLng(location.latitude, location.longitude)

        updateMap(mapa,latLng)
    }
    //-------------------------------------------------------------------------
    private fun checkLocation(): Boolean {
        return isLocationEnabled
    }
    //----------------------------------------------------------------------------
    /**
     * Esta função manipula o mapa com todos os detalhes necessários
     * para a apresentação de informações, tais como, cores dos pins,
     * textos de títulos e complementos de endereços.
     *
     * Inserimos dados de latitute e lontitude fixos com as unidades da FIAP
     */
    //-------------------------------------------------------------------------
    override fun onMapReady(googleMap: GoogleMap) {

        updateMap(googleMap, null)
    }
    //-------------------------------------------------------------------------
    fun updateMap(googleMap: GoogleMap, latLng: LatLng?) {

        //-- vincula os objetos
        mapa = googleMap

        //--Adiciona a latitude e longitude da FIAP Campus Vila Mariana
        var myTitle    = "FIAP Campus Vila Mariana"
        var mySnippet  = "Av. Lins de Vasconcelos,1264\nSão Paulo - SP\nCEP: 01538-001"
        var myLocation = LatLng(-23.5746685,-46.6232043)

        if(latLng != null) {
            myLocation = latLng
            myTitle = "Localização capturada"
            mySnippet = "Veja os detalhes\nda sua localização."
        }

        //-- Faixa de cores dos Pins no Mapa
        var bitmap = arrayOf(0.0F,30.0F,60.0F,120.0F,180.0F,210.0F,240.0F,270.0F,300.0F,330.0F)
        var bitmapSorted = bitmap[((Math.random() * bitmap.size).toInt())]

        //--Insere no objeto mapa
        mapa.addMarker(MarkerOptions()
                .position(myLocation)
                .title(myTitle)
                .snippet(mySnippet)
                .icon(BitmapDescriptorFactory.defaultMarker(bitmapSorted))
        )

        //--Movimenta a camera para que a visualização aparece o mais perto dos
        //--endereços das unidades. O valor float 12.5F indica a distância da comera
        //--que pode varia entre 0.0F e 21.0F
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 12.5F));

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
    //-------------------------------------------------------------------------
}