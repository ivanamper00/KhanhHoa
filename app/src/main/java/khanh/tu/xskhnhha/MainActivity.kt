package khanh.tu.xskhnhha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import khanh.tu.xskhnhha.jump_code.ui.web_view.WebViewActivity
import khanh.tu.xskhnhha.jump_code.view_model.JumpViewModel
import khanh.tu.xskhnhha.utils.*

class MainActivity : AppCompatActivity() {

    /**
     * applicationId is the id of the jumpCode
     * '123456' for testing
     * packageName for release
     */
    private val applicationID: String
        get() = packageName // or 123456

    private lateinit var cache: SharedPreferencesHelper

    private lateinit var viewModel: JumpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cache = SharedPreferencesHelper.getInstance(this)
        viewModel = ViewModelProvider(this)[JumpViewModel::class.java]

        viewModel.urlResponse.observe(this){ response ->
            when(response){
                is Response.Success -> {
                    if(!response.data.isNullOrEmpty()){
                        startActivity(WebViewActivity.createIntent(this, response.data ?: ""))
                        finish()
                    }else {
                        // execute code if the url is empty
                        startActivity(NextActivity.createIntent(this))
                        finish()
                    }
                }
                is Response.Error -> {
                    when(response.exception){
                        is NetworkException -> {
                            //Switch to other domain and retry request
                            RetrofitHelper.onRetry = true
                            viewModel.getUrl(applicationID)
                        }
                        else -> {
                            // handling other type of errors
                        }
                    }
                }
            }
        }

        viewModel.registrationResponse.observe(this){ response ->
            when(response){
                is Response.Success -> viewModel.getUrl(applicationID)
                is Response.Error -> {
                    when(response.exception){
                        is NetworkException -> {
                            //Switch to other domain and retry request
                            RetrofitHelper.onRetry = true
                            viewModel.registerApp(applicationID)
                        }
                        else -> {
                            // handling other type of errors
                        }
                    }
                }
            }
        }

        if(cache.getIsAppRegistered()) viewModel.getUrl(applicationID)
        else viewModel.registerApp(applicationID)
    }
}