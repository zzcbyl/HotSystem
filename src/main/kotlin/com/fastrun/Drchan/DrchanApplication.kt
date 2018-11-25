package com.fastrun.TempCollection

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import com.fastrun.TempCollection.auth.JwtFilter
import com.fastrun.TempCollection.controller.UploadConfig
import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.context.annotation.Bean
import java.util.*


@SpringBootApplication
@ServletComponentScan
class DrchanApplication

fun main(args: Array<String>) {
    // runApplication<DrchanApplication>(*args)
     SpringApplication.run(DrchanApplication::class.java, *args)
}
