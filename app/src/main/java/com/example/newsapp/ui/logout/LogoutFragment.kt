package com.example.newsapp.ui.logout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newsapp.MainActivity
import com.example.newsapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_user.*

class LogoutFragment : Fragment() {
    private lateinit var logoutConstraintLayout: ConstraintLayout
    private lateinit var savedConstraintLayout : ConstraintLayout


    private var logout = FirebaseAuth.getInstance()
    private lateinit var allViewModel: AllViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        saveViewModel = ViewModelProvider(this).get(SaveViewModel::class.java)
        allViewModel = ViewModelProvider(this).get(AllViewModel::class.java)

        logoutConstraintLayout = view.findViewById(R.id.logout)
        logoutConstraintLayout.setOnClickListener {
            val dialog: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
            dialog.setTitle("Выход")
            dialog.setMessage("Вы действительно хотите выйти")

            dialog.setNegativeButton("Отменить") { cancelBtn, _ ->
                cancelBtn.cancel()
            }

            dialog.setPositiveButton("Выйти") { createBtn, _ ->
                logout()
            }
            dialog.show()
            Log.d("TAG", "onViewCreated: salom")
        }

        logout.addAuthStateListener {
            if (logout.currentUser == null) {
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()

                allViewModel.deleteAllNews()
            }
        }

        savedConstraintLayout = view.findViewById(R.id.savedNews)
        savedConstraintLayout.setOnClickListener {
            findNavController().navigate(R.id.saveNewsFragment)
        }
        history.setOnClickListener {
            findNavController().navigate(R.id.historyFragment)
        }


    }

    private fun logout() {
        logout.signOut()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }


}