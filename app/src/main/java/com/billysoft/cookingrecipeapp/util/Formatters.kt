package com.billysoft.cookingrecipeapp.util

object Formatters {


    /**
     * Formats a float number with comma decimal separator.
     */
    fun formatRatingStars(value: Float) = String.format("%.1f", value).replace('.', ',')


    /**
     * Formats a given amount of minutes to a string indicating the amount of hours or minutes.
     */
    fun formatTime(minutes: Int): String {
        return when {
            minutes < 60 -> "$minutes min"
            else -> {
                when (val hours = minutes / 60) {
                    1 -> "$hours hr"
                    else -> "$hours hrs"
                }
            }
        }
    }

}