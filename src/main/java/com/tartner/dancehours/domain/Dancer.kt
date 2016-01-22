package com.tartner.dancehours.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.GenericGenerator
import org.hibernate.validator.constraints.Email
import org.springframework.data.elasticsearch.annotations.Document
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "dancers")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "dancers")
open class Dancer {
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "UUID")
    @Id
    private var id: UUID = UUID.fromString("")

    @NotNull
    @Size(min = 1, max = 100)
    @Column(length = 100, unique = true, nullable = false)
    private var login: String? = null

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60)
    private var password: String? = null

    @Size(max = 250)
    @Column(name = "full_name", length = 250)
    private var fullName: String? = null

    @Email
    @Size(max = 100)
    @Column(length = 100, unique = true)
    private var email: String? = null

    @Column(nullable = false)
    private var activated = false


}
