package com.example.n2_pedrobalbastrobroso_andrecorradi

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()

        cadastrar.setOnClickListener {
            val email = email.text.toString()
            val senha = senha.text.toString()

                mAuth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                this, "Registrado com sucesso!!! " + email,
                                Toast.LENGTH_LONG
                            ).show();
                        } else {
                            Toast.makeText(this, "Algo de errado aconteceu!!!", Toast.LENGTH_LONG)
                                .show();
                        }
                    }
        }

        logar.setOnClickListener {
            var email = email.text.toString()
            var senha = senha.text.toString()

            mAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(
                            this,
                            "Logado com sucesso!!! ", Toast.LENGTH_LONG
                        ).show()
                        val intent = Intent(this, MainActivity2::class.java)
                        intent.putExtra("email", email)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this,
                            "“Algo de errado aconteceu!!! ", Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }

    private fun validarForm (email: TextView, senha: TextView): Boolean {
        var valido=true
        if (TextUtils.isEmpty(email.text.toString())){
            email.error="Campo obrigatorio"
            valido= false
        }
        if (TextUtils.isEmpty(senha.text.toString())){
            senha.error="Campo obrigatorio"
            valido= false
        }
        return valido
    }
}
