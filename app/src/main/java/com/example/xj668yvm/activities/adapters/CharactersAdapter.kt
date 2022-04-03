package com.example.xj668yvm.activities.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.xj668yvm.activities.DetailActivity
import com.example.xj668yvm.activities.models.Characters
import com.example.xj668yvm.databinding.CharactersItemBinding


class CharactersAdapter(private var context: Context) :
    PagingDataAdapter<Characters, CharactersAdapter.CharactersViewHolder>(CharactersComparator) {
    var characterClickListener: CharacterClickListener? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharactersViewHolder {
        return CharactersViewHolder(
            CharactersItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindCharacters(it) }
        holder.itemView.setOnClickListener {
            val intent=Intent(context,DetailActivity::class.java)
            intent.putExtra("id",item!!.id)
            context.startActivity(intent)
        }
    }

    inner class CharactersViewHolder(private val binding: CharactersItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindCharacters(item: Characters) = with(binding) {
            Glide.with(context).load(item.image).into(characterImage)
            itemName.text = item.name
        }

    }

    object CharactersComparator : DiffUtil.ItemCallback<Characters>() {
        override fun areItemsTheSame(oldItem: Characters, newItem: Characters): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean {
            return oldItem == newItem
        }
    }
    interface CharacterClickListener {
        fun onCharacterClicked(id:Int)
    }
}