package com.nelkinda.training

data class Expense(val type: ExpenseType, val amount: Int = 0) {
    fun isExpensive(): Boolean {
        return amount > type.expenseLimit
    }
}
