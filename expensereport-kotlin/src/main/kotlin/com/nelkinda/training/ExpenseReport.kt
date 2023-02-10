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

        val total = expenses.sumOf { it.amount }
        val mealExpenses = expenses
            .filter { it.type == ExpenseType.DINNER || it.type == ExpenseType.BREAKFAST }
            .sumOf { it.amount }

        expenses.forEach {
            printer(it.type.nameToReport + "\t" + it.amount + "\t" + mealOverExpensesMarker(it))
        }

        printer("Meal expenses: $mealExpenses")
        printer("Total expenses: $total")
    }

    private fun mealOverExpensesMarker(expense: Expense) = if (expense.isMealTooExpensive()) "X" else " "

}
