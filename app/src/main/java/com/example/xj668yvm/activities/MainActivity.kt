package com.example.xj668yvm.activities

import android.os.Bundle
import com.example.xj668yvm.R
import com.example.xj668yvm.activities.ui.main.MainFragment
import com.example.xj668yvm.activities.util.NoneActionBarActivity

class MainActivity : NoneActionBarActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}