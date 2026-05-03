package com.upn.catatlari.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.upn.catatlari.databinding.FragmentRegisterBinding
import com.upn.catatlari.model.User
import com.upn.catatlari.data.AppDatabase
import com.upn.catatlari.Repository.UserRepository
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnRegister.setOnClickListener {

            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val confirm = binding.etConfirmPassword.text.toString()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(requireContext(), "Isi semua field!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirm) {
                Toast.makeText(requireContext(), "Password tidak sama!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val db = AppDatabase.getDatabase(requireContext())
            val repository = UserRepository(db.userDao())

            lifecycleScope.launch {

                val newUser = User(name = name, email = email, password = password)

                repository.register(newUser)

                // 🔥 AMBIL USER DARI DATABASE (WAJIB)
                val loginUser = repository.login(email, password)

                Log.d("REGISTER_DEBUG", "User dari DB = ${loginUser?.name}")

                if (loginUser != null) {

                    val intent = Intent(requireContext(), MainActivity::class.java)
                    intent.putExtra("user", loginUser)

                    // 🔥 FIX BIAR TIDAK NULL
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                    startActivity(intent)

                } else {
                    Toast.makeText(requireContext(), "Gagal ambil user", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}