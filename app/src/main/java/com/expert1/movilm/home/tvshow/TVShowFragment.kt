package com.expert1.movilm.home.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.expert1.core.domain.model.TvShow
import com.expert1.core.ui.TVShowAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import com.expert1.movilm.databinding.FragmentTVShowBinding
import com.expert1.movilm.detail.DetailActivity
import com.expert1.movilm.home.HomeViewModel

class TVShowFragment : Fragment() {

    private var _fragmentTVShowBinding: FragmentTVShowBinding? = null
    private val fragmentTVShowBinding get() = _fragmentTVShowBinding as FragmentTVShowBinding
    private lateinit var adapterTv: TVShowAdapter
    private val tvViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _fragmentTVShowBinding = FragmentTVShowBinding.inflate(layoutInflater, container, false)
        return fragmentTVShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterTv = TVShowAdapter()
        adapterTv.notifyDataSetChanged()
        handleData()

        fragmentTVShowBinding.progressBar.visibility = View.VISIBLE

        tvViewModel.getTv().observe(viewLifecycleOwner, { tv->
            fragmentTVShowBinding.progressBar.visibility = View.GONE
            adapterTv.setData(tv.data)
            adapterTv.notifyDataSetChanged()
        })

        adapterTv.setOnItemClickCallback(object : TVShowAdapter.OnItemClickCallback {
            override fun onItemClicked(data: TvShow) {
                val moveActivity = Intent(activity, DetailActivity::class.java)
                moveActivity.putExtra(DetailActivity.EXTRA_DETAIL, "TVShow")
                moveActivity.putExtra(DetailActivity.EXTRA_ID, data.id)
                startActivity(moveActivity)
            }
        })
    }

    private fun handleData() {
        fragmentTVShowBinding.rvTvshows.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = adapterTv
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentTVShowBinding = null
    }
}