package com.feyza.applicationecommerce

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.feyza.applicationecommerce.adapter.ProductAdapter
import com.feyza.applicationecommerce.database.viewmodel.ItemViewModel
import com.feyza.applicationecommerce.databinding.FragmentHomepageBinding

class HomePageFragment : Fragment() {
    private var _homeBinding: FragmentHomepageBinding? = null
    private val homeBinding get() = _homeBinding!!

    private val viewModel by lazy { ItemViewModel(requireContext()) }
    private val productAdapter by lazy {ProductAdapter()}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_homepage,container,false)
    return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        homeBinding.recyclerView.apply {  }
    }

    private fun initObservers() {
        with(homeBinding){
            with(viewModel){
                itemList.observe(viewLifecycleOwner){list->
                    recyclerView.apply {
                        setHasFixedSize(true)
                        adapter=productAdapter.also {adapter->
                            adapter.updateList(list.takeLast(10).reversed())

                        }

                    }
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _homeBinding = null
    }


}