package br.com.ricardo.reposgithub

import br.com.ricardo.reposgithub.data.model.OwnerInfo
import br.com.ricardo.reposgithub.data.model.Repo

object FakeRepoList {

    val repos = arrayOf(
        Repo(
            "architecture-samples",
            37455,
            10390,
            OwnerInfo(
                "android",
                "https://avatars3.githubusercontent.com/u/32689599?v=4"
            )
        ),
        Repo(
            "kotlin",
            33534,
            4185,
            OwnerInfo(
                "JetBrains",
                "https://avatars2.githubusercontent.com/u/878437?v=4"
            )
        ),
        Repo(
            "shadowsocks-android",
            30315,
            11246,
            OwnerInfo(
                "shadowsocks",
                "https://avatars1.githubusercontent.com/u/3006190?v=4"
            )
        ),
        Repo(
            "leakcanary",
            25312,
            3690,
            OwnerInfo(
                "square",
                "https://avatars0.githubusercontent.com/u/82592?v=4"
            )
        ),
        Repo(
            "p3c",
            23357,
            6095,
            OwnerInfo(
                "alibaba",
                "https://avatars1.githubusercontent.com/u/1961952?v=4"
            )
        )
    )
}
