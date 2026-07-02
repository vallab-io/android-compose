package vallab.practice.data.mapper

import vallab.practice.data.model.RepositoryEntity
import vallab.practice.domain.Repository

fun RepositoryEntity.toDomain(): Repository {
    return Repository(
        fullName = fullName.orEmpty(),
        description = description.orEmpty(),
        stars = stars ?: 0
    )
}
