package com.example.klambyshop.presentation.ui.detail

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.klambyshop.R
import com.example.klambyshop.data.db.entities.CartEntity
import com.example.klambyshop.data.db.entities.KlambyEntity
import com.example.klambyshop.data.model.KlambyModel
import com.example.klambyshop.databinding.ActivityDetailBinding
import com.example.klambyshop.presentation.ui.cart.factory.CartModelFactory
import com.example.klambyshop.presentation.ui.favorite.factory.FavoriteModelFactory
import com.example.klambyshop.presentation.ui.cart.insert.CartAddUpdateViewModel
import com.example.klambyshop.presentation.ui.favorite.insert.FavoriteAddUpdateViewModel
import com.example.klambyshop.presentation.ui.cart.main.CartViewModel
import com.example.klambyshop.presentation.ui.favorite.main.FavoriteViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var mainFavoriteViewModel: FavoriteViewModel
    private lateinit var mFavoriteAddUpdateViewModel : FavoriteAddUpdateViewModel
    private lateinit var mainCartViewModel: CartViewModel
    private lateinit var mCartAddUpdateViewModel : CartAddUpdateViewModel
    private lateinit var dataDetail: KlambyModel
    private var klambyEntity: KlambyEntity? = null
    private  var cartEntity: CartEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        dataDetail = intent.getParcelableExtra<KlambyModel>(TAG_DETAIL_KLAMBY) as KlambyModel
        setDetail(dataDetail)

        mainFavoriteViewModel = obtainFavoriteViewModel(this)
        mFavoriteAddUpdateViewModel = obtainFavoriteAddUpdateViewModel(this)
        mainCartViewModel = obtainCartViewModel(this)
        mCartAddUpdateViewModel = obtainCartAddUpdateViewModel(this)

        mainFavoriteViewModel.getKlambyById(dataDetail.id).observe(this) {
            klambyEntity = if (it.isNotEmpty()) {
                it[0]

            } else {
                null
            }
            checkIsFavorite()

        }

        mainCartViewModel.getKlambyById(dataDetail.id).observe(this){
            cartEntity = if(it.isNotEmpty()){
                it[0]
            }else{
                null
            }
            checkIsCart()
        }

        binding.ivCart.setOnClickListener {
            if(cartEntity != null){
                deleteCart()
            }else{
                addCart()
            }
        }
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

    fun setDetail(dataDetail:KlambyModel){
        binding.descriptionDetail.text = dataDetail.description
        binding.textTitle.text = dataDetail.title
        binding.textLocation.text = dataDetail.place
        binding.textPrice.text = dataDetail.price
        if(!dataDetail.status){
            binding.textInstock.text = getString(R.string.instock)
        }else{
            binding.textInstock.text = getString(R.string.soldout)
        }
        binding.textSize.text = dataDetail.size


    }

    private fun obtainFavoriteAddUpdateViewModel(activity: AppCompatActivity): FavoriteAddUpdateViewModel {
        val factory = FavoriteModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[FavoriteAddUpdateViewModel::class.java]
    }

    private fun obtainFavoriteViewModel(activity: AppCompatActivity): FavoriteViewModel {
        val factory = FavoriteModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[FavoriteViewModel::class.java]
    }

    private fun obtainCartAddUpdateViewModel(activity: AppCompatActivity): CartAddUpdateViewModel {
        val factory = CartModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[CartAddUpdateViewModel::class.java]
    }

    private fun obtainCartViewModel(activity: AppCompatActivity): CartViewModel {
        val factory = CartModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[CartViewModel::class.java]
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
    private fun checkIsCart() {
        if(cartEntity != null){
            val imgInt = resources.getIdentifier("@drawable/ic_baseline_remove_shopping_cart_24","drawable",this.packageName )
            binding.ivCart.setImageResource(imgInt)
            binding.ivCart.setColorFilter(Color.parseColor("#D14848"))
        }else{
            val imgInt = resources.getIdentifier("@drawable/ic_cart","drawable",this.packageName )
            binding.ivCart.setImageResource(imgInt)
            binding.ivCart.setColorFilter(Color.parseColor("#48D1CC"))

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

    private fun deleteCart(){
        mCartAddUpdateViewModel.delete(cartEntity!!)
        cartEntity = null

    }
    private fun addCart(){
        val klamby = CartEntity(
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

        cartEntity = klamby
        mCartAddUpdateViewModel.insert(cartEntity!!)

    }



    companion object {
        const val TAG_DETAIL_KLAMBY ="TAG_DETAIL_KLAMBY"
    }
}