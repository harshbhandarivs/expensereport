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
        var total = 0
        var mealExpenses = 0

        printer("Expenses ${currentDateProvider()}")

        for (expense in expenses) {
            if (expense.type == ExpenseType.DINNER || expense.type == ExpenseType.BREAKFAST) {
                mealExpenses += expense.amount
            }

            val mealOverExpensesMarker = if (expense.isMealTooExpensive()) "X" else " "

            printer(expense.type.nameToReport + "\t" + expense.amount + "\t" + mealOverExpensesMarker)

            total += expense.amount
        }

        printer("Meal expenses: $mealExpenses")
        printer("Total expenses: $total")
    }

}
