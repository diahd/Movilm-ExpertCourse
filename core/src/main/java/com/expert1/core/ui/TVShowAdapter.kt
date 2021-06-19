package com.expert1.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.expert1.core.BuildConfig
import com.expert1.core.R
import com.expert1.core.databinding.ItemsMovietvBinding
import com.expert1.core.domain.model.TvShow

class TVShowAdapter: RecyclerView.Adapter<TVShowAdapter.DataViewHolder>() {

    private var listTV = ArrayList<TvShow>()
    fun setData(data: List<TvShow>?) {
        if (data == null) return
        listTV.clear()
        listTV.addAll(data)
        notifyDataSetChanged()
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun getSwipedData(swipedPosition: Int): TvShow = listTV[swipedPosition]

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemsMovietvBinding = ItemsMovietvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(itemsMovietvBinding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val data = listTV[position]
        holder.bind(data)

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(data)
        }
    }

    class DataViewHolder(private val binding: ItemsMovietvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TvShow) {
            with(binding) {
                tvItemTitle.text = data.title
                tvScore.text = data.score
                Glide.with(itemView.context)
                    .load(BuildConfig.BASE_IMG + data.poster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                    )
                    .centerInside()
                    .into(imgPoster)
            }
        }
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: TvShow)
    }
    override fun getItemCount(): Int = listTV.size

}