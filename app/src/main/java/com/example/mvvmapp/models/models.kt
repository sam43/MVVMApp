package com.example.mvvmapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ItemModel(
    val color: String? = ""
)

data class BusItemModel(
    val busBrand: String? = "",
    val busColor: String? = ""
)
data class Demo (
    @SerializedName("included")
    val included: List<Included?>? = listOf(),
    @SerializedName("u_data")
    val uData: List<UData?>? = listOf()
) {
    data class Included(
        @SerializedName("attributes")
        val attributes: Attributes? = Attributes(),
        @SerializedName("id")
        val id: Int? = 0,
        @SerializedName("type")
        val type: String? = ""
    ) {
        data class Attributes(
            @SerializedName("age")
            val age: Int? = 0,
            @SerializedName("gender")
            val gender: String? = "",
            @SerializedName("name")
            val name: String? = ""
        )
    }

    data class UData(
        @SerializedName("id")
        val id: Int? = 0,
        @SerializedName("relationships")
        val relationships: Relationships? = Relationships(),
        @SerializedName("type")
        val type: String? = ""
    ) {
        data class Relationships(
            @SerializedName("author")
            val author: Author? = Author()
        ) {
            data class Author(
                @SerializedName("a_data")
                val aData: AData? = AData()
            ) {
                data class AData(
                    @SerializedName("id")
                    val id: Int? = 0,
                    @SerializedName("type")
                    val type: String? = ""
                )
            }
        }
    }

    data class Student (
        val name: String? = null,
        val age: Int? = null
    )

}