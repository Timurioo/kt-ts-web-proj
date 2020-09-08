package by.tska.backend

import java.util.Optional

fun <T : Any> Optional<T>.toNullable(): T? = this.orElse(null);

fun <T : Any> T?.toOptional(): Optional<T> = Optional.ofNullable(this)
