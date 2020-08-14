package com.ando.wo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ando.wo.databinding.ActivityMainBinding
import androidx.databinding.DataBindingUtil.setContentView
import com.ando.wo.ui.WxArticleFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Note : import androidx.databinding.DataBindingUtil.setContentView
        setContentView<ActivityMainBinding>(this, R.layout.activity_main)


        //
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_main_container, WxArticleFragment(), "wx_article_tabs")
            .commitAllowingStateLoss()

    }
}