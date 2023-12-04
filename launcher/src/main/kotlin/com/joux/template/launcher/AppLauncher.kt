package com.joux.template.launcher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AppLauncher

fun main(args: Array<String>) {
    runApplication<AppLauncher>(*args)
}
