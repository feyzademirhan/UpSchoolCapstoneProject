package com.feyza.applicationecommerce.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.feyza.applicationecommerce.R
import com.feyza.applicationecommerce.adapter.CategoryAdapter
import com.feyza.applicationecommerce.database.viewmodel.CategoryViewModel
import com.feyza.applicationecommerce.databinding.FragmentShopBinding


class ShopFragment : Fragment() {
    private var _shopBinding: FragmentShopBinding? = null
    private val shopBinding get() = _shopBinding!!
    private val adapterCategory by lazy { CategoryAdapter() }
    private val viewModel by lazy { CategoryViewModel(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _shopBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_shop,container,false)
        return shopBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(shopBinding){
            with(viewModel){
                categoryList.observe(viewLifecycleOwner){list->
                    recyclerCategory.apply {
                        setHasFixedSize(true)
                        adapter=adapterCategory.also { adapter->
                            adapter.updateList(list)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _shopBinding = null
    }

}