package io.pikassa.sdk.entities

/**
Created by Denis Chornyy on 25,Июнь,2020
All rights received.
 */

/**
 * Class representing redirect structure
 * @param url redirect url
 * @param method http method
 * @param params additional parameters
 */
data class RedirectResponse(
    val url: String,
    val method: String,
    val params: List<Pair<String, String>>?
) {
    override fun toString(): String {
        return "redirect url: $url\nhttp method: $method\nparams: ${params?.forEach { "${it.first}: ${it.second}" }}"
    }
}