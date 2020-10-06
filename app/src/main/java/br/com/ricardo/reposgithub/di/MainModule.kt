package br.com.ricardo.reposgithub.di

import android.content.Context
import br.com.ricardo.reposgithub.presentation.repolist.repository.RepoRepository
import br.com.ricardo.reposgithub.presentation.repolist.repository.RepoRepositoryImpl
import br.com.ricardo.reposgithub.presentation.repolist.viewmodel.RepoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    factory<RepoRepository> {
        RepoRepositoryImpl()
    }

    viewModel {
        RepoViewModel(
            repository = get()
        )
    }
}