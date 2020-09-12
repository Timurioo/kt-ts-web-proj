package by.tska.backend

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/api/user"])
class UserProfileController(private val userService: UserService,
                            private val roleService: RoleService) {

    @PostMapping(path = ["/signup"])
    fun saveUser(@RequestBody userEntity: UserEntity) = userService.save(userEntity)

    @GetMapping(path = ["/id/{id}"])
    fun getUserById(@PathVariable(name = "id") id: Long): ResponseEntity<UserEntity> {
        return when (val user : UserEntity? = userService.findById(id).toNullable()) {
            null -> ResponseEntity<UserEntity>(null,  HttpStatus.NOT_FOUND)
            else -> ResponseEntity.ok(user)
        }
    }

    @GetMapping(path = ["/all"])
    fun getAllUsers() = userService.findAll()

    @GetMapping(path = ["/email/{email}"])
    fun getUserByEmail(@PathVariable(name = "email") email: String?): ResponseEntity<UserEntity> {
        return when (val user = userService.findByEmail(email!!)) {
            null -> ResponseEntity<UserEntity>(null, HttpStatus.NOT_FOUND)
            else -> ResponseEntity.ok(user)
        }
    }

    @GetMapping(path = ["/role/{id}"])
    fun getByRole(@PathVariable(name = "id") id: Long?): List<UserEntity> {
        val role = roleService.findById(id!!).toNullable()
        return userService.findByRole(role!!)
    }

    @PutMapping(value = ["/block/id/{id}"])
    fun blockUser(@PathVariable id: Long?): UserEntity? {
        val user = userService.findById(id!!).toNullable()
        if (user != null) {
            user.isBlocked = true
            return userService.save(user)
        }
        return null
    }

    @PutMapping(value = ["/activate/id/{id}"])
    fun activateUser(@PathVariable id: Long?): UserEntity? {
        val user = userService.findById(id!!).toNullable()
        if (user != null) {
            user.isBlocked = true
            return userService.save(user)
        }
        return null
    }
}