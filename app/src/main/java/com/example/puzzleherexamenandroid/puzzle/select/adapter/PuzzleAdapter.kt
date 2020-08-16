package com.example.puzzleherexamenandroid.puzzle.select.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.puzzleherexamenandroid.databinding.ListItemPuzzleBinding
import com.example.puzzleherexamenandroid.model.Puzzle

class PuzzleAdapter(private val onclickListener: OnClickListener): RecyclerView.Adapter<PuzzleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
    var data = listOf<Puzzle>()
        set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item,onclickListener)
    }

    class OnClickListener(val clickListener: (puzzle: Puzzle) ->Unit){
        fun onClick(puzzle:Puzzle) = clickListener(puzzle)
    }
    class ViewHolder private constructor(val binding: ListItemPuzzleBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Puzzle, clickListener:OnClickListener ) {
            binding.puzzle = item
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPuzzleBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}