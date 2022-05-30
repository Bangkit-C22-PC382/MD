package com.example.klambyshop.presentation.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.klambyshop.R
import com.example.klambyshop.data.model.KlambyModel
import com.example.klambyshop.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val dataDetail = intent.getParcelableExtra<KlambyModel>(TAG_DETAIL_KLAMBY) as KlambyModel

        binding.descriptionDetail.text = dataDetail.description
        binding.textTitle.text = dataDetail.title
        binding.textLocation.text = dataDetail.place
        binding.textPrice.text = dataDetail.price
        if(!dataDetail.status){
            binding.textInstock.text = "in Stock"
        }else{
            binding.textInstock.text = "Sold Out"
        }
        binding.textSize.text = dataDetail.size

        binding.ivArrowBack.setOnClickListener {
            finish()
        }

    }

    companion object {
        const val TAG_DETAIL_KLAMBY ="TAG_DETAIL_KLAMBY"
    }
}