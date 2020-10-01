package br.com.ricardo.reposgithub.presentation.repolist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.ricardo.reposgithub.R
import br.com.ricardo.reposgithub.data.model.Repo
import br.com.ricardo.reposgithub.presentation.repolist.repository.RepoResitoryImpl
import br.com.ricardo.reposgithub.presentation.repolist.ui.adapter.RepoListAdapter
import br.com.ricardo.reposgithub.presentation.repolist.viewmodel.RepoViewModel
import kotlinx.android.synthetic.main.activity_repo_list.*

class RepoListActivity : AppCompatActivity() {


    private val viewModel : RepoViewModel by lazy {
        RepoViewModel.ViewModelFactory(RepoResitoryImpl()).create(RepoViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)


        viewModel.repoList.observe(this, Observer {
            it?.let { repo ->
                with(git_recycler){
                    layoutManager = LinearLayoutManager(this@RepoListActivity, LinearLayoutManager.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = RepoListAdapter(repo)
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
    }

    override fun onResume() {
        super.onResume()
        viewModel.getRepoList()
    }
}