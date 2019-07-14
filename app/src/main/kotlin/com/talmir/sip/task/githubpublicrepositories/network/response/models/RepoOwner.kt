package com.talmir.sip.task.githubpublicrepositories.network.response.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * A model class which holds information about repo owner's data.
 */
@Parcelize
data class RepoOwner(
    @SerializedName("avatar_url")
    val avatar: String = "",

    @SerializedName("login")
    val name: String = "") : Parcelable
