package uz.gita.talkiesjt.data.model

import java.io.Serializable

data class ArticleData(
    val title: String,
    val section: String,
    val subsection: String,
    val published_date: String,
    val url: String,
    val imageUrl: String?
): Serializable
