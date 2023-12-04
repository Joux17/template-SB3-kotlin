package com.joux.template.domain.user.exception

import com.joux.template.domain.exception.FunctionalException
import java.util.*

class UserNotFoundException(userId: UUID) : FunctionalException("No any user foud for id \"$userId\".") {
}