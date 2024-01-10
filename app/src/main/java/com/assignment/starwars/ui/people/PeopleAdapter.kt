package com.assignment.starwars.ui.people

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.starwars.R
import com.assignment.starwars.models.Person
import com.assignment.starwars.utils.Constants.STAR_WARS_IAMGES
import kotlin.random.Random

class PeopleAdapter : PagingDataAdapter<Person, PeopleAdapter.PeopleViewHolder>(COMPARATOR) {
    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.name.text = item.name
            holder.height.text = item.height
            holder.mass.text = item.mass
            holder.image.setImageResource(
                STAR_WARS_IAMGES[Random.nextInt(
                    0,
                    STAR_WARS_IAMGES.size
                )]
            )
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PeopleViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_name, parent, false)
        return PeopleViewHolder(view)
    }

    class PeopleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.itemImage)
        val name = itemView.findViewById<TextView>(R.id.name)
        val mass = itemView.findViewById<TextView>(R.id.mass)
        val height = itemView.findViewById<TextView>(R.id.height)
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