package com.applicaster.android_url_redirection_plugin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		redirect()

	}

	private fun redirect() {
		val url: String = "http://link.theplatform.com/s/DGOYhC/Jgs9_cRk_u70?feed=Demo%20Feed"
		val redirectHandler = RedirectHandler { redirectUrl, errorMessage ->
			//success
			redirectUrl?.let { redirectedUrl -> runOnUiThread { Toast.makeText(this, "Success: $redirectedUrl", Toast.LENGTH_LONG).show() } }
			//failure
			errorMessage?.let { errorMsg -> runOnUiThread { Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_LONG).show() } }
		}
		redirectHandler.redirectUrl(url)
	}
}
