package com.assignment.starwars.ui.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.starwars.databinding.ListItemNameBinding
import com.assignment.starwars.models.Person
import com.assignment.starwars.utils.Constants.STAR_WARS_IAMGES
import kotlin.random.Random

class PeopleAdapter(private val onItemClickListener: (Person) -> Unit) :
    PagingDataAdapter<Person, PeopleAdapter.PeopleViewHolder>(COMPARATOR) {
    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { person ->
            holder.bind(person)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val binding =
            ListItemNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PeopleViewHolder(binding, onItemClickListener)
    }

    class PeopleViewHolder(
        private val binding: ListItemNameBinding,
        private val onItemClickListener: (Person) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Person) {
            binding.apply {
                name.text = item.name
                height.text = item.height
                mass.text = item.mass
                starships.text  = item.starships?.size.toString()
                homeWorld.text = if(item.gender == "male")"M" else if(item.gender == "female") "F" else "-"
                itemImage.setImageResource(
                    STAR_WARS_IAMGES[Random.nextInt(0, STAR_WARS_IAMGES.size)]
                )
                itemView.setOnClickListener {
                    onItemClickListener(item)
                }
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
                return oldItem == newItem
            }
        }
    }
}
