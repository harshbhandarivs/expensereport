package com.nelkinda.training

import com.nelkinda.training.ExpenseType.BREAKFAST
import com.nelkinda.training.ExpenseType.DINNER
import java.util.*

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

class ExpenseReport(
    val printer: (String) -> Any = { s: String -> println(s) },
    val currentDateProvider: () -> Date = { Date() }
) {
    fun printReport(expenses: List<Expense>) {
        printer("Expenses ${currentDateProvider()}")

        expenses.forEach {
            printer(it.type.nameToReport + "\t" + it.amount + "\t" + mealOverExpensesMarker(it))
        }

        val (mealList, nonMealList) = expenses.partition { it.type == DINNER || it.type == BREAKFAST }
        val mealExpenses = mealList.sumOf { it.amount }
        printer("Meal expenses: $mealExpenses")
        val total = mealExpenses + nonMealList.sumOf { it.amount }
        printer("Total expenses: $total")
    }

    private fun mealOverExpensesMarker(expense: Expense) = if (expense.isMealTooExpensive()) "X" else " "

}
