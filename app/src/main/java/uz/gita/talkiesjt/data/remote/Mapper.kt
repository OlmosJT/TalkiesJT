package uz.gita.talkiesjt.data.remote

import uz.gita.talkiesjt.data.model.ArticleData
import uz.gita.talkiesjt.data.remote.objson.Result
import uz.gita.talkiesjt.data.remote.response.TopStoriesResult

object Mapper {
    fun Result.toArticleData(): ArticleData = ArticleData(
        title = this.title,
        section = this.section,
        subsection = this.subsection,
        published_date = this.published_date,
        url = this.url,
        imageUrl = this.media[0].`media-metadata`[1].url
    )

    fun TopStoriesResult.Result.toArticleData() = ArticleData(
        title = this.title,
        section = this.section,
        subsection = this.subsection,
        published_date = this.published_date,
        url = this.url,
        imageUrl = if(!multimedia.isNullOrEmpty()) this.multimedia[0].url else null
    )
}