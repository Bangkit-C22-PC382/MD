package com.example.klambyshop.presentation.ui.detail

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.klambyshop.R
import com.example.klambyshop.data.db.entities.KlambyEntity
import com.example.klambyshop.data.model.KlambyModel
import com.example.klambyshop.databinding.ActivityDetailBinding
import com.example.klambyshop.presentation.ui.favorite.factory.FavoriteModelFactory
import com.example.klambyshop.presentation.ui.favorite.insert.FavoriteAddUpdateViewModel
import com.example.klambyshop.presentation.ui.favorite.main.FavoriteViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var mainFavoriteViewModel: FavoriteViewModel
    private lateinit var mFavoriteAddUpdateViewModel : FavoriteAddUpdateViewModel
    private lateinit var dataDetail: KlambyModel
    private var klambyEntity: KlambyEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        dataDetail = intent.getParcelableExtra<KlambyModel>(TAG_DETAIL_KLAMBY) as KlambyModel
        setdetail(dataDetail)

        mainFavoriteViewModel = obtainFavoriteViewModel(this)
        mFavoriteAddUpdateViewModel = obtainFavoriteAddUpdateViewModel(this)
        mainFavoriteViewModel.getKlambyById(dataDetail.id).observe(this,{
            if(it.size !=0){
                klambyEntity = it.get(0)

            }else{
                klambyEntity = null
            }
            checkIsFavorite()
        })

        binding.ivLike.setOnClickListener {
            if (klambyEntity != null) {
                // Delete from favorite user
                deleteFavorite()
            } else {
                // Add to favorite user
                addFavorite()
            }
        }


        binding.ivArrowBack.setOnClickListener {
            finish()
        }
    }

    fun setdetail(dataDetail:KlambyModel){
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


    }

    private fun obtainFavoriteAddUpdateViewModel(activity: AppCompatActivity): FavoriteAddUpdateViewModel {
        val factory = FavoriteModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(FavoriteAddUpdateViewModel::class.java)
    }

    private fun obtainFavoriteViewModel(activity: AppCompatActivity): FavoriteViewModel {
        val factory = FavoriteModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(FavoriteViewModel::class.java)
    }

    private fun checkIsFavorite() {
        if(klambyEntity != null){
            val imgInt = resources.getIdentifier("@drawable/ic_baseline_favorite_24","drawable",this.packageName )
            binding.ivLike.setImageResource(imgInt)
            binding.ivLike.setColorFilter(Color.parseColor("#48D1CC"))
        }else{
            val imgInt = resources.getIdentifier("@drawable/ic_like","drawable",this.packageName )
            binding.ivLike.setImageResource(imgInt)
            binding.ivLike.setColorFilter(Color.parseColor("#48D1CC"))

        }
    }

    private fun addFavorite(){
        val klamby = KlambyEntity(
            dataDetail.id,
            dataDetail.status,
            dataDetail.title,
            dataDetail.image_url,
            dataDetail.description,
            dataDetail.create_at,
            dataDetail.price,
            dataDetail.color,
            dataDetail.size,
            dataDetail.place,
            dataDetail.seller,
            dataDetail.seller_profile)

        klambyEntity = klamby
        mFavoriteAddUpdateViewModel.insert(klambyEntity!!)

    }

    private fun deleteFavorite(){
        mFavoriteAddUpdateViewModel.delete(klambyEntity!!)
        klambyEntity = null


    }

    companion object {
        const val TAG_DETAIL_KLAMBY ="TAG_DETAIL_KLAMBY"
    }
}