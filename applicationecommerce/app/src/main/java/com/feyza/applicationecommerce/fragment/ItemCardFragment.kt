package com.feyza.applicationecommerce.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.feyza.applicationecommerce.R
import com.feyza.applicationecommerce.database.viewmodel.ItemViewModel
import com.feyza.applicationecommerce.databinding.FragmentItemcardBinding
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class ItemCardFragment : Fragment() {
    private lateinit var itemCardBinding: FragmentItemcardBinding
    private val args: ItemCardFragmentArgs by navArgs()

    private val viewModel by lazy { ItemViewModel(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        itemCardBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_itemcard, container, false)
        return itemCardBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = args.itemModel

        itemCardBinding.apply {
            itemModel = product
            Picasso.get().load(product.image).into(imageView2)

            addToCartButton.setOnClickListener {
                FirebaseAuth.getInstance().currentUser?.let { user ->
                    viewModel.addToBag(
                        user.uid,
                        product.title,
                        product.price,
                        product.description,
                        product.category,
                        product.image,
                        product.rate,
                        product.count,
                        product.sale_state
                    )
                }

            }

            backButton.setOnClickListener {
                findNavController().navigate(R.id.action_itemCardFragment_to_homePageFragment)
            }
        }
    }
}