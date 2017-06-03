package jp.ogiwara.AnitubeAPI2.http

import com.google.api.client.http.GenericUrl
import com.google.api.client.http.javanet.NetHttpTransport
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

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
