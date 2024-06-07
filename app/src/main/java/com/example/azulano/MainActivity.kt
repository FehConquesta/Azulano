package com.example.azulano

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.azulano.adapter.CarrosselAdapter
import com.example.azulano.databinding.ActivityMainBinding
import com.example.azulano.model.Carrossel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var carrosselAdapter: CarrosselAdapter
    private val listaCarrossel: MutableList<Carrossel> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewCarrossel = binding.recyclerView
        recyclerViewCarrossel.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        recyclerViewCarrossel.setHasFixedSize(true)
        carrosselAdapter = CarrosselAdapter(this,listaCarrossel)
        recyclerViewCarrossel.adapter = carrosselAdapter
        getImages()

    }
    private fun getImages(){
        val imagem1 = Carrossel(R.drawable.cavalo_marinho)
        listaCarrossel.add(imagem1)

        val imagem2 = Carrossel(R.drawable.estrela)
        listaCarrossel.add(imagem2)

        val imagem3 = Carrossel(R.drawable.moreia)
        listaCarrossel.add(imagem3)

        val imagem4 = Carrossel(R.drawable.tartaruga)
        listaCarrossel.add(imagem4)


    }

}