package ogiwara.sample.qiitaclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import com.trello.rxlifecycle.kotlin.bindToLifecycle
import ogiwara.sample.qiitaclient.client.ArticleClient
import ogiwara.sample.qiitaclient.model.Article
import ogiwara.sample.qiitaclient.model.User
import ogiwara.sample.qiitaclient.view.ArticleView
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar = findViewById(R.id.progress_bar) as ProgressBar

        val listAdapter = ArticleListAdapter(applicationContext)
        listAdapter.articles = listOf(dummyArticle("Kotlin","w"),dummyArticle("Java","j"))

        val listView: ListView = findViewById(R.id.list_view) as ListView
        listView.adapter = listAdapter
        listView.setOnItemClickListener { parent, view, position, id ->
            val article = listAdapter.articles[position]
            ArticleActivity.intent(this,article).let { startActivity(it) }
        }

        val gson =GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://qiita.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        val articleClient = retrofit.create(ArticleClient::class.java)

        val queryEditText = findViewById(R.id.query_edit_text) as EditText
        val searchButton = findViewById(R.id.search_button) as Button

        searchButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE

            articleClient.search(queryEditText.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate {
                        progressBar.visibility = View.GONE
                    }
                    .bindToLifecycle(this)
                    .subscribe({
                        queryEditText.text.clear()
                        listAdapter.articles = it
                        listAdapter.notifyDataSetChanged()
                    },{
                        Toast.makeText(this@MainActivity,"エラー: $it",Toast.LENGTH_SHORT).show()
                    })//SAM変換!!!
        }
    }

    private fun dummyArticle(title: String,userName: String): Article =
            Article("",title,"https://kotlinlang.org/",User("",userName,""))
}
