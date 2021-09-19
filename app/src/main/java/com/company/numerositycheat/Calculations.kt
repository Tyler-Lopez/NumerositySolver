package com.company.numerositycheat

fun calculate(arr: List<Int>, target: Int, operand: Operand): List<Int> {
    val returnList: MutableList<Int> = mutableListOf()
    calculate(arr as List<Double>, target.toDouble(), returnList, operand)
    val stringBuilder = StringBuilder()
    for (element in returnList) {
        stringBuilder.append(" $element ")
    }
    if(returnList.isEmpty()) stringBuilder.append("N/A")
    return returnList
}

private fun calculate(remaining: List<Double>, target: Double, output: MutableList<Int>, operand: Operand) {
    // If remaining is not inserted, just copy from the mutable list

    // For each one of the remaining elements in this trace
    for (i in 0..remaining.lastIndex) {
        val intAt = remaining[i]
        val newRemaining = ArrayList(remaining)
        val trace = listOf(intAt)
        newRemaining.removeAt(i)
        calculateRecursive(newRemaining, trace, target, intAt, output, operand)
    }
}

private fun calculateRecursive(
    remaining: ArrayList<Double>,
    trace: List<Double>,
    target: Double,
    sum: Double,
    output: MutableList<Int>,
    operand: Operand,
    ) {
    // Base-cases for searches which would be ineffective
    when {
        (operand == Operand.Addition || operand == Operand.Multiplication) -> if (sum > target) return
        (operand == Operand.Subtraction || operand == Operand.Division) -> if (sum < target) return
    }
    // Base case
    if (sum == target && trace.size > 1) {
        if (output.isEmpty()) {
            for (element in trace) {
                if (element % 1.0 == 0.0) output.add(element.toInt()) // Ensure math works for some division
            }
        }
    }
    // For each one of the remaining elements in this trace
    for (i in 0..remaining.lastIndex) {
        val intAt = remaining[i]
        val newRemaining = ArrayList(remaining)
        val newTrace = mutableListOf<Double>()
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
