package ogiwara.sample.qiitaclient

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import ogiwara.sample.qiitaclient.model.Article
import ogiwara.sample.qiitaclient.view.ArticleView


class ArticleListAdapter(private val context: Context): BaseAdapter() {

    var articles: List<Article> = emptyList()

    override fun getCount() = articles.size

    override fun getItem(position: Int) = articles.get(position)

    override fun getItemId(position: Int) = 0L

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View =
        ((convertView as? ArticleView) ?: ArticleView(context)).apply {
            setArticle(articles.get(position))
        }
}