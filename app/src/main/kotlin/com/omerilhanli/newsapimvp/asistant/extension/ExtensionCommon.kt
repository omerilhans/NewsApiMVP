package com.omerilhanli.newsapimvp.asistant.extension

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.omerilhanli.newsapimvp.App
import com.omerilhanli.newsapimvp.mvvm.view.activity.MainActivity
import android.net.ConnectivityManager
import android.view.LayoutInflater
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.omerilhanli.newsapimvp.R
import com.omerilhanli.newsapimvp.databinding.LayoutDialogBinding

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun Context.hasNetworkConnection(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
}

fun Context.openWebTabBrowser(url: String) {
    if (!url.isNullOrEmpty()) {
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(
            ContextCompat.getColor(
                this,
                R.color.colorPrimaryDark
            )
        )
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }
}

fun MainActivity.inject() {
    val app = application as App
    app.component?.inject(this)
}

fun Context.showPopupFor(message: String) {
    val builder = AlertDialog.Builder(this)
    val binding = DataBindingUtil.inflate<LayoutDialogBinding>(
        LayoutInflater.from(this),
        R.layout.layout_dialog,
        null,
        false
    )
    builder.setView(binding.root).setCancelable(true)
    val dialog = builder.create()
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    binding.description = message
    binding.btnOk.setOnClickListener {
        dialog.dismiss()
    }

    dialog.show()
}

fun ActionBar?.configure() {
    this?.setDisplayHomeAsUpEnabled(true)
    this?.setDisplayShowHomeEnabled(true)
    this?.setHomeAsUpIndicator(R.drawable.icn_about)
}