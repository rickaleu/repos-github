package br.com.ricardo.reposgithub.presentation.repolist.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.ricardo.reposgithub.R
import br.com.ricardo.reposgithub.data.model.Repo
import kotlinx.android.synthetic.main.item_git.view.*

class RepoListAdapter(private val repoList: List<Repo>,
                      private val onClickListener: ((repo: Repo) -> Unit))
    : RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_git, parent, false)
        return RepoViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bindView(repoList[position])
    }

    override fun getItemCount() = repoList.count()

    class RepoViewHolder(private val itemView: View, private val onClickListener: (repo: Repo) -> Unit)
        : RecyclerView.ViewHolder(itemView) {

        private val id = itemView.item_text_id
        private val name = itemView.item_text_name

        fun bindView(repo: Repo) {

            name.text = repo.name

            itemView.setOnClickListener {
                onClickListener.invoke(repo)
            }
        }
    }
}