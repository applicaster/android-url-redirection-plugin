package com.applicaster.android_url_redirection_plugin

import android.content.Context
import android.util.Log
import com.applicaster.atom.model.APAtomEntry
import com.applicaster.hook_screen.HookScreen
import com.applicaster.hook_screen.HookScreenListener

class UrlRedirectionPlugin : HookScreen {

	private val KEY_HOOK_DATASOURSE = "hook_props_datasource"
	private var hookListener: HookScreenListener? = null

	override var hook: HashMap<String, String?> = hashMapOf()

	override fun executeHook(context: Context, hookListener: HookScreenListener, hookProps: Map<String, Any>?) {
		this.hookListener = hookListener
		val entry = hookProps?.get(KEY_HOOK_DATASOURSE) as? APAtomEntry
		//obtain redirected url
		val url: String = entry?.playable?.contentVideoURL.orEmpty()
		val redirectHandler = RedirectHandler { redirectUrl, errorMessage ->
			//success if not null
			redirectUrl?.let { redirectedUrl ->
				entry?.content?.src = redirectedUrl
			}
			//error if not null
			errorMessage?.let { Log.e(this.javaClass.simpleName, errorMessage) }
			//finish hook
			hookListener.hookCompleted(hookProps?.toMutableMap())
		}
		redirectHandler.redirectUrl(url)
	}

	override fun getListener(): HookScreenListener {
		return hookListener ?: object : HookScreenListener {
			override fun hookCompleted(hookProps: MutableMap<String, Any>?) = Unit
			override fun hookFailed(hookProps: MutableMap<String, Any>?) = Unit
		}
	}

	override fun hookDismissed() = Unit

	override fun isFlowBlocker(): Boolean = true

	override fun isRecurringHook(): Boolean = true

	override fun shouldPresent(): Boolean = true
}