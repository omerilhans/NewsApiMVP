package com.omerilhanli.newsapimvp.mvvm.model.entity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import javax.inject.Inject

class ViewVisibility @Inject constructor() : BaseObservable() {

    var visible: Boolean? = false
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.visible)
        }
}