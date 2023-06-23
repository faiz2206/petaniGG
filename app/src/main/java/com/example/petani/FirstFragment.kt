package com.example.petani

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petani.application.PetaniApp
import com.example.petani.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val petaniViewModel: PetaniViewModel by viewModels {
        PetaniViewModelFactory((applicationContext as PetaniApp).repository)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = context.applicationContext
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PetaniListAdapter{ petani ->
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(petani)
            findNavController().navigate(action)
        }
        binding.dataRecyclerView.adapter = adapter
        binding.dataRecyclerView.layoutManager = LinearLayoutManager(context)
        PetaniViewModel.allPetanis.observe(viewLifecycleOwner) { petani ->
            petani.let {
                if (petani.isEmpty()){
                    binding.notFoundImageView.visibility = View.VISIBLE
                    binding.notFoundTextView.visibility = View.VISIBLE
                }else{
                    binding.notFoundImageView.visibility = View.GONE
                    binding.notFoundTextView.visibility = View.GONE
                }
                adapter.submitList(petani)
            }
        }

        binding.addFAB.setOnClickListener {
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(null)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}