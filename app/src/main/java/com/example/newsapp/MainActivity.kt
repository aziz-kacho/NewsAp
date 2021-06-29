package com.example.newsapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.newsapp.data.Models.UserJV
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.rengwuxian.materialedittext.MaterialEditText

class MainActivity : AppCompatActivity() {
    private lateinit var btnSignIn: Button
    private lateinit var btnSignUp: Button
    private lateinit var root: RelativeLayout
    lateinit var auth: FirebaseAuth
    lateinit var dbFirebase: FirebaseDatabase
    lateinit var users: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSignIn = findViewById(R.id.btnSignIn)
        btnSignUp = findViewById(R.id.btnSignUp)
        root = findViewById(R.id.root_element)

        auth = FirebaseAuth.getInstance()
        dbFirebase = FirebaseDatabase.getInstance()
        users = dbFirebase.getReference("Users")

        btnSignUp.setOnClickListener {
            showRegisterDialog()
        }

        btnSignIn.setOnClickListener {
            showSignInDialog()
        }

    }

    override fun onStart() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            val intent = Intent(this, ProfileUser::class.java)
            startActivity(intent)
            finish()
        }
        super.onStart()
    }

    private fun showRegisterDialog() {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(this)
        dialog.setTitle("Зарегистрироваться")

        val inflater: LayoutInflater = LayoutInflater.from(this)
        val signUp: View = inflater.inflate(R.layout.signup_window, null)
        dialog.setView(signUp)

        val email: MaterialEditText = signUp.findViewById(R.id.emailField)
        val name: MaterialEditText = signUp.findViewById(R.id.nameField)
        val password: MaterialEditText = signUp.findViewById(R.id.passField)

        dialog.setNegativeButton("Отменить") { cancelBtn, _ ->
            cancelBtn.cancel()
        }

        dialog.setPositiveButton("Зарегистрироваться") { createBtn, _ ->
            if (email.text!!.isEmpty()) {
                Snackbar.make(root, "Введите свой Email", Snackbar.LENGTH_SHORT).show()
                return@setPositiveButton
            }

            if (name.text!!.isEmpty()) {
                Snackbar.make(root, "Введите свой имя", Snackbar.LENGTH_SHORT).show()
                return@setPositiveButton
            }

            if (password.text!!.isEmpty()) {
                Snackbar.make(root, "Введите свой пароль", Snackbar.LENGTH_SHORT).show()
                return@setPositiveButton
            }

            auth.createUserWithEmailAndPassword(email.text.toString(),
                password.text.toString()).addOnSuccessListener {

                val user =
                    UserJV(email.text.toString(), name.text.toString(), password.text.toString())
                it.user.let {
                    FirebaseDatabase.getInstance().reference.child(it!!.uid).setValue(user)
                }
            }
        }
        dialog.show()
    }

    @SuppressLint("WrongConstant", "ShowToast")
    private fun showSignInDialog() {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(this)
        dialog.setTitle("Войти")

        val inflater: LayoutInflater = LayoutInflater.from(this)
        val signIn: View = inflater.inflate(R.layout.signin_window, null)
        dialog.setView(signIn)

        val email: MaterialEditText = signIn.findViewById(R.id.emailField)
        val password: MaterialEditText = signIn.findViewById(R.id.passField)

        dialog.setNegativeButton("Отменить") { cancelBtn, _ ->
            cancelBtn.cancel()
        }

        dialog.setPositiveButton("Вход") { createBtn, _ ->
            if (email.text!!.isEmpty()) {
                Toast.makeText(this, "Введите свой Email", Snackbar.LENGTH_SHORT).show()
                return@setPositiveButton
            }
            if (password.text!!.isEmpty()) {
                Toast.makeText(this, "Введите свой пароль", Snackbar.LENGTH_SHORT).show()
                return@setPositiveButton
            }

            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnSuccessListener {
                    Log.d("TAG", "showSignInDialog: kor kad")
                    val intent = Intent(this, ProfileUser::class.java)
                    startActivity(intent)
                    finish()
                }.addOnFailureListener {
                    Toast.makeText(this, "Ошибка авторизации", Toast.LENGTH_SHORT)
                }
        }
        dialog.show()
    }
}

