package com.sk.android.letswatch

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.MenuItem
import com.sk.android.letswatch.movies.MovieDetailFragment
import com.sk.android.letswatch.movies.MoviesFragment
import com.sk.android.letswatch.movies.MoviesViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesViewModel =
                ViewModelProviders.of(this, viewModelFactory).get(MoviesViewModel::class.java)
        moviesViewModel.movieDetail.observe(this, Observer {
            addFragment(MovieDetailFragment.newInstance(it), "detail")
        })

        replaceFragment(MoviesFragment())
    }

    fun replaceFragment(fragment: Fragment, addToBackstack: String? = null) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        addToBackstack?.let {
            fragmentTransaction.addToBackStack(addToBackstack)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        fragmentTransaction.replace(R.id.contentFrame, fragment).commit()
    }

    fun addFragment(fragment: Fragment, addToBackstack: String? = null) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        addToBackstack?.let {
            fragmentTransaction.addToBackStack(addToBackstack)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        fragmentTransaction.add(R.id.contentFrame, fragment).commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount == 0) {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

}
