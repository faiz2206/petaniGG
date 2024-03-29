package com.example.petani


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.petani.model.Petani


class PetaniListAdapter(
    private val onItemClickListener: (Petani) -> Unit,
): ListAdapter<Petani, PetaniListAdapter.CourseViewHolder>(WORDS_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        return CourseViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
        holder.itemView.setOnClickListener {
            onItemClickListener(current)
        }
    }


    class CourseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val courseTitle = itemView.findViewById<android.widget.TextView>(R.id.titleTextView)
        private val courseCategory = itemView.findViewById<android.widget.TextView>(R.id.categoryTextView)
        private val courseContent = itemView.findViewById<android.widget.TextView>(R.id.contentTextView)
        fun bind(current: Petani?) {
            courseTitle.text = current?.title
            courseCategory.text = current?.category
            courseContent.text = current?.content
        }

        companion object {
            fun create(parent: ViewGroup): PetaniListAdapter.CourseViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_petani, parent, false)
                return CourseViewHolder(view)
            }
        }

    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Petani>() {
            override fun areItemsTheSame(oldItem: Petani, newItem: Petani): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Petani, newItem: Petani): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}