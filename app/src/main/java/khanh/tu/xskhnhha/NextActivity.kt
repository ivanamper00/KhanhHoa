package khanh.tu.xskhnhha

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import khanh.tu.xskhnhha.jump_code.ui.web_view.WebViewActivity

class NextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, NextActivity::class.java)
    }
}