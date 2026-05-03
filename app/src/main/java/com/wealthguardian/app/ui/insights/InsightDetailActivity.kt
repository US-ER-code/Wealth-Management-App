package com.wealthguardian.app.ui.insights

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.components.XAxis
import com.wealthguardian.app.R
import com.wealthguardian.app.data.model.Insight
import com.wealthguardian.app.data.model.InsightType
import com.wealthguardian.app.utils.CurrencyFormatter

class InsightDetailActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_TITLE       = "title"
        private const val EXTRA_DESCRIPTION = "description"
        private const val EXTRA_TYPE        = "type"
        private const val EXTRA_DETAIL_BODY = "detail_body"
        private const val EXTRA_ACTION_LBL  = "action_label"
        private const val EXTRA_AMOUNT      = "amount"
        private const val EXTRA_INSTITUTION = "institution"
        private const val EXTRA_CATEGORY    = "category"

        fun launch(context: Context, insight: Insight) {
            context.startActivity(Intent(context, InsightDetailActivity::class.java).apply {
                putExtra(EXTRA_TITLE,       insight.title)
                putExtra(EXTRA_DESCRIPTION, insight.description)
                putExtra(EXTRA_TYPE,        insight.type.name)
                putExtra(EXTRA_DETAIL_BODY, insight.detailBody)
                putExtra(EXTRA_ACTION_LBL,  insight.actionLabel)
                putExtra(EXTRA_AMOUNT,      insight.amount)
                putExtra(EXTRA_INSTITUTION, insight.institution)
                putExtra(EXTRA_CATEGORY,    insight.category)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Build UI programmatically (no extra layout file needed)
        val scroll = ScrollView(this)
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 48, 48, 48)
        }
        scroll.addView(root)
        setContentView(scroll)

        val title       = intent.getStringExtra(EXTRA_TITLE) ?: ""
        val description = intent.getStringExtra(EXTRA_DESCRIPTION) ?: ""
        val typeStr     = intent.getStringExtra(EXTRA_TYPE) ?: "INFO"
        val detailBody  = intent.getStringExtra(EXTRA_DETAIL_BODY) ?: ""
        val actionLabel = intent.getStringExtra(EXTRA_ACTION_LBL) ?: "View"
        val amount      = intent.getDoubleExtra(EXTRA_AMOUNT, 0.0)
        val institution = intent.getStringExtra(EXTRA_INSTITUTION) ?: ""
        val category    = intent.getStringExtra(EXTRA_CATEGORY) ?: ""
        val type        = runCatching { InsightType.valueOf(typeStr) }.getOrDefault(InsightType.INFO)

        supportActionBar?.apply {
            this.title = "Insight Detail"
            setDisplayHomeAsUpEnabled(true)
        }

        // ── Type badge ──────────────────────────────────────
        val (badgeText, badgeColor, iconTxt) = when (type) {
            InsightType.ALERT   -> Triple("⚠️ Alert",   R.color.error_red,     "🔴")
            InsightType.WARNING -> Triple("⚡ Warning", R.color.warning_amber, "🟡")
            InsightType.INFO    -> Triple("ℹ️ Info",    R.color.teal_primary,  "🔵")
            InsightType.SUCCESS -> Triple("✅ Good",    R.color.success_green, "🟢")
        }

        val tvBadge = TextView(this).apply {
            text = badgeText; textSize = 13f
            setTextColor(ContextCompat.getColor(this@InsightDetailActivity, badgeColor))
            setPadding(0, 0, 0, 8)
        }
        val tvTitle = TextView(this).apply {
            text = title; textSize = 22f
            setTextColor(ContextCompat.getColor(this@InsightDetailActivity, R.color.text_primary))
            setPadding(0, 0, 0, 12)
        }
        val tvDesc = TextView(this).apply {
            text = description; textSize = 15f
            setTextColor(ContextCompat.getColor(this@InsightDetailActivity, R.color.text_secondary))
            setPadding(0, 0, 0, 24)
        }
        root.addView(tvBadge); root.addView(tvTitle); root.addView(tvDesc)

        // ── Amount card (if present) ────────────────────────
        if (amount > 0) {
            val card = androidx.cardview.widget.CardView(this).apply {
                radius = 16f; cardElevation = 4f
                setContentPadding(32, 32, 32, 32)
            }
            val amountLayout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL; setPadding(32, 32, 32, 32)
            }
            val tvAmtLabel = TextView(this).apply { text = "Amount"; textSize = 13f }
            val tvAmt = TextView(this).apply {
                text = CurrencyFormatter.format(amount); textSize = 28f
                setTextColor(ContextCompat.getColor(this@InsightDetailActivity, badgeColor))
            }
            amountLayout.addView(tvAmtLabel); amountLayout.addView(tvAmt)
            card.addView(amountLayout)
            val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            lp.bottomMargin = 24
            root.addView(card, lp)
        }

        // ── Institution tag ─────────────────────────────────
        if (institution.isNotBlank()) {
            val tvInst = TextView(this).apply {
                text = "🏦 Institution: $institution"; textSize = 14f
                setPadding(0, 0, 0, 8)
            }
            root.addView(tvInst)
        }
        if (category.isNotBlank()) {
            val tvCat = TextView(this).apply {
                text = "📂 Category: $category"; textSize = 14f
                setPadding(0, 0, 0, 24)
            }
            root.addView(tvCat)
        }

        // ── Divider ─────────────────────────────────────────
        val divider = View(this).apply {
            setBackgroundColor(0x1A000000)
        }
        root.addView(divider, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2).apply { topMargin = 8; bottomMargin = 24 })

        // ── Detail body ─────────────────────────────────────
        val bodyText = detailBody.ifBlank { buildDefaultDetailBody(type, title, amount, institution) }
        val tvBody = TextView(this).apply {
            text = bodyText; textSize = 15f; setLineSpacing(0f, 1.5f)
            setTextColor(ContextCompat.getColor(this@InsightDetailActivity, R.color.text_primary))
            setPadding(0, 0, 0, 32)
        }
        root.addView(tvBody)

        // ── Mini sparkline chart ────────────────────────────
        addMiniChart(root, type)

        // ── Action button ───────────────────────────────────
        val btnAction = com.google.android.material.button.MaterialButton(this).apply {
            text = actionLabel
            setBackgroundColor(ContextCompat.getColor(this@InsightDetailActivity, badgeColor))
            setOnClickListener {
                Toast.makeText(this@InsightDetailActivity, "Action: $actionLabel", Toast.LENGTH_SHORT).show()
            }
        }
        root.addView(btnAction, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply { topMargin = 8 })

        // ── Snooze / Dismiss ────────────────────────────────
        val btnSnooze = com.google.android.material.button.MaterialButton(this, null,
            com.google.android.material.R.attr.borderlessButtonStyle).apply {
            text = "Snooze for 7 days"
            setOnClickListener {
                Toast.makeText(this@InsightDetailActivity, "Snoozed for 7 days", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        root.addView(btnSnooze, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
    }

    private fun buildDefaultDetailBody(type: InsightType, title: String, amount: Double, institution: String): String {
        return when {
            title.contains("premium", true) || title.contains("LIC", true) ->
                "Your insurance premium is due soon. Missing a premium payment can lead to policy lapse and loss of coverage.\n\n" +
                "📋 What to do:\n• Log into your insurer's app or website\n• Set up auto-debit to avoid future misses\n• Check if your bank account has sufficient balance\n\n" +
                "💡 Tip: Set up a reminder 3 days before every premium date."

            title.contains("EMI", true) || title.contains("loan", true) ->
                "Your EMI is due. Delayed EMI payments can negatively affect your CIBIL credit score.\n\n" +
                "📋 What to do:\n• Pay the EMI before the due date\n• Check for auto-debit mandate on your bank account\n• Contact your lender if you need a grace period\n\n" +
                "💡 Tip: Set up an ECS mandate to auto-pay EMIs on time."

            title.contains("SIP", true) ->
                "A SIP instalment was missed. SIPs work best with consistent monthly investments.\n\n" +
                "📋 What to do:\n• Ensure your bank account has sufficient balance on SIP date\n• Check if the mandate is still active with your AMC\n• Consider a one-time catch-up lumpsum contribution\n\n" +
                "💡 Tip: Keep a buffer of 1-2 months' SIP amount in your bank."

            title.contains("rebalance", true) || title.contains("portfolio", true) ->
                "Your portfolio allocation has drifted from the target. Rebalancing helps maintain your desired risk profile.\n\n" +
                "📊 Current vs Target:\n• Equity: 72% (Target: 60%)\n• Debt: 18% (Target: 30%)\n• Gold: 10% (Target: 10%)\n\n" +
                "📋 What to do:\n• Sell some equity funds and move to debt\n• Or increase debt SIP for the next 3 months\n\n" +
                "💡 Tip: Rebalance annually or when allocation drifts by more than 5%."

            title.contains("AMC", true) || title.contains("fee", true) ->
                "An AMC (Asset Management Company) fee was detected. This is a recurring charge on your mutual fund.\n\n" +
                "📋 Details:\n• AMC fees are expressed as Expense Ratio (%)\n• Direct plans have lower fees than Regular plans\n• Over 20 years, a 1% lower expense ratio can grow your corpus by 20%+\n\n" +
                "💡 Tip: Review if you're invested in Direct plans. Switch via MF Central or CAMS."

            else ->
                "This insight requires your attention to maintain your financial health.\n\n" +
                "📋 Recommended action:\n• Review the highlighted account or policy\n• Take corrective action before the due date\n• Contact your financial advisor if needed\n\n" +
                "💡 WealthGuardian monitors your accounts in real-time via the AA framework."
        }
    }

    private fun addMiniChart(root: LinearLayout, type: InsightType) {
        val chart = LineChart(this).apply {
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 300).apply { bottomMargin = 24 }
            description.isEnabled = false; legend.isEnabled = false
            setTouchEnabled(false)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            axisLeft.setDrawGridLines(true); axisRight.isEnabled = false
        }

        // Generate contextual chart data
        val raw = when (type) {
            InsightType.ALERT   -> listOf(100f, 98f, 95f, 90f, 85f, 80f, 72f) // declining
            InsightType.WARNING -> listOf(50f, 55f, 60f, 70f, 75f, 85f, 95f)  // rising concern
            InsightType.INFO    -> listOf(60f, 62f, 65f, 63f, 67f, 70f, 72f)  // stable growth
            InsightType.SUCCESS -> listOf(40f, 50f, 60f, 70f, 80f, 88f, 95f)  // strong growth
        }
        val entries = raw.mapIndexed { i, v -> Entry(i.toFloat(), v) }
        val color = ContextCompat.getColor(this, when (type) {
            InsightType.ALERT   -> R.color.error_red
            InsightType.WARNING -> R.color.warning_amber
            InsightType.INFO    -> R.color.teal_primary
            InsightType.SUCCESS -> R.color.success_green
        })
        val ds = LineDataSet(entries, "Trend")
        ds.color = color
        ds.lineWidth = 2f
        ds.setDrawCircles(false)
        ds.setDrawValues(false)
        ds.mode = LineDataSet.Mode.CUBIC_BEZIER
        ds.setDrawFilled(true)
        ds.fillAlpha = 40
        ds.fillColor = color

        chart.data = LineData(ds)
        chart.invalidate()

        val tvChartLbl = TextView(this).apply {
            text = "Trend (last 7 days)"; textSize = 12f
            setTextColor(ContextCompat.getColor(this@InsightDetailActivity, R.color.text_secondary))
            setPadding(0, 0, 0, 8)
        }
        root.addView(tvChartLbl)
        root.addView(chart)
    }

    override fun onSupportNavigateUp(): Boolean { finish(); return true }
}
