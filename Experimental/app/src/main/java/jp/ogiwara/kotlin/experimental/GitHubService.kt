package jp.ogiwara.kotlin.experimental

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * RetrofitでGitHubのAPIを利用するためのクラス
 */
interface GitHubService {

    /**
     * GitHubのリポジトリ検索結果を取得する
     * https://developer.github.com/v3/search/
     * @param query GitHubのAPIで検索を行う内容
     * @return APIアクセス結果取得後のコールバックとしてSearchResponseが取得できるRxJavaのObservableで返す
     */
    @GET("search/repositories?sort=stars&order=desc")
    fun listRepos(@Query("q")query: String): Observable<Repositories>

    /**
     * リポジトリの詳細を取得する
     * https://developer.github.com/v3/repos/#get
     * @return APIアクセス結果取得後のコールバックとしてRepositoryItemが取得できるRxJavaのObservableで返す
     */
    @GET("repos/{repoOwner}/{repoName}")
    fun detailRepo(@Path(value = "repoOwner") owner: String,@Path(value = "repoName") repoName: String): Observable<RepositoryItem>
}

/**
 * APIアクセス結果がこのクラスに入る
 * GitHubのリポジトリのリストが入っている
 * @see GitHubService#listRepos(String)
 */
data class Repositories(val items: List<RepositoryItem>)

/**
 * APIアクセス結果がこのクラスに入る
 * GitHubのリポジトリのデータが入っている
 * @see GitHubService#detailRepo(String, String)
 */
data class RepositoryItem(val description: String,val owner: Owner,val language: String,val name: String,
                          val stargazers_count: String,val forks_count: String,val full_name: String,val html_url: String)

/**
 * GitHubのリポジトリに対するオーナーのデータが入っている
 * @see GitHubService#detailRepo(String, String)
 */
data class Owner(val received_events_url: String,val organizations_url: String,val avatar_url: String,val gravatar_id: String,
                 val gists_url: String,val starred_url: String,val site_admin: String,val type: String,val url: String,val id: String,
                 val html_url: String, val following_url: String,val events_url: String,val login: String,val subscriptions_url: String,
                 val repos_url: String,val followers_url: String)