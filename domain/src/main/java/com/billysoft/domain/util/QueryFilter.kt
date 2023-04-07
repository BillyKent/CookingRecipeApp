package com.billysoft.domain.util

sealed class QueryFilter {

    class ByKeyword(val keyword: String) : QueryFilter()

    object NoFilter : QueryFilter()

}