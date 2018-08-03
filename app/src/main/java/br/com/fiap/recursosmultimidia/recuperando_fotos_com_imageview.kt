package br.com.fiap.recursosmultimidia

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_recuperando_fotos_com_imageview.view.*

class Recuperando_Fotos_Com_ImageView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperando_fotos_com_imageview)

        //-- Declarando e vinculando os objetos de ImageView
        val image1 = findViewById<ImageView>(R.id.imageView1)
        val image2 = findViewById<ImageView>(R.id.imageView2)
        val image3 = findViewById<ImageView>(R.id.imageView3)
        val image4 = findViewById<ImageView>(R.id.imageView4)
        val image5 = findViewById<ImageView>(R.id.imageView5)
        val image6 = findViewById<ImageView>(R.id.imageView6)

        //-- Declarando e vinculando os objetos TextView
        val text1 = findViewById<TextView>(R.id.textView1)
        val text2 = findViewById<TextView>(R.id.textView2)
        val text3 = findViewById<TextView>(R.id.textView3)
        val text4 = findViewById<TextView>(R.id.textView4)
        val text5 = findViewById<TextView>(R.id.textView5)
        val text6 = findViewById<TextView>(R.id.textView6)

        //-- Declarando um array com as equipes principais da F1
        var equipes = arrayOf("Ferrari","Mercedes","Renault","McLaren","Red-Bull","Haas")

        //-- Adicionando o texto nos objetos visuais
        text1.setText(equipes[0])
        text2.setText(equipes[1])
        text3.setText(equipes[2])
        text4.setText(equipes[3])
        text5.setText(equipes[4])
        text6.setText(equipes[5])

        //-- Declarando as variáveis das imagens que serão apresentados nos objetos visuais
        var i1 = "https://www.formula1.com/content/fom-website/en/championship/teams/Ferrari/_jcr_content/teamCar.img.jpg"
        var i2 = "https://www.formula1.com/content/fom-website/en/championship/teams/Mercedes/_jcr_content/teamCar.img.jpg"
        var i3 = "https://www.formula1.com/content/fom-website/en/championship/teams/Renault/_jcr_content/teamCar.img.jpg"

        var i4 = "https://www.formula1.com/content/fom-website/en/championship/teams/McLaren/_jcr_content/teamCar.img.jpg"
        var i5 = "https://www.formula1.com/content/fom-website/en/championship/teams/Red-Bull/_jcr_content/teamCar.img.jpg"
        var i6 = "https://www.formula1.com/content/fom-website/en/championship/teams/Haas/_jcr_content/teamCar.img.jpg"

        //-- Utilizando o framework Glide para recuperar a informação
        //-- das imagens que estão fora do dispositivo mobile
        Glide.with(this).load(i1).into(image1!!)
        Glide.with(this).load(i2).into(image2!!)
        Glide.with(this).load(i3).into(image3!!)

        Glide.with(this).load(i4).into(image4!!)
        Glide.with(this).load(i5).into(image5!!)
        Glide.with(this).load(i6).into(image6!!)
    }
}