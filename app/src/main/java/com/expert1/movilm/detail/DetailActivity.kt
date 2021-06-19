package com.expert1.movilm.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.expert1.movilm.BuildConfig
import com.expert1.movilm.R
import com.expert1.core.domain.model.Movie
import com.expert1.core.domain.model.TvShow
import com.expert1.movilm.databinding.ActivityDetailBinding
import com.expert1.movilm.databinding.ContentDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ContentDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailBinding = activityDetailBinding.detailContent

        setContentView(activityDetailBinding.root)

        setSupportActionBar(activityDetailBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailBinding.progressBar.visibility = View.VISIBLE

        val idextra = intent.getIntExtra(EXTRA_ID, 0)
        val typextra = intent.getStringExtra(EXTRA_DETAIL)

        if (typextra == "MOVIE") {
            viewModel.getMovieDetail(idextra).observe(this, { movie ->
                if (movie != null){
                    movie.data?.let { showData(it, null) }
                }
            })
        } else {
            viewModel.getTvDetail(idextra).observe(this, { tv ->
                if (tv != null){
                    tv.data?.let { showData(null, it) }
                }
            })
        }

        detailBinding.imgRate.setOnClickListener {
            Toast.makeText(this, "You like this.", Toast.LENGTH_SHORT).show()
        }
        detailBinding.imgShare.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, "Watch \"${detailBinding.txtTitle.text}.\"")
            intent.type = "text/plain"
            startActivity(intent)
        }
    }

    private fun showData(movie: Movie?, tv: TvShow?) {
        detailBinding.progressBar.visibility = View.INVISIBLE
        val title = movie?.title ?: tv?.title
        val date = movie?.date ?: tv?.date
        val score = movie?.score ?: tv?.score
        val overview = movie?.overview ?: tv?.overview
        val poster = movie?.poster ?: tv?.poster
        val state = movie?.isFavorite ?: tv?.isFavorite

        supportActionBar?.title = title

        setFavState(state)

        detailBinding.apply {
            txtTitle.text = title
            txtDate.text = resources.getString(R.string.date_release, date)
            ratingBar.rating = (score?.toFloat() as Float) /2
            txtScore.text = resources.getString(R.string.score, score)
            txtOverview.text = overview
            imgAdd.setOnClickListener{
                setFav(movie, tv)
            }
        }
        Glide.with(this)
            .load(BuildConfig.BASE_IMG + poster)
            .centerCrop()
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
            .into(detailBinding.imgPoster)

        Glide.with(this)
            .load(BuildConfig.BASE_IMG + poster)
            .apply(RequestOptions.bitmapTransform(jp.wasabeef.glide.transformations.BlurTransformation(10, 3)))
            .into(detailBinding.imgBgPoster)

    }

    private fun setFav(movie: Movie?, tv: TvShow?) {
        if (movie != null) {
            if (movie.isFavorite){
                Toast.makeText(this, R.string.add_unfav, Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this, R.string.add_fav, Toast.LENGTH_SHORT).show()
            }
            viewModel.setFavMovie(movie, movie.isFavorite)
        } else{
            if (tv != null) {
                if(tv.isFavorite){
                    Toast.makeText(this, R.string.add_unfav, Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this, R.string.add_fav, Toast.LENGTH_SHORT).show()
                }
                viewModel.setFavTV(tv, tv.isFavorite)
            }
        }
    }

    private fun setFavState(state: Boolean?) {
        if (state == true){
            detailBinding.imgAdd.setImageResource(R.drawable.ic_favorite_red)
        } else{
            detailBinding.imgAdd.setImageResource(R.drawable.ic_favorite)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_DETAIL = "extra_detail"
    }
}