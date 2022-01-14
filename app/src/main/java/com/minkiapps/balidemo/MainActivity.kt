package com.minkiapps.balidemo

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.minkiapps.balidemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Bali Foreground Service Demo"

        binding.btnStartTemplateZero.setOnClickListener {
            actionOnService(Actions.TEMPLATE0)
        }

        binding.btnStartTemplateOne.setOnClickListener {
            actionOnService(Actions.TEMPLATE1)
        }

        binding.btnStartTemplateTwo.setOnClickListener {
            actionOnService(Actions.TEMPLATE2)
        }

        binding.btnStopService.setOnClickListener {
            actionOnService(Actions.STOP)
        }
    }

    private fun actionOnService(action: Actions) {
        Intent(this, BaliDemoService::class.java).also {
            it.action = action.name
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(it)
                return
            }
            startService(it)
        }
    }
}
