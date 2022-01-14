package com.minkiapps.balidemo.domain

import android.os.Parcelable
import androidx.annotation.ColorInt
import kotlinx.android.parcel.Parcelize

enum class TemplateIndex {
    ZERO,ONE,TWO
}

interface TemplateDemo : Parcelable

@Parcelize
data class TemplateZero(
    val templateTitle: String? = null,
    @ColorInt val titleColor: Int = 0xffffffff.toInt(),
    val templateContent: String,
    @ColorInt val contentColor: Int = 0xffffffff.toInt(),
    val disColorContent: String? = null,
    @ColorInt val disColor: Int = 0xffffffff.toInt(),
    val templateSecondly: String? = null,
    @ColorInt val secondlyColor: Int = 0xffffffff.toInt()
) : TemplateDemo

@Parcelize
data class TemplateOne(
    val templateTitle: String? = null,
    @ColorInt val titleColor: Int = 0xffffffff.toInt(),
    val templateContentFirst: TemplateContent,
    val templateContentSecondly: TemplateContent,
    val templateBottom: TemplateContent,
) : TemplateDemo

@Parcelize
data class TemplateTwo(
    val templateTitle: String? = null,
    @ColorInt val titleColor: Int = 0xe6ffffff.toInt(),
    val midContent: String? = null,
    val midContentBigSize : Boolean = false,
    @ColorInt val midContentColor: Int = 0xE6ffffff.toInt(),
    val bottomContent: String,
    @ColorInt val bottomContentColor: Int = 0x99ffffff.toInt(),
    val progress: Int,
    @ColorInt val progressColor: Int = 0x33ffffff.toInt(),
    @ColorInt val progressBgColor: Int = 0xff317af7.toInt()
) : TemplateDemo, Parcelable

@Parcelize
data class TemplateContent(
    val oriContent : String,
    @ColorInt val oriContentColor: Int,
    val disContents : List<DisContent> = arrayListOf()
) : Parcelable

@Parcelize
data class DisContent(
    val disContent : String,
    @ColorInt val disContentColor: Int
) : Parcelable