package com.savit.core.mapper

interface Mapper<in T, out R> {
    fun map(input: T): R
    fun mapList(input: List<T>): List<R> {
        return input.map { map(it) }
    }
}
