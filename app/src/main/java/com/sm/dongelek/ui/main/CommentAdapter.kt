package com.sm.dongelek.ui.main


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sm.dongelek.databinding.CommentBinding

class CommentAdapter{}
/*
class CommentAdapter(private val comments: List<Comment>): RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: Comment){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = CommentBinding.inflate(inflater, parent, false)
        val view = inflate.invoke(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        ViewHolder.bind(comments[position])
    }

    override fun getItemCount(): Int {
        return comments.size
    }

}*/