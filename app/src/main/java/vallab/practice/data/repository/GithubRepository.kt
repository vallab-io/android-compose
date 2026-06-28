package vallab.practice.data.repository

import vallab.practice.data.model.RepositoryEntity

interface GithubRepository {
    suspend fun getRepositories(organization: String): List<RepositoryEntity>
}