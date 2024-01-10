package com.assignment.starwars.ui.film

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.starwars.databinding.ListFilmsBinding
import com.assignment.starwars.models.FilmResponse

class FilmAdapter(private var filmList: MutableList<FilmResponse>) :
    RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListFilmsBinding.inflate(inflater, parent, false)
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = filmList[position]
        holder.bind(film)
    }

    override fun getItemCount(): Int = filmList.size

    fun updateData(newFilmList: MutableList<FilmResponse>) {
        filmList = newFilmList
        notifyDataSetChanged()
    }

    inner class FilmViewHolder(private val binding: ListFilmsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(film: FilmResponse) {
            binding.filmName.text = film.title
        }
    }
}
