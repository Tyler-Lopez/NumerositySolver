package com.company.numerositycheat

fun calculateAddition(arr: List<Int>, target: Int): String {
    val returnList: MutableList<Int> = mutableListOf()
    calculateAddition(arr, target, returnList)
    val stringBuilder = StringBuilder()
    for (element in returnList) {
        stringBuilder.append(" $element ")
    }
    return stringBuilder.toString()
}

fun calculateAddition(remaining: List<Int>, target: Int, output: MutableList<Int>) {
    // If remaining is not inserted, just copy from the mutable list

    // For each one of the remaining elements in this trace
    for (i in 0 until remaining.lastIndex) {
        val intAt = remaining[i]
        val newRemaining = ArrayList(remaining)
        val trace = listOf(intAt)
        newRemaining.removeAt(i)
        calculateAdditionRecursive(newRemaining, trace, target, intAt, output)
    }
}

private fun calculateAdditionRecursive(
    remaining: ArrayList<Int>,
    trace: List<Int>,
    target: Int,
    sum: Int,
    output: MutableList<Int>
) {
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
        calculateAdditionRecursive(
            newRemaining,
            newTrace,
            target,
            sum + intAt,
            output
        )
    }
}
