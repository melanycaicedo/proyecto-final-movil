package com.proyecto.ecotecsp2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Carga : AppCompatActivity() {
    private val duracion: Long = 3000 // Duración en milisegundos (3 segundos)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_carga)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Handler(Looper.getMainLooper()).postDelayed({
            // Código a ejecutar después del retraso
            val intent = Intent(this@Carga, Login::class.java)
            startActivity(intent)
            finish() // Finalizar la actividad actual
        }, duracion)
    }
}