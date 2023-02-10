package com.nelkinda.training

enum class ExpenseType(val nameToReport: String, val expenseLimit: Int) {
    DINNER("Dinner", 5000),
    BREAKFAST("Breakfast", 1000),
    CAR_RENTAL("Car Rental", Int.MAX_VALUE)
}