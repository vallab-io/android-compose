package vallab.practice.data.service

import retrofit2.http.GET
import retrofit2.http.Path
import vallab.practice.data.model.RepositoryEntity


interface GithubService{

    @GET("orgs/{organization}/repos")
    suspend fun getRepositories(@Path("organization") organization: String): List<RepositoryEntity>
}
