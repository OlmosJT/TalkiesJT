package uz.gita.talkiesjt.data.remote.objson

data class Result(
    val uri: String,
    val url: String,
    val id: Long,
    val asset_id: Long,
    val source: String,
    val published_date: String,
    val updated: String,
    val section: String,
    val subsection: String,
    val nytdsection: String,
    val adx_keywords: String,
    val column: Any?,
    val byline: String,
    val type: String,
    val title: String,
    val `abstract`: String,
    val des_facet: List<String>,
    val org_facet: List<String>,
    val per_facet: List<String>,
    val geo_facet: List<String>,
    val media: List<Media>,
    val eta_id: Int
)