package com.example.xj668yvm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.xj668yvm.activities.util.NoneActionBarActivity
import com.example.xj668yvm.databinding.ActivitySplashBinding

class SplashActivity : NoneActionBarActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val splashDuration: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            splashImage.alpha = 0f
            splashImage.animate().setDuration(splashDuration).alpha(1f).withEndAction {
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                goMainPage()
                finish()
            }
        }
    }
    private fun goMainPage() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
    }
}