package jp.ogiwara.AnitubeAPI.utils

import org.apache.commons.lang3.StringUtils

/**
 * Created by ogiwara on 2017/04/05.
 */
fun String.trim(from: String,to: String): String{
    val str1 = StringUtils.substringAfter(this,from)
    val str2 = StringUtils.substringBefore(str1,to)
    return str2
}