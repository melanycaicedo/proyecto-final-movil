package com.proyecto.ecotecsp2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.proyecto.ecotecsp2.databinding.ActivityLoginBinding
class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            // Aquí puedes añadir la lógica de autenticación del usuario
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // Finalizar la actividad actual para que no esté en la pila de actividades
            finish()
        }
    }
}