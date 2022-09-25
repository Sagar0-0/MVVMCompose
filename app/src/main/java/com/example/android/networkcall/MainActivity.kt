package com.example.android.networkcall

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.networkcall.fragments.RecipeListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, RecipeListFragment())
            .commit()
    }
}