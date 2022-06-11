package com.example.klambyshop.presentation.ui.favorite

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
import com.example.klambyshop.databinding.FragmentFavoriteBinding
import com.example.klambyshop.presentation.adapter.ListFavoriteAdapter
import com.example.klambyshop.presentation.ui.NavigationViewModel
import com.example.klambyshop.presentation.ui.detail.DetailActivity
import com.example.klambyshop.presentation.ui.favorite.factory.FavoriteModelFactory
import com.example.klambyshop.presentation.ui.favorite.insert.FavoriteAddUpdateViewModel
import com.example.klambyshop.presentation.ui.favorite.main.FavoriteViewModel
import java.util.*
import kotlin.collections.ArrayList


class FavoriteFragment : Fragment() {


    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainFavoriteViewModel: FavoriteViewModel
    private lateinit var mFavoriteAddUpdateViewModel : FavoriteAddUpdateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stateAppbar = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(
            NavigationViewModel::class.java)

        stateAppbar.updateStateNavigation("Favorite")

        mainFavoriteViewModel = obtainFavoriteViewModel(requireActivity() as AppCompatActivity)
        mFavoriteAddUpdateViewModel = obtainFavoriteAddUpdateViewModel(requireActivity() as AppCompatActivity)



        context?.let { context ->
            binding.rvFavorite.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            mainFavoriteViewModel.getAllFavorite().observe(viewLifecycleOwner) {
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

                val adapter = ListFavoriteAdapter(listKlamby,mFavoriteAddUpdateViewModel)
                binding.rvFavorite.adapter = adapter

                adapter.setOnItemClickCallback(object : ListFavoriteAdapter.OnItemClickCallback{
                    override fun onItemClicked(klamby: KlambyModel) {
                        val intentToDetail = Intent(requireActivity(),DetailActivity::class.java)
                        intentToDetail.putExtra(DetailActivity.TAG_DETAIL_KLAMBY, klamby)
                        startActivity(intentToDetail)
                    }
                })
            }


        }
    }

    private fun obtainFavoriteViewModel(activity: AppCompatActivity): FavoriteViewModel {
        val factory = FavoriteModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[FavoriteViewModel::class.java]
    }

    private fun obtainFavoriteAddUpdateViewModel(activity: AppCompatActivity): FavoriteAddUpdateViewModel {
        val factory = FavoriteModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[FavoriteAddUpdateViewModel::class.java]
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}