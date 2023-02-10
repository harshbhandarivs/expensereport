package com.nelkinda.training

import com.nelkinda.training.ExpenseType.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class ExpenseReportTest {
    @Test
    fun shouldPrintThings() {
        val expenses = listOf(
            Expense(DINNER, 500),
            Expense(DINNER, 6000),
            Expense(BREAKFAST, 200),
            Expense(BREAKFAST, 2000),
            Expense(CAR_RENTAL, 300),
        )
        val testDate = Date()
        val expectedOutputLines = listOf(
            "Expenses $testDate",
            "Dinner\t500\t ",
            "Dinner\t6000\tX",
            "Breakfast\t200\t ",
            "Breakfast\t2000\tX",
            "Car Rental\t300\t ",
            "Meal expenses: 8700",
            "Total expenses: 9000",
        )
        val outputLines = mutableListOf<String>()
        val testPrinter = {s: String -> outputLines.add(s) }

        ExpenseReport(testPrinter) { testDate }.printReport(expenses)

        assertEquals(expectedOutputLines, outputLines)
    }
}