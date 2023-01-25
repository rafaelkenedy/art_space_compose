package com.example.artspacecompose.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Frame(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val author: Int,
    @StringRes val year: Int
)
