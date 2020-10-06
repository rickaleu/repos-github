package br.com.ricardo.reposgithub.presentation.repolist.ui

import android.os.Bundle
import android.view.View.INVISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.ricardo.reposgithub.R
import br.com.ricardo.reposgithub.presentation.repodetail.ui.RepoDetailActivity
import br.com.ricardo.reposgithub.presentation.repolist.ui.adapter.RepoListAdapter
import br.com.ricardo.reposgithub.presentation.repolist.viewmodel.RepoViewModel
import br.com.ricardo.reposgithub.utils.ConnectionUtils
import br.com.ricardo.reposgithub.utils.RepoGitHubConstants
import kotlinx.android.synthetic.main.activity_repo_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoListActivity : AppCompatActivity() {

    private val viewModel: RepoViewModel by viewModel()

    private val connectionUtils = ConnectionUtils(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)

        if (!connectionUtils.isConnectionAvailable(this)) {
            git_view_flipper.visibility = INVISIBLE
            Toast.makeText(this, R.string.repo_no_network, Toast.LENGTH_SHORT).show()
        } else {
            viewModel.getRepoList(RepoGitHubConstants.GET_FIELD_3)
        }

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

                viewFlipper.second?.let { errorMessage ->
                    git_text_error.text = getString(errorMessage)
                }
            }
        })

//        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        git_recycler.addOnScrollListener(
//            InfiniteScrollListener({
//                viewModel.getRepoList(1)
//            }, layoutManager)
//        )
    }
}