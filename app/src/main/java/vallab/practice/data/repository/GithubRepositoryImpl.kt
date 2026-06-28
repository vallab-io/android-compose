package vallab.practice.data.repository

import vallab.practice.data.model.RepositoryEntity
import vallab.practice.data.service.GithubService

class GithubRepositoryImpl(private val githubService: GithubService) : GithubRepository {
    override suspend fun getRepositories(organization: String): List<RepositoryEntity> {
        return githubService.getRepositories(organization)
    }
}