package com.company.numerositycheat

fun calculate(arr: List<Int>, target: Int, operand: Operand): String {
    val returnList: MutableList<Int> = mutableListOf()
    calculate(arr, target, returnList, operand)
    val stringBuilder = StringBuilder()
    for (element in returnList) {
        stringBuilder.append(" $element ")
    }
    return stringBuilder.toString()
}

private fun calculate(remaining: List<Int>, target: Int, output: MutableList<Int>, operand: Operand) {
    // If remaining is not inserted, just copy from the mutable list

    // For each one of the remaining elements in this trace
    for (i in 0 until remaining.lastIndex) {
        val intAt = remaining[i]
        val newRemaining = ArrayList(remaining)
        val trace = listOf(intAt)
        newRemaining.removeAt(i)
        calculateRecursive(newRemaining, trace, target, intAt, output, operand)
    }
}

private fun calculateRecursive(
    remaining: ArrayList<Int>,
    trace: List<Int>,
    target: Int,
    sum: Int,
    output: MutableList<Int>,
    operand: Operand,
    ) {
    // Base-cases for searches which would be ineffective
    when {
        (operand == Operand.Addition || operand == Operand.Multiplication) -> if (sum > target) return
        (operand == Operand.Subtraction || operand == Operand.Division) -> if (sum < target) return
    }
    // Base case
    if (sum == target) {
        if (output.isEmpty()) {
            for (element in trace) {
                output.add(element)
            }
        }
    }
    // For each one of the remaining elements in this trace
    for (i in 0 until remaining.lastIndex) {
        val intAt = remaining[i]
        val newRemaining = ArrayList(remaining)
        val newTrace = mutableListOf<Int>()
        for (element in trace)
            newTrace.add(element)
        newTrace.add(intAt)
        newRemaining.removeAt(i)
        // New sum
        val newSum =
            when (operand) {
                Operand.Addition -> sum + intAt
                Operand.Subtraction -> sum - intAt
                Operand.Multiplication -> sum * intAt
                Operand.Division -> sum / intAt
            }
        calculateRecursive(
            newRemaining,
            newTrace,
            target,
            newSum,
            output,
            operand
        )
    }
}
