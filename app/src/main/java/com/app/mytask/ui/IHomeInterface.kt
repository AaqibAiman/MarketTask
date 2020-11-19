package com.app.mytask.ui

import android.os.Bundle
import androidx.fragment.app.Fragment

interface IHomeInterface {
    fun startFragment(fragment: Fragment, bundle: Bundle?, tag: String): Boolean
}