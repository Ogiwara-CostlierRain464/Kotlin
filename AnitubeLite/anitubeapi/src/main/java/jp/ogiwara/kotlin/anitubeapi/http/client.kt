package jp.ogiwara.kotlin.anitubeapi.http

import com.google.api.client.http.GenericUrl
import com.google.api.client.http.javanet.NetHttpTransport
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

fun getBody(url: String): Document{
    val httpTransport = NetHttpTransport()
    val requestFactory = httpTransport.createRequestFactory()
    val uri = GenericUrl(url)
    val req = requestFactory.buildGetRequest(uri)
    val res = req.execute()
    val result = Jsoup.parse(res.parseAsString())

    res.disconnect()
    httpTransport.shutdown()

    return result
}
