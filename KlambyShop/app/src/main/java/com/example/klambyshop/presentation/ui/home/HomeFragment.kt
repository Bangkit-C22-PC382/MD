package com.example.klambyshop.presentation.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
//import androidx.lifecycle.ViewModelProvider
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.klambyshop.R
import com.example.klambyshop.data.model.KlambyModel
import com.example.klambyshop.data.util.DataKlamby
import com.example.klambyshop.databinding.FragmentHomeBinding
import com.example.klambyshop.presentation.adapter.ListKlambyAdapter
import com.example.klambyshop.presentation.ui.NavigationViewModel
import com.example.klambyshop.presentation.ui.detail.DetailActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stateAppbar = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(
            NavigationViewModel::class.java)

        stateAppbar.updateStateNavigation("Home")

        val imageSlider:ImageSlider = binding.slider
        val slideModel :List<SlideModel> = listOf(SlideModel(R.drawable.slide_1),SlideModel(R.drawable.slide_2),SlideModel(R.drawable.slide_3))
        imageSlider.setImageList(slideModel,ScaleTypes.CENTER_CROP)

        context?.let {
            binding.rvListBaju.layoutManager = GridLayoutManager(it, 2)
            val adapter = ListKlambyAdapter(DataKlamby.DATA_LIST_KLAMBY)
            binding.rvListBaju.adapter = adapter

            adapter.setOnItemClickCallback(object : ListKlambyAdapter.OnItemClickCallback{
                override fun onItemClicked(klamby: KlambyModel) {
                    val intentToDetail = Intent(requireActivity(),DetailActivity::class.java)
                    intentToDetail.putExtra(DetailActivity.TAG_DETAIL_KLAMBY, klamby)
                    startActivity(intentToDetail)

                }
            })
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}