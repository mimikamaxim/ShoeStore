package com.udacity.shoestore

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeViewModel

class ShoeDetailFragment : Fragment() {
    private lateinit var binding: FragmentShoeDetailBinding
    private lateinit var viewModel: ShoeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.cancel.setOnClickListener {
            findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }

        binding.save.setOnClickListener {
            clickSave()
        }
        return binding.root
    }

    private fun clickSave() {
        if (TextUtils.isEmpty(viewModel.shoe.name) ||
            TextUtils.isEmpty(viewModel.shoe.company) ||
            TextUtils.isEmpty(viewModel.shoe.size) ||
            TextUtils.isEmpty(viewModel.shoe.description)
        ) {
            Toast.makeText(requireContext(), getString(R.string.not_all_fields_filled), Toast.LENGTH_SHORT).show()
        } else {
            val newShoe = (Shoe(
                viewModel.shoe.name,
                viewModel.shoe.company,
                viewModel.shoe.size,
                viewModel.shoe.description
            ))
            viewModel.addShoeToList(newShoe)
            findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }
    }
}