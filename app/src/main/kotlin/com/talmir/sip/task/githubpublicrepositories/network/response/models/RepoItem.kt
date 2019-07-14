package com.talmir.sip.task.githubpublicrepositories.network.response.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

/**
 * A model class that holds GitHub repository details.
 */
@Parcelize
data class RepoItem(
    val id: Int,

    val owner: RepoOwner = RepoOwner(),

    val name: String = "",

    @SerializedName("description")
    private val _description: String?,

    @SerializedName("homepage")
    val homepageUrl: String = "",

    @SerializedName("commits_url")
    private val _commitsUrl: String = "",

    @SerializedName("html_url")
    val htmlUrl: String = "",

    @SerializedName("language")
    private val _language: String?,

    @SerializedName("created_at")
    private val _createdAt: String = "",

    @SerializedName("pushed_at")
    private val _pushedAt: String = "",

    @SerializedName("updated_at")
    private val _updatedAt: String = "",

    @SerializedName("stargazers_count")
    private val _starsCount: Int = 0,

    @SerializedName("forks_count")
    private val _forksCount: Int = 0,

    val license: RepoLicense = RepoLicense()) : Parcelable {

    @IgnoredOnParcel
    private val apiDateFormat: SimpleDateFormat by lazy {
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    }

    @IgnoredOnParcel
    private val localDateFormat: SimpleDateFormat by lazy {
        SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    }

    /**
     * Remove unnecessary parts from [_commitsUrl] and set new value to [commitsUrl].
     */
    val commitsUrl: String
        get() = if (_commitsUrl.isNotBlank()) _commitsUrl.replace("api.", "").replace("repos/", "") else ""

    /**
     * Parse [_createdAt] as desired format mentioned in [localDateFormat].
     */
    val createdAt: String
        get() = localDateFormat.format(apiDateFormat.parse(_createdAt)!!)

    /**
     * Parse [_pushedAt] as desired format mentioned in [localDateFormat].
     */
    val pushedAt: String
        get() = localDateFormat.format(apiDateFormat.parse(_pushedAt)!!)

    /**
     * Parse [_updatedAt] as desired format mentioned in [localDateFormat].
     */
    val updatedAt: String
        get() = localDateFormat.format(apiDateFormat.parse(_updatedAt)!!)

    /**
     * Due to it's possible to get null from GitHub API, escape from it
     * easily by using Elvis operator :D
     */
    val description: String
        get() = _description ?: "No description"

    /**
     * We may get null from GitHub API, escape from it easily by using
     * Elvis operator again :D
     */
    val language: String
        get() = _language ?: "NaN"

    /**
     * [_starsCount] type is [Int] and we cannot set [Int] value to
     * [TextView], thus convert it simply.
     */
    val starsCount: String
        get() = _starsCount.toString()

    /**
     * [_forksCount] type is [Int] and we cannot set [Int] value to
     * [TextView], thus convert it simply.
     */
    val forksCount: String
        get() = _forksCount.toString()
}
