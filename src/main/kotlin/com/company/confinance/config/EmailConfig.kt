package com.company.confinance.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl

@Configuration
class EmailConfig {

    @Value("\${spring.mail.host}")
    private val host: String = ""

    @Value("\${spring.mail.port}")
    private val port: Int = 0

    @Value("\${spring.mail.username}")
    private val username: String = ""

    @Value("\${spring.mail.password}")
    private val password: String = ""

    @Bean
    fun javaMailSender(): JavaMailSender {
        val mailSender = JavaMailSenderImpl()
        mailSender.javaMailProperties["mail.smtp.starttls.enable"] = "true"
        mailSender.javaMailProperties["mail.smtp.ssl.trust"] = "smtp.gmail.com"
        mailSender.host = host
        mailSender.port = port
        mailSender.username = username
        mailSender.password = password

        return mailSender
    }

    fun generateRandomCode(length: Int): String {
        val characters = "0123456789"
        val random = java.util.Random()
        val code = StringBuilder()

        for (i in 0 until length) {
            val index = random.nextInt(characters.length)
            code.append(characters[index])
        }

        return code.toString()
    }
    fun getFromAddress(): String {
        return username
    }
}