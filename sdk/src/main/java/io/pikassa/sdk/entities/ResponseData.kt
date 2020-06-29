package io.pikassa.sdk.entities

/**
Created by Denis Chornyy on 25,Июнь,2020
All rights received.
 */

/**
 * class representing response structure
 * @param uuid payment identificator
 * @param requestId request identificator
 * @param redirect redirect data (if exists)w
 */
data class ResponseData(
    val uuid: String,
    val requestId: String,
    val redirect: RedirectResponse?
) {
    override fun toString(): String {
        return "uuid: $uuid\nrequest ID: $requestId\nredirectInfo:\n${redirect.toString()}"
    }
}