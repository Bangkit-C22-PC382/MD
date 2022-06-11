package com.example.klambyshop.presentation.ui.cart

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.klambyshop.data.model.KlambyModel
import com.example.klambyshop.databinding.FragmentCartBinding
import com.example.klambyshop.presentation.adapter.ListCartAdapter
import com.example.klambyshop.presentation.ui.NavigationViewModel
import com.example.klambyshop.presentation.ui.cart.factory.CartModelFactory
import com.example.klambyshop.presentation.ui.cart.insert.CartAddUpdateViewModel
import com.example.klambyshop.presentation.ui.cart.main.CartViewModel
import com.example.klambyshop.presentation.ui.detail.DetailActivity


class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainCartViewModel: CartViewModel
    private lateinit var mCartAddUpdateViewModel : CartAddUpdateViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCartBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stateAppbar = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(
            NavigationViewModel::class.java)

        stateAppbar.updateStateNavigation("Cart")

        mainCartViewModel = obtainCartViewModel(requireActivity() as AppCompatActivity)
        mCartAddUpdateViewModel = obtainCartAddUpdateViewModel(requireActivity() as AppCompatActivity)

        context?.let { context ->
            binding.rvCart.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            mainCartViewModel.getAllCart().observe(viewLifecycleOwner) {
                val listKlamby: MutableList<KlambyModel> = mutableListOf()

                it.forEach { unit ->
                    val dataKlamby =
                        KlambyModel(
                            unit.id,
                            unit.status,
                            unit.title,
                            unit.image_url,
                            unit.description,
                            unit.create_at,
                            unit.price,
                            unit.color,
                            unit.size,
                            unit.place,
                            unit.seller,
                            unit.seller_profile
                        )
                    listKlamby.add(dataKlamby)

                }

                val adapter = ListCartAdapter(listKlamby,mCartAddUpdateViewModel)
                binding.rvCart.adapter = adapter

                adapter.setOnItemClickCallback(object : ListCartAdapter.OnItemClickCallback{
                    override fun onItemClicked(klamby: KlambyModel) {
                        val intentToDetail = Intent(requireActivity(), DetailActivity::class.java)
                        intentToDetail.putExtra(DetailActivity.TAG_DETAIL_KLAMBY, klamby)
                        startActivity(intentToDetail)
                    }
                })
            }


        }


    }
    private fun obtainCartViewModel(activity: AppCompatActivity): CartViewModel {
        val factory = CartModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[CartViewModel::class.java]
    }

    private fun obtainCartAddUpdateViewModel(activity: AppCompatActivity): CartAddUpdateViewModel {
        val factory = CartModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[CartAddUpdateViewModel::class.java]
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}