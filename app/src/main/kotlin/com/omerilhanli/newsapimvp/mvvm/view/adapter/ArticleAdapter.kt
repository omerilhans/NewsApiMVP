package com.omerilhanli.newsapimvp.mvvm.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.omerilhanli.newsapimvp.R
import com.omerilhanli.newsapimvp.asistant.callback.ItemClickListener
import com.omerilhanli.newsapimvp.mvvm.model.entity.Article
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleAdapter @Inject constructor() :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    var list: List<Article>? = emptyList()
    var listener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_news,
            parent,
            false
        )

        return ArticleViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(list!![position])
    }

    fun updateAndNotify(_list: List<Article>?) {
        this.list = _list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list?.size ?: 0

    class ArticleViewHolder(
        private val binding: ViewDataBinding,
        private val listener: ItemClickListener?
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            binding.root.setOnClickListener {
                listener?.click(article.url!!)
            }
            binding.setVariable(BR.article, article)
            binding.executePendingBindings()
        }
    }
}