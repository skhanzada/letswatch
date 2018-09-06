package com.sk.android.letswatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sk.android.letswatch.movies.MoviesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.contentFrame, MoviesFragment())
            .commit()
    }
}
