package by.tska.backend

import javax.persistence.*

@Entity
@Table(name = "user_profile", schema = "charging_service")
class UserEntity(
        @Basic @Column(name = "email") var email: String,
        @Basic @Column(name = "password") var password: String,
        @Basic @Column(name = "is_blocked") var isBlocked: Boolean? = null,
        @Basic @Column(name = "first_name") var firstName: String,
        @Basic @Column(name = "last_name") var lastName: String? = null,
        @Basic @Column(name = "address") var address: String? = null,
        @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "roles_id", referencedColumnName = "id") var role: RoleEntity,
        @Id @GeneratedValue var id: Long? = null)

@Entity
@Table(name = "roles", schema = "charging_service")
class RoleEntity(
        @Basic @Column(name = "name") var name: String,
        @Id @GeneratedValue var id: Long)

@Entity
@Table(name = "billing_account", schema = "charging_service")
class BillingAccountEntity(
        @Basic @Column(name = "funds") var funds: Float,
        @Basic @Column(name = "is_active") var isActive: Boolean? = null,
        @Basic @Column(name = "wallet_id") var walletId: Int,
        @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_profile", referencedColumnName = "id") var userProfile: UserEntity,
        @Id @GeneratedValue var id: Long? = null)

@Entity
@Table(name = "subscription", schema = "charging_service")
class SubscriptionEntity(
        var isBlocked: Boolean,
        @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "billing_account", referencedColumnName = "id") var billingAccount: BillingAccountEntity,
        @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "content", referencedColumnName = "id") var content: ContentEntity,
        @Id @GeneratedValue var id: Long? = null)

@Entity
@Table(name = "content", schema = "charging_service")
class ContentEntity(
        @Basic @Column(name = "item_name") var itemName: String? = null,
        @Basic @Column(name = "picture_url") var pictureUrl: String? = null,
        @Basic @Column(name = "description") var description: String? = null,
        @Basic @Column(name = "cost") var cost: String? = null,
        @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_profile", referencedColumnName = "id") var userProfile: UserEntity,
        @Id @GeneratedValue var id: Long? = null)