package br.com.ricardo.reposgithub.presentation.repolist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AbsListView
import android.widget.AbsListView.OnScrollListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.ricardo.reposgithub.R
import br.com.ricardo.reposgithub.data.model.Repo
import br.com.ricardo.reposgithub.presentation.repodetail.ui.RepoDetailActivity
import br.com.ricardo.reposgithub.presentation.repolist.repository.RepoResitoryImpl
import br.com.ricardo.reposgithub.presentation.repolist.ui.adapter.RepoListAdapter
import br.com.ricardo.reposgithub.presentation.repolist.viewmodel.RepoViewModel
import br.com.ricardo.reposgithub.utils.RepoGitHubConstants
import kotlinx.android.synthetic.main.activity_repo_list.*

class RepoListActivity : AppCompatActivity() {


    private val viewModel: RepoViewModel by lazy {
        RepoViewModel.ViewModelFactory(RepoResitoryImpl()).create(RepoViewModel::class.java)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)

        viewModel.repoList.observe(this, Observer {
            it?.let { repo ->
                with(git_recycler) {
                    layoutManager = LinearLayoutManager(
                        this@RepoListActivity,
                        LinearLayoutManager.VERTICAL, false
                    )
                    setHasFixedSize(true)
                    adapter = RepoListAdapter(repo) { repo ->
                        this@RepoListActivity.startActivity(
                            RepoDetailActivity.getStartIntent(this@RepoListActivity, repo)
                        )
                    }
                }
            }
        })

        viewModel.viewFlipper.observe(this, Observer {
            it?.let { viewFlipper ->
                git_view_flipper.displayedChild = viewFlipper.first

                viewFlipper?.let { errorMessage ->
                    git_text_error.text = errorMessage.toString()
                }
            }
        })

//        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//
//        git_recycler.addOnScrollListener(
//            InfiniteScrollListener({
//                viewModel.getRepoList(1)
//            }, layoutManager)
//        )
    }

    override fun onResume() {
        super.onResume()
        viewModel.getRepoList(RepoGitHubConstants.GET_FIELD_3)
    }
}