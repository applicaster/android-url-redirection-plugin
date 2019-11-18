package com.applicaster.android_url_redirection_plugin

import okhttp3.*
import java.io.IOException

class RedirectHandler(private val redirectCallback: (redirectUrl: String?, errorMessage: String?) -> Unit) {
	fun redirectUrl(url: String) {
		val client = OkHttpClient().newBuilder()
				.followRedirects(true)
				.followSslRedirects(true)
				.build()
		val request = Request.Builder().url(url).build()
		client.newCall(request).enqueue(object : Callback {
			override fun onFailure(call: Call, e: IOException) {
				redirectCallback(null, e.message.orEmpty())
			}

			override fun onResponse(call: Call, response: Response) {
				if (response.isSuccessful) {
					redirectCallback(response.request().url().toString(), null)
				} else {
					redirectCallback(null, response.message())
				}
			}
		})
	}
}