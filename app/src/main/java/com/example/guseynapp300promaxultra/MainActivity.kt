package com.example.guseynapp300promaxultra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.guseynapp300promaxultra.utils.EdgeToEdgeDecorator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureEdgeToEdge()
    }

    private fun configureEdgeToEdge() {
        EdgeToEdgeDecorator()
            .updateConfig { isEdgeToEdgeEnabled = true }
            .apply(this, window)
    }
}