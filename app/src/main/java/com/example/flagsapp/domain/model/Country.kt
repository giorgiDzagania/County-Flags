package com.example.flagsapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val name: Name?,
    val flags: Flags?,
    val capital: List<String>?,
) : Parcelable {

    @Parcelize
    data class Name(
        val common: String?,
        val official: String?
    ) : Parcelable

    @Parcelize
    data class Flags(
        val png: String?,
        val svg: String?
    ) : Parcelable
}