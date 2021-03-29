package com.savit.core.mapper

interface BiMapper<T, R> : Mapper<T, R> {

    fun reverse(output: R): T
    fun reverseList(output: List<R>): List<T> {
        return output.map { reverse(it) }
    }
}
