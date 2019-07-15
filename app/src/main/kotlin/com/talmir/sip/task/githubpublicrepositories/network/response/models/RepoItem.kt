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

    @SerializedName("html_url")
    val gitHubUrl: String = "",

    @SerializedName("language")
    private val _language: String?,

    @SerializedName("stargazers_count")
    val starsCountInt: Int = 0,

    @SerializedName("forks_count")
    val forksCountInt: Int = 0,

    val license: RepoLicense = RepoLicense()) : Parcelable {

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
        get() = _language ?: "---"

    /**
     * [starsCountInt] type is [Int] and we cannot set [Int] value to
     * [TextView], thus convert it simply.
     */
    val starsCount: String
        get() = starsCountInt.toString()

    /**
     * [forksCountInt] type is [Int] and we cannot set [Int] value to
     * [TextView], thus convert it simply.
     */
    val forksCount: String
        get() = forksCountInt.toString()
}
