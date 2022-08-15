package uz.gita.talkiesjt.presenter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.talkiesjt.R
import uz.gita.talkiesjt.data.model.ArticleData
import uz.gita.talkiesjt.databinding.ItemArticleBinding

class ArticleAdapter: ListAdapter<ArticleData, ArticleAdapter.ArticleViewHolder>(ArticleDiffUtils) {
    private var onArticleClickListener: ( (data: ArticleData) -> Unit ) ?= null

    object ArticleDiffUtils: DiffUtil.ItemCallback<ArticleData>() {
        override fun areItemsTheSame(oldItem: ArticleData, newItem: ArticleData): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: ArticleData, newItem: ArticleData): Boolean {
            return oldItem == newItem
        }
    }

    inner class ArticleViewHolder(private val binding: ItemArticleBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            // when clicks listen and invoke
            binding.cardView.setOnClickListener {
                onArticleClickListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun bind() {
            getItem(absoluteAdapterPosition).apply {
                binding.articleName.text = this.title
                binding.articleType.text = section.ifEmpty { subsection }
                binding.articlePublishedDate.text = published_date
                if(!this.imageUrl.isNullOrEmpty()){
                    Glide.with(binding.root).load(this.imageUrl).apply {
                        centerCrop()
                        placeholder(R.drawable.ic_launcher_background)
                        error(R.drawable.ic_launcher_background)
                    }.into(binding.image)
                } else {
                    binding.image.setImageResource(R.drawable.ic_launcher_background)
                }

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder = ArticleViewHolder(
        ItemArticleBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        )
    )

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) = holder.bind()

    fun setOnArticleClickListener(block: (data: ArticleData) -> Unit ) {
        onArticleClickListener = block
    }
}