package com.wealthguardian.app.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.wealthguardian.app.R
import com.wealthguardian.app.WealthGuardianApp
import com.wealthguardian.app.databinding.ActivityOnboardingBinding
import com.wealthguardian.app.ui.MainActivity

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    private val pages = listOf(
        Triple("Unified Financial View",
            "See all your banks, loans, investments, and insurance in one secure dashboard.",
            R.drawable.ic_onboard_dashboard),
        Triple("Consent-First and Secure",
            "Your data flows only with your explicit consent via India Account Aggregator framework.",
            R.drawable.ic_onboard_consent),
        Triple("Family Financial Continuity",
            "Grant role-based access to family members so financial knowledge is never lost.",
            R.drawable.ic_onboard_family),
        Triple("Verified Document Vault",
            "Store and access verified policy, loan, and identity documents powered by DigiLocker.",
            R.drawable.ic_onboard_vault))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = OnboardingAdapter(pages)

        // Use TabLayoutMediator for ViewPager2 dots indicator
        TabLayoutMediator(binding.dotsIndicator, binding.viewPager) { _, _ -> }.attach()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(p: Int) { updateButtons(p) }
        })

        binding.btnNext.setOnClickListener {
            val c = binding.viewPager.currentItem
            if (c < pages.size - 1) binding.viewPager.currentItem = c + 1
        }

        val go = {
            WealthGuardianApp.setFirstLaunchDone()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.btnGetStarted.setOnClickListener { go() }
        binding.tvSkip.setOnClickListener { go() }

        updateButtons(0)
    }

    private fun updateButtons(pos: Int) {
        val last = pos == pages.size - 1
        binding.btnNext.visibility        = if (last) View.GONE    else View.VISIBLE
        binding.btnGetStarted.visibility  = if (last) View.VISIBLE else View.GONE
        binding.tvSkip.visibility         = if (last) View.GONE    else View.VISIBLE
    }
}
