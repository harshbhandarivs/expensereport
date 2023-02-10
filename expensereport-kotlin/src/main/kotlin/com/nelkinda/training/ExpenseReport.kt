package com.nelkinda.training

import java.util.*

enum class ExpenseType(val nameToReport: String) {
    DINNER("Dinner"), BREAKFAST("Breakfast"), CAR_RENTAL("Car Rental")
}

data class Expense(val type: ExpenseType, val amount: Int = 0) {
    fun isMealTooExpensive(): Boolean {
        return when {
            type == ExpenseType.BREAKFAST && amount > 1000 -> return true
            type == ExpenseType.DINNER && amount > 5000 -> return true
            else -> false
        }
    }
}

class ExpenseReport(
    val printer: (String) -> Any = { s: String -> println(s) },
    val currentDateProvider: () -> Date = { Date() }
) {
    fun printReport(expenses: List<Expense>) {
        printer("Expenses ${currentDateProvider()}")

        expenses.forEach {
            printer(it.type.nameToReport + "\t" + it.amount + "\t" + mealOverExpensesMarker(it))
        }

        val parts = expenses.partition { it.type == ExpenseType.DINNER || it.type == ExpenseType.BREAKFAST }
        val mealExpenses = parts.first.sumOf { it.amount }
        printer("Meal expenses: $mealExpenses")
        val total = mealExpenses + parts.second.sumOf { it.amount }
        printer("Total expenses: $total")
    }

    private fun mealOverExpensesMarker(expense: Expense) = if (expense.isMealTooExpensive()) "X" else " "

}
