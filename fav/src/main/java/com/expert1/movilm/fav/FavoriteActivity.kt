package com.expert1.movilm.fav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.expert1.movilm.fav.databinding.ActivityFavoriteBinding
import com.expert1.movilm.fav.di.mapsModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityFavoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(activityFavoriteBinding.root)

        val sectionsPagerAdapter = SectionsPagerAdapterFav(this, supportFragmentManager)
        activityFavoriteBinding.viewPagerFav.adapter = sectionsPagerAdapter
        activityFavoriteBinding.tabsFav.setupWithViewPager(activityFavoriteBinding.viewPagerFav)

        loadKoinModules(mapsModule)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "My Favorite"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(mapsModule)
    }
}