package com.example.xj668yvm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.xj668yvm.R
import com.example.xj668yvm.activities.ui.main.DetailFragment
import com.example.xj668yvm.activities.util.NoneActionBarActivity

class DetailActivity : NoneActionBarActivity() {
    private var characterId:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        characterId = intent.getIntExtra("id",0)
        val bundle = Bundle()
        bundle.putInt("id", characterId)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DetailFragment.newInstance(bundle))
                .commitNow()
        }
    }
}