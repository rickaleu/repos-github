package br.com.ricardo.reposgithub.presentation.repodetail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.renderscript.Sampler
import androidx.appcompat.app.AppCompatActivity
import br.com.ricardo.reposgithub.R
import kotlinx.android.synthetic.main.activity_repo_detail.*

class RepoDetailActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_ID = "id"
        private const val EXTRA_NAME = "name"

        fun getStartIntent(context: Context) : Intent {
            return Intent(context, RepoDetailActivity::class.java).apply {
//                putExtra(EXTRA_ID, id)
//                putExtra(EXTRA_NAME, name)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_detail)

        val actionbar = supportActionBar
        actionbar!!.title = intent.getStringExtra(EXTRA_NAME)

        actionbar.setDisplayHomeAsUpEnabled(true)

        git_detail_text_id.text = "teste1" //intent.getCharSequenceExtra(EXTRA_ID)
        git_detail_text_name.text = "teste2" //intent.getStringExtra(EXTRA_NAME)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}