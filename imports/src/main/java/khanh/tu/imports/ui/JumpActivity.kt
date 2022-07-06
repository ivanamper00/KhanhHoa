package khanh.tu.imports.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import khanh.tu.imports.utils.SharedPreferencesHelper
import khanh.tu.imports.view_model.JumpViewModel

abstract class JumpActivity: AppCompatActivity() {

    protected val cache by lazy {
        SharedPreferencesHelper.getInstance(this)
    }

    protected val viewModel by lazy {
        ViewModelProvider(this)[JumpViewModel::class.java]
    }

}