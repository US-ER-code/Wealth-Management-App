package com.wealthguardian.app.ui.onboarding
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.wealthguardian.app.WealthGuardianApp
import com.wealthguardian.app.databinding.ActivitySplashBinding
import com.wealthguardian.app.ui.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,
                if (WealthGuardianApp.isFirstLaunch()) OnboardingActivity::class.java
                else MainActivity::class.java))
            finish()
        }, 1800)
    }
}
