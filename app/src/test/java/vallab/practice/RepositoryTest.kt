package vallab.practice

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import vallab.practice.domain.Repository

class RepositoryTest {

    @Test
    fun `star의_갯수가_50개_미만이면_isHot함수_false_반환`() {
        val repository = Repository(
            fullName = "홍길동",
            description = "홍길동입니다",
            stars = 10
        )

        assertFalse(repository.isHot())
    }

    @Test
    fun `star의_갯수가_50개_이상이면_isHot함수_true_반환`() {
        val repository = Repository(
            fullName = "홍길동",
            description = "홍길동입니다",
            stars = 51
        )

        assertTrue(repository.isHot())
    }
}