package com.talmir.sip.task.githubpublicrepositories.network.response.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * A model class that holds GitHub repository license details.
 */
@Parcelize
data class RepoLicense(val name: String = "") : Parcelable