package com.expert1.movilm.home.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.expert1.core.domain.model.Movie
import com.expert1.core.ui.MovieAdapter
import com.expert1.movilm.databinding.FragmentMovieBinding
import com.expert1.movilm.detail.DetailActivity
import com.expert1.movilm.home.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private lateinit var adapter2: MovieAdapter
    private val movieViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter2 = MovieAdapter()
        adapter2.notifyDataSetChanged()
        handleData()

        fragmentMovieBinding.progressBar.visibility = View.VISIBLE

        movieViewModel.getMovies().observe(viewLifecycleOwner, { movie ->
            fragmentMovieBinding.progressBar.visibility = View.GONE
            adapter2.setdata(movie.data)
            adapter2.notifyDataSetChanged()
        })
    }

    private fun handleData(){
        fragmentMovieBinding.progressBar.visibility = View.INVISIBLE

        fragmentMovieBinding.rvMovies.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = adapter2
        }

        adapter2.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Movie) {
                val moveActivity = Intent(activity, DetailActivity::class.java)
                moveActivity.putExtra(DetailActivity.EXTRA_DETAIL, "MOVIE")
                moveActivity.putExtra(DetailActivity.EXTRA_ID, data.id)
                startActivity(moveActivity)
            }
        })
    }
}