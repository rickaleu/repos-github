package br.com.ricardo.reposgithub.presentation.repodetail.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.ricardo.reposgithub.R
import br.com.ricardo.reposgithub.data.model.Repo
import com.bumptech.glide.Glide

class RepoDetailViewModel : ViewModel() {

    private val _repo = MutableLiveData<Repo>()
    val repo: LiveData<Repo>
    get() = _repo


    fun getRepoInfos(repo: Repo) {
        _repo.value = repo
    }


    companion object {
        @JvmStatic
        @BindingAdapter("profileImage")
        fun loadImage(image: ImageView, url: String?) {
            if (!url.isNullOrEmpty()){
                Glide.with(image.context)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.drawable.ic_placeholder)
                    .into(image)
            }
        }
    }

}