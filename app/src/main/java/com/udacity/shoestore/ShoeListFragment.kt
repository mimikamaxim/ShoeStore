package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeCardBinding
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.ShoeViewModel

class ShoeListFragment : Fragment() {
    lateinit var viewModel: ShoeViewModel
    lateinit var binding: FragmentShoeListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        binding.toDetail.setOnClickListener {
            findNavController().navigate(
                ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment()
            )
        }
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)

        getShoeList()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.sidebar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun getShoeList() {
        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            for (shoe in shoeList) {
                val listBinding = FragmentShoeCardBinding.inflate(layoutInflater)
                listBinding.shoe = shoe
                binding.shoeListView.addView(listBinding.root)
            }
        })
    }

}