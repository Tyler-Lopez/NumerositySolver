package com.company.numerositycheat

fun calculate(arr: List<Int>, target: Int, operand: Operand): List<Int> {
    val returnList: MutableList<Int> = mutableListOf()
    // Recursive, brute-force function returning null
    // If a pair is found, returnList, which is a MutableList is populated with values
    calculate(arr as List<Double>, target.toDouble(), returnList, operand)
    return returnList
}

private fun calculate(remaining: List<Double>, target: Double, output: MutableList<Int>, operand: Operand) {
    // Begin an exhaustive brute-force trace of each possible value
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
    // Base-cases for searches which would be ineffective to save time
    when {
        output.isNotEmpty() -> return // If there is already an output found, return.
        (operand == Operand.Addition || operand == Operand.Multiplication) -> if (sum > target) return
        (operand == Operand.Subtraction || operand == Operand.Division) -> if (sum < target) return
    }
    // Base case for the solution
    // 1. Check to see sum has exacltly reached the target
    // 2. Check to ensure the trace is more than just 1 value
    // 3. Check to ensure that any division has not produced a number that would round down to the target (This might not be necessary?)
    if (sum == target && trace.size > 1 && sum % 1.0 == 0.0) {
            for (element in trace) {
                 output.add(element.toInt())
            }
    }
    // For each one of the remaining elements in this trace
    for (i in 0..remaining.lastIndex) {
        val intAt = remaining[i]
        val newRemaining = ArrayList(remaining)
        val newTrace = mutableListOf<Double>()
        for (element in trace)
            newTrace.add(element) // Copy the previous trace to this new trace and add the new value to it
        newTrace.add(intAt)
        newRemaining.removeAt(i) // Remove the extracted value from the new remaining values to iterate through
        // Calculate the new sum provided the operand
        val newSum =
            when (operand) {
                Operand.Addition -> sum + intAt
                Operand.Subtraction -> sum - intAt
                Operand.Multiplication -> sum * intAt
                Operand.Division -> sum / intAt
            }
        // Recursive call
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
