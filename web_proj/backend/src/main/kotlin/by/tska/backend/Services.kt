package by.tska.backend

import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun findAll() = userRepository.findAll()

    fun findByEmail(email: String) = userRepository.findByEmail(email)

    fun findById(id: Long) = userRepository.findById(id)

    fun save(user: UserEntity): UserEntity? {
        val userForComparison = userRepository.findByEmail(user.email) != null
        return if (user.id != null || !userForComparison) {
            userRepository.save(user)
        } else null
    }

    fun delete(id: Long) = userRepository.deleteById(id)

    fun findByRole(role: RoleEntity) = userRepository.findByRole(role)
}

@Service
class RoleService(private val rolesRepository: RolesRepository) {
    fun findById(id: Long) = rolesRepository.findById(id)
    fun getAllRoles(): List<RoleEntity> = rolesRepository.findAll().toList()
}

