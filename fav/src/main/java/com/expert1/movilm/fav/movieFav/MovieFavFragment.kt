package com.expert1.movilm.fav.movieFav

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
import com.expert1.core.domain.model.Movie
import com.expert1.core.ui.MovieAdapter
import com.expert1.movilm.detail.DetailActivity
import com.expert1.movilm.fav.FavoriteViewModel
import com.expert1.movilm.fav.databinding.FragmentMovieFavBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFavFragment : Fragment() {

    private var _binding: FragmentMovieFavBinding? = null
    private val binding get() = _binding as FragmentMovieFavBinding
    private lateinit var adapterMovie: MovieAdapter
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieFavBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding.rvFavMovies)

        adapterMovie = MovieAdapter()

        setData()
    }

    private fun setData() {
        viewModel.getFavMovie().observe(viewLifecycleOwner, { favMovie ->
            adapterMovie.setdata(favMovie)
            adapterMovie.notifyDataSetChanged()
        })

        binding.rvFavMovies.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = adapterMovie
        }

        adapterMovie.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Movie) {
                val moveActivity = Intent(activity, DetailActivity::class.java)
                moveActivity.putExtra(DetailActivity.EXTRA_DETAIL, "MOVIE")
                moveActivity.putExtra(DetailActivity.EXTRA_ID, data.id)
                startActivity(moveActivity)
            }
        })
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
                makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movieEntity = adapterMovie.getSwipedData(swipedPosition)
                movieEntity.let { viewModel.setFavMovie(movieEntity, it.isFavorite) }
                val snackbar = Snackbar.make(view as View, R.string.add_unfav, Snackbar.LENGTH_LONG)
                snackbar.show()
            }
        }
    })

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}