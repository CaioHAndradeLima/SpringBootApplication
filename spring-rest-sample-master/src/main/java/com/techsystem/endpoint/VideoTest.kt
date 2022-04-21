package com.techsystem.endpoint

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.techsystem.Application
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.InputStream
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("video")
class VideoTest {

    @GetMapping("/{hash}")
    fun getVideoTest(@PathVariable("hash") hash: String) : ResponseEntity<String> {

        val value = Application.transactionSmartContract.returnStruct(hash).send().component2()

        return ResponseEntity(value, HttpStatus.OK)
    }

    @RequestMapping(path = ["/upload"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    fun sendVideoTest(@RequestParam request: InputStream): ResponseEntity<String> {

        System.out.println("file send")

        return ResponseEntity(HttpStatus.CREATED)
    }

    @GetMapping()
    fun getVideoTest() : ResponseEntity<String> {
        System.out.println("file send")
        return ResponseEntity("teste feito com sucesso", HttpStatus.OK)
    }
}
