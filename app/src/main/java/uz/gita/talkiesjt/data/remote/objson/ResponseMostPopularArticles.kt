package uz.gita.talkiesjt.data.remote.objson

data class ResponseMostPopularArticles(
    val status: String,
    val copyright: String,
    val num_results: Int,
    val results: List<Result>
)