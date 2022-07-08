package com.feyza.applicationecommerce.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.feyza.applicationecommerce.R
import com.feyza.applicationecommerce.adapter.BagAdapter
import com.feyza.applicationecommerce.database.viewmodel.BagViewModel
import com.feyza.applicationecommerce.databinding.FragmentBagBinding
import com.google.firebase.auth.FirebaseAuth

class BagFragment : Fragment() {
    private var _binding: FragmentBagBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { BagViewModel(requireContext()) }
    private val itemBagAdapter by lazy { BagAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_bag, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserves()
    }

    private fun initObserves() {
        with(binding){
            with(viewModel){

                FirebaseAuth.getInstance().currentUser?.let {
                    getBagProducts(it.uid)
                }

                bagList.observe(viewLifecycleOwner) { list ->
                    bagRecyclerView.apply {
                        setHasFixedSize(true)
                        adapter = itemBagAdapter.also { adapter ->
                            adapter.bagupdateList(list)
                        }
                    }
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}