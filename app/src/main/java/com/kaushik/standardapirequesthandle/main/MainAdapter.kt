package com.kaushik.standardapirequesthandle.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kaushik.standardapirequesthandle.databinding.PostItemBinding
import com.kaushik.standardapirequesthandle.retrofit.models.Posts

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainViewModel>() {
    private val postList = Posts()
    private lateinit var binding:PostItemBinding

    inner class MainViewModel(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewModel {
        binding = PostItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainViewModel(binding.root)

    }

    override fun onBindViewHolder(holder: MainViewModel, position: Int) {
        binding.postItem.text = postList[position].body
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    fun updateData(posts: Posts){
        postList.clear()
        postList.addAll(posts)
        notifyDataSetChanged()
    }
}