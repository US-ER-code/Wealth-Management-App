package com.wealthguardian.app.utils
import kotlin.math.abs

object CurrencyFormatter {
    fun format(amount: Double): String {
        val a = abs(amount)
        val p = if (amount < 0) "-Rs." else "Rs."
        return when {
            a >= 10_000_000 -> "$p${"%.2f".format(a / 10_000_000)}Cr"
            a >= 100_000    -> "$p${"%.1f".format(a / 100_000)}L"
            a >= 1_000      -> "$p${"%.1f".format(a / 1_000)}K"
            else            -> "$p${a.toLong()}"
        }
    }
}
