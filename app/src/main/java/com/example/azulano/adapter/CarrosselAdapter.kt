package com.example.azulano.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.azulano.databinding.CarrosselBinding
import com.example.azulano.model.Carrossel

class CarrosselAdapter(private val context: Context, val listaCarrossel: MutableList<Carrossel>): RecyclerView.Adapter<CarrosselAdapter.CarrosselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarrosselViewHolder {
        val itemLista = CarrosselBinding.inflate(LayoutInflater.from(context),parent,false)
        return CarrosselViewHolder(itemLista)
    }

    override fun getItemCount() = listaCarrossel.size

    override fun onBindViewHolder(holder: CarrosselViewHolder, position: Int) {
        holder.imgCarrossel.setImageResource(listaCarrossel[position].img!!)
    }
    inner class CarrosselViewHolder(binding: CarrosselBinding): RecyclerView.ViewHolder(binding.root){
        val imgCarrossel = binding.carrossel

    }
}