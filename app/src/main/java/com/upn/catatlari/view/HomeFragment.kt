package com.upn.catatlari.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.upn.catatlari.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val user = (activity as MainActivity).user

        binding.welcomingTxt.text = "Halo, ${user?.name ?: "User"}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}