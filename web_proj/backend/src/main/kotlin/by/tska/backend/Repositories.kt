package by.tska.backend

import org.springframework.data.repository.CrudRepository

interface BillingAccountRepository : CrudRepository<BillingAccountEntity, Long> {
    fun findByWalletId(walletId: Long): BillingAccountEntity?
    fun findAllByUserProfile(userProfileEntity: UserEntity): Iterable<BillingAccountEntity>
}

interface ContentRepository : CrudRepository<ContentEntity, Long> {
    fun findByItemName(itemName: String?): ContentEntity?
    fun findAllByUserProfile(userProfile: UserEntity?): List<ContentEntity>
}

interface RolesRepository : CrudRepository<RoleEntity, Long> {
    fun findByName(name: String): RoleEntity?
}

interface SubscriptionRepository : CrudRepository<SubscriptionEntity, Long> {
    fun findByBillingAccount(billingAccount: BillingAccountEntity): List<SubscriptionEntity>
}

interface UserRepository : CrudRepository<UserEntity, Long> {
    fun findByEmail(email: String): UserEntity?
    fun findByRole(role: RoleEntity): List<UserEntity>
}
