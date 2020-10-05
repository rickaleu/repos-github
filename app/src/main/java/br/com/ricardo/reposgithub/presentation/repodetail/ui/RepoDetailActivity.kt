package br.com.ricardo.reposgithub.presentation.repodetail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.ricardo.reposgithub.R
import br.com.ricardo.reposgithub.data.model.Repo
import br.com.ricardo.reposgithub.databinding.ActivityRepoDetailBinding
import br.com.ricardo.reposgithub.presentation.repodetail.viewmodel.RepoDetailViewModel

class RepoDetailActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_REPO = "repo"

        fun getStartIntent(context: Context, repo: Repo): Intent {
            return Intent(context, RepoDetailActivity::class.java).apply {
                putExtra(EXTRA_REPO, repo)
            }
        }
    }

    private lateinit var repoObject: Repo
    private val viewModel: RepoDetailViewModel by viewModels()
    private val binding: ActivityRepoDetailBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_repo_detail)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repoObject = intent.getSerializableExtra(EXTRA_REPO) as Repo

        val actionbar = supportActionBar
        actionbar!!.title = repoObject.name
        actionbar.setDisplayHomeAsUpEnabled(true)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
    }

    override fun onResume() {
        super.onResume()
        viewModel.getRepoInfos(repoObject)
        RepoDetailViewModel.loadImage(binding.gitDetailImageAvatar, repoObject.owner.avatar)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}