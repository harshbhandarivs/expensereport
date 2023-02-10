package com.nelkinda.training

import com.nelkinda.training.ExpenseType.*

enum class ExpenseType(val nameToReport: String) {
    DINNER("Dinner"), BREAKFAST("Breakfast"), CAR_RENTAL("Car Rental")
}

data class Expense(val type: ExpenseType, val amount: Int = 0) {
    fun isMealTooExpensive(): Boolean {
        return when {
            type == BREAKFAST && amount > 1000 -> return true
            type == DINNER && amount > 5000 -> return true
            else -> false
        }
    }
}
