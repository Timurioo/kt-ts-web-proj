package by.tska.backend

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoriesTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val userRepository: UserRepository,
        val rolesRepository: RolesRepository) {

    @Test
    fun `When findByIdOrNull then return User`() {
        val user = UserEntity(email = "test@test.com",
                password = "test",
                firstName = "test",
                role = rolesRepository.findById(2).get())
        entityManager.persist(user)
        entityManager.flush()
        val foundUser = userRepository.findByIdOrNull(user.id!!)

        assertThat(foundUser).isEqualTo(user)
    }
}