package com.company.confinance.model.response

import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class PasswordResetValidationService {
    private val validationResults: MutableMap<String, Boolean> = ConcurrentHashMap()

    fun setValidationResult(email: String, isValid: Boolean) {
        validationResults[email] = isValid
    }

    fun getValidationResult(email: String): Boolean? {
        return validationResults[email]
    }
}
