package by.tska.backend

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping(path = ["/api/user"])
class UserProfileController(private val userService: UserService,
                            private val rolesService: RolesService) {

    @PostMapping(path = ["/signup"])
    fun saveUser(@RequestBody userEntity: UserEntity) = userService.save(userEntity)

    @GetMapping(path = ["id/{id}"])
    fun getUserById(@PathVariable(name = "id") id: Long): ResponseEntity<UserEntity> {
        val user = userService.findById(id).toNullable()
        return if (user != null) ResponseEntity.ok(user)
               else ResponseEntity.badRequest().body(Error("This user does not exist"))
                                    as ResponseEntity<UserEntity>
    }

    @GetMapping(path = ["/all"])
    fun getAllUsers() = userService.findAll()

    @GetMapping(path = ["/email/{email}"])
    fun getUserByEmail(@PathVariable(name = "email") email: String?): ResponseEntity<UserProfileEntity>? {
        val user = userService.findByEmail(email!!)
        return user.map<Any?>(ResponseEntity::ok).orElse(null)
    }

    @GetMapping(path = ["/role/{id}"])
    fun getByRole(@PathVariable(name = "id") id: Long?): List<UserProfileEntity>? {
        val role: Optional<RoleEntity> = rolesService.findById(id)
        return role.map<Any?> { roleEntity: RoleEntity? -> userService.findByRole(roleEntity) }.orElse(null)
    }

    @PutMapping(value = ["/block/id/{id}"])
    fun blockUser(@PathVariable id: Long?): UserProfileEntity {
        val user: Optional<UserProfileEntity> = userService.findById(id)
        val entity: UserProfileEntity = user.get()
        entity.setIsBlocked(1.toByte())
        return userService.save(entity)
    }

    @PutMapping(value = ["/activate/id/{id}"])
    fun activateUser(@PathVariable id: Long?): UserProfileEntity {
        val user: Optional<UserProfileEntity> = userService.findById(id)
        val entity: UserProfileEntity = user.get()
        entity.setIsBlocked(0.toByte())
        return userService.save(entity)
    }
}