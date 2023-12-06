package com.joux.template.launcher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "com.joux.template.exposition",
        "com.joux.template.application",
        "com.joux.template.infrastructure"
    ]
)
class AppLauncher

fun main(args: Array<String>) {
    runApplication<AppLauncher>(*args)
}
