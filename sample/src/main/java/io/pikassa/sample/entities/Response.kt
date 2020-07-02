package io.pikassa.sample.entities

/**
Created by Denis Chornyy on 02,Июль,2020
All rights received.
 */
data class Response <T, V>  (
    val success: Boolean,
    val data: T?,
    val error: V?
)