package uz.gita.talkiesjt.presenter.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.talkiesjt.R
import uz.gita.talkiesjt.data.model.ArticleData
import uz.gita.talkiesjt.data.model.ChipData
import uz.gita.talkiesjt.databinding.ItemChipBinding

class ChipAdapter: ListAdapter<ChipData, ChipAdapter.ChipViewHolder>(ChipDiffUtils) {
    private var onChipClickListener: ( (data: ChipData) -> Unit ) ?= null

    object ChipDiffUtils: DiffUtil.ItemCallback<ChipData>() {
        override fun areItemsTheSame(oldItem: ChipData, newItem: ChipData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ChipData, newItem: ChipData): Boolean {
            return oldItem == newItem
        }

    }

    inner class ChipViewHolder(private val binding: ItemChipBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val data = getItem(absoluteAdapterPosition)
                data.checked = !data.checked
                currentList.forEach {
                    if(it.id != data.id && it.checked) it.checked = false
                }
                notifyDataSetChanged()
                binding.root.isCloseIconVisible = data.checked
                // invoke listener
                onChipClickListener?.invoke(data)
            }
        }

        fun bind() {
            val data = getItem(absoluteAdapterPosition)
            binding.root.text = data.text
            binding.root.isChecked = data.checked
            binding.root.isCloseIconVisible = data.checked
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipViewHolder  = ChipViewHolder(
        ItemChipBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_chip, parent, false)
        )
    )

    override fun onBindViewHolder(holder: ChipViewHolder, position: Int) = holder.bind()

    fun setOnChipClickListener(block: (data: ChipData) -> Unit ) {
        onChipClickListener = block
    }
}