package com.upn.catatlari.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.upn.catatlari.databinding.FragmentLoginBinding
import com.upn.catatlari.data.AppDatabase
import com.upn.catatlari.Repository.UserRepository
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnLogin.setOnClickListener {

            val email = binding.etemail.text.toString()
            val password = binding.etPassword.text.toString()

            val db = AppDatabase.getDatabase(requireContext())
            val repository = UserRepository(db.userDao())

            lifecycleScope.launch {

                val user = repository.login(email, password)

                Log.d("LOGIN_DEBUG", "User = ${user?.name}")

                if (user != null) {

                    val intent = Intent(requireContext(), MainActivity::class.java)
                    intent.putExtra("user", user)

                    // 🔥 FIX PENTING
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                    startActivity(intent)

                } else {
                    Toast.makeText(requireContext(), "Email / Password salah", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}