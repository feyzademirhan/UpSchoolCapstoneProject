package com.feyza.applicationecommerce.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.feyza.applicationecommerce.R
import com.feyza.applicationecommerce.databinding.FragmentSuccessBinding


class SuccessFragment : Fragment() {

    private lateinit var _successBinding: FragmentSuccessBinding
    private var successBinding : FragmentSuccessBinding
        get() = _successBinding
        set(value) {
            _successBinding = value
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        successBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_success,container,false)
        return successBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        successBinding.buttonContinueShop.setOnClickListener {
            findNavController().navigate(R.id.action_successFragment_to_shopFragment)
        }
    }


}