package com.manuelmarvelapp.data.model.marvel.character_response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class MarvelResponse(

	@field:SerializedName("copyright")
	val copyright: String? = null,

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("attributionHTML")
	val attributionHTML: String? = null,

	@field:SerializedName("attributionText")
	val attributionText: String? = null,

	@field:SerializedName("etag")
	val etag: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

@Parcelize
data class ResultsItem(

	@field:SerializedName("thumbnail")
	val thumbnail: Thumbnail? = null,

	@field:SerializedName("urls")
	val urls: List<UrlsItem?>? = null,

	@field:SerializedName("stories")
	val stories: Stories? = null,

	@field:SerializedName("series")
	val series: Series? = null,

	@field:SerializedName("comics")
	val comics: Comics? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("modified")
	val modified: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("resourceURI")
	val resourceURI: String? = null,

	@field:SerializedName("events")
	val events: Events? = null
): Parcelable

@Parcelize
data class UrlsItem(

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
): Parcelable

@Parcelize
data class Stories(

	@field:SerializedName("collectionURI")
	val collectionURI: String? = null,

	@field:SerializedName("available")
	val available: Int? = null,

	@field:SerializedName("returned")
	val returned: Int? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
): Parcelable

@Parcelize
data class Series(

	@field:SerializedName("collectionURI")
	val collectionURI: String? = null,

	@field:SerializedName("available")
	val available: Int? = null,

	@field:SerializedName("returned")
	val returned: Int? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
): Parcelable

data class Data(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("offset")
	val offset: Int? = null,

	@field:SerializedName("limit")
	val limit: Int? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null
)

@Parcelize
data class Comics(

	@field:SerializedName("collectionURI")
	val collectionURI: String? = null,

	@field:SerializedName("available")
	val available: Int? = null,

	@field:SerializedName("returned")
	val returned: Int? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
): Parcelable

@Parcelize
data class Events(

	@field:SerializedName("collectionURI")
	val collectionURI: String? = null,

	@field:SerializedName("available")
	val available: Int? = null,

	@field:SerializedName("returned")
	val returned: Int? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
): Parcelable

@Parcelize
data class ItemsItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("resourceURI")
	val resourceURI: String? = null,

	@field:SerializedName("type")
	val type: String? = null
):Parcelable

@Parcelize
data class Thumbnail(

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("extension")
	val extension: String? = null
): Parcelable
