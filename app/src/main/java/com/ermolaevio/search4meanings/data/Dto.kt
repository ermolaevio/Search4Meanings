package com.ermolaevio.search4meanings.data

interface Dto<out T> {
    fun convert(): T
}