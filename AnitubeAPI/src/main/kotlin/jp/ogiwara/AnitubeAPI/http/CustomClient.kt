package jp.ogiwara.AnitubeAPI.http

import com.google.api.client.http.GenericUrl
import com.google.api.client.http.javanet.NetHttpTransport

fun getBody(url: String): String{
    val httpTransport = NetHttpTransport()
    val requestFactory = httpTransport.createRequestFactory()
    val uri = GenericUrl(url)
    val req = requestFactory.buildGetRequest(uri)
    val res = req.execute()
    val result =  res.parseAsString()

    res.disconnect()
    httpTransport.shutdown()

    return result
}