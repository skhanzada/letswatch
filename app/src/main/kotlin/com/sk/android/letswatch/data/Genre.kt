package com.sk.android.letswatch.data

import android.arch.persistence.room.Entity

@Entity
data class Genre(val id: Int, val name: String)