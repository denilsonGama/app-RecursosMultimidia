package br.com.fiap.recursosmultimidia


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class Rotas_e_Pontos_de_Interesse : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rotas_e_pontos_de_interesse)

        /*
          Rota 1: Paulista até Vila Olimpia
          Rota 2: Vila Olimpia até Vila Mariana
          Rota 3: Vila Mariana até Paulista

          Museu do Catavento Cultural
          Sabina Escola Parque do Conhecimento
          Museu de Microbiologia
          Planetário do Ibirapuera
          Parque Cientec
       */
    }

    //-- Rotas
    fun rotas_e_pontos_de_interesse_rota1(view: View) {
        val intent = Intent(this, Rotas_e_Pontos_de_Interesse_Rota1::class.java)
        startActivity(intent)
    }

    fun rotas_e_pontos_de_interesse_rota2(view: View) {
        val intent = Intent(this, Rotas_e_Pontos_de_Interesse_Rota2::class.java)
        startActivity(intent)
    }

    fun rotas_e_pontos_de_interesse_rota3(view: View) {
        val intent = Intent(this, Rotas_e_Pontos_de_Interesse_Rota3::class.java)
        startActivity(intent)
    }

    //-- Pontos de Interesse
    fun rotas_e_pontos_de_interesse_ponto1(view: View) {
        val intent = Intent(this, Rotas_e_Pontos_de_Interesse_Ponto1::class.java)
        startActivity(intent)
    }

    fun rotas_e_pontos_de_interesse_ponto2(view: View) {
        val intent = Intent(this, Rotas_e_Pontos_de_Interesse_Ponto2::class.java)
        startActivity(intent)
    }

    fun rotas_e_pontos_de_interesse_ponto3(view: View) {
        val intent = Intent(this, Rotas_e_Pontos_de_Interesse_Ponto3::class.java)
        startActivity(intent)
    }

    fun rotas_e_pontos_de_interesse_ponto4(view: View) {
        val intent = Intent(this, Rotas_e_Pontos_de_Interesse_Ponto4::class.java)
        startActivity(intent)
    }

    fun rotas_e_pontos_de_interesse_ponto5(view: View) {
        val intent = Intent(this, Rotas_e_Pontos_de_Interesse_Ponto5::class.java)
        startActivity(intent)
    }
}