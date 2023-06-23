package com.example.petani

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.petani.application.PetaniApp
import com.example.petani.databinding.FragmentSecondBinding
import com.example.petani.model.Petani


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val PetaniViewModel: PetaniViewModel by viewModels {
        PetaniViewModelFactory ((applicationContext as PetaniApp).repository)
    }

    private val args: SecondFragmentArgs? by navArgs()
    private var petani : Petani? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = context.applicationContext
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View ? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        petani = args?.petani
        if (petani != null){
            binding.deleteButton.visibility = View.VISIBLE
            binding.saveButton.text = "Update"
            binding.nameEditText.setText(petani?.title)
            binding.categoryEditText.setText(petani?.category)
            binding.contentTextMultiLine.setText(petani?.content)
            val label = "Edit Petani"
            navController.currentDestination?.label = label
        }else{
            val label = "Add Petani"
            navController.currentDestination?.label = label
        }

        val title = binding.nameEditText.text
        val category = binding.categoryEditText.text
        val content = binding.contentTextMultiLine.text
        binding.saveButton.setOnClickListener {
            if(title.isEmpty() || category.isEmpty() || content.isEmpty()){
                if (title.isEmpty()){
                    Toast.makeText(context, "Title is required", Toast.LENGTH_SHORT).show()
                }
                if (category.isEmpty()){
                    Toast.makeText(context, "Category is required", Toast.LENGTH_SHORT).show()
                }
                if (content.isEmpty()){
                    Toast.makeText(context, "Content is required", Toast.LENGTH_SHORT).show()
                }
            }else{
                if (petani == null){
                    val petani = Petani(title = title.toString(), category = category.toString(), content = content.toString())
                    PetaniViewModel.insert(petani)
                }else{
                    val petani = Petani(id = petani?.id!!, title = title.toString(), category = category.toString(), content = content.toString())
                    PetaniViewModel.update(petani)
                }
                findNavController().popBackStack()
            }
        }

        binding.deleteButton.setOnClickListener {
            PetaniViewModel.delete(petani!!)
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}