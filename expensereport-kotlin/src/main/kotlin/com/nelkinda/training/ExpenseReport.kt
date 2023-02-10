package com.nelkinda.training

import com.nelkinda.training.ExpenseType.BREAKFAST
import com.nelkinda.training.ExpenseType.DINNER
import java.util.*


class ExpenseReport(
    val printer: (String) -> Any = { s: String -> println(s) },
    val currentDateProvider: () -> Date = { Date() }
) {
    fun printReport(expenses: List<Expense>) {
        printer("Expenses ${currentDateProvider()}")

        expenses.forEach {
            printer(it.type.nameToReport + "\t" + it.amount + "\t" + expensesMarker(it))
        }

        val (mealList, nonMealList) = expenses.partition { it.type == DINNER || it.type == BREAKFAST }
        val mealExpenses = mealList.sumOf { it.amount }
        printer("Meal expenses: $mealExpenses")
        val total = mealExpenses + nonMealList.sumOf { it.amount }
        printer("Total expenses: $total")
    }

    private fun expensesMarker(expense: Expense) = if (expense.isExpensive()) "X" else " "

}
