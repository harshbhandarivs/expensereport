package com.nelkinda.training

import java.util.*

enum class ExpenseType {
    DINNER, BREAKFAST, CAR_RENTAL
}

data class Expense(val type: ExpenseType, val amount: Int = 0)

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

            val expenseName = when (expense.type) {
                ExpenseType.DINNER -> "Dinner"
                ExpenseType.BREAKFAST -> "Breakfast"
                ExpenseType.CAR_RENTAL -> "Car Rental"
            }

            val mealOverExpensesMarker =
                if (expense.type == ExpenseType.DINNER && expense.amount > 5000 || expense.type == ExpenseType.BREAKFAST && expense.amount > 1000) "X" else " "

            printer(expenseName + "\t" + expense.amount + "\t" + mealOverExpensesMarker)

            total += expense.amount
        }

        printer("Meal expenses: $mealExpenses")
        printer("Total expenses: $total")
    }
}
