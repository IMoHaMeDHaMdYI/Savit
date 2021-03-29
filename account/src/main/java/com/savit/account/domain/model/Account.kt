package com.savit.account.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Account(val id: Long, val name: String, val amount: Double) : Parcelable
