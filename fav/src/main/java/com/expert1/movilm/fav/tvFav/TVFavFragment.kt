package com.expert1.movilm.fav.tvFav

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.expert1.movilm.R
import com.expert1.core.domain.model.TvShow
import com.expert1.core.ui.TVShowAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import com.expert1.movilm.detail.DetailActivity
import com.expert1.movilm.fav.FavoriteViewModel
import com.expert1.movilm.fav.databinding.FragmentTVFavBinding
import com.google.android.material.snackbar.Snackbar

class TVFavFragment : Fragment() {

    private lateinit var binding: FragmentTVFavBinding
    private lateinit var adapterTv: TVShowAdapter
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentTVFavBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding.rvFavTv)

        adapterTv = TVShowAdapter()

        showData()
    }

    private fun showData() {
        viewModel.getFavTV().observe(viewLifecycleOwner, { tvFav ->
            adapterTv.setData(tvFav)
            adapterTv.notifyDataSetChanged()

            binding.rvFavTv.apply {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = adapterTv
            }

            adapterTv.setOnItemClickCallback(object : TVShowAdapter.OnItemClickCallback {
                override fun onItemClicked(data: TvShow) {
                    val moveActivity = Intent(activity, DetailActivity::class.java)
                    moveActivity.putExtra(DetailActivity.EXTRA_DETAIL, "TVShow")
                    moveActivity.putExtra(DetailActivity.EXTRA_ID, data.id)
                    startActivity(moveActivity)
                }
            })
        })
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
                makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val tvEntity = adapterTv.getSwipedData(swipedPosition)
                tvEntity.let { viewModel.setFavTV(tvEntity, it.isFavorite) }
                val snackbar = Snackbar.make(view as View, R.string.add_unfav, Snackbar.LENGTH_LONG)
                snackbar.show()
            }
        }
    })
}