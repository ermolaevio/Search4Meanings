package com.ermolaevio.search4meanings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ermolaevio.search4meanings.ui.SearchWordFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainContainer, SearchWordFragment(), "SearchWordScreen")
                .commit()
        }
    }
}