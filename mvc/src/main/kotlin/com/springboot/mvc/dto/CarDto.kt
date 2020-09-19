package com.springboot.mvc.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.springboot.mvc.annotation.StringDateTimeFormat
import io.swagger.annotations.ApiModelProperty
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.validation.constraints.*

data class CarDto(

        // 이름은 반드시 있어야 하고 공백 불가
        @field:ApiModelProperty(
                value = "이름",
                example = "홍길동"
        )
        @field:NotNull
        @field:NotBlank
        var name : String?=null,

        // 번호의 길이는 2~4
        @field:Min(value = 4)
        var number : Int?=null,

        // 전화번호 정규식
        @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$")
        var phoneNumber : String?=null,

        // 등록 일자는 과거
        @field:Past
        var registerAt : LocalDateTime?=null,

        // 만료일자는 미래이거나 오늘
        @field:FutureOrPresent(message = "만료일자는 오늘보다 미래여야 합니다.")
        var expireAt : LocalDateTime?=null,

        // 거리는 0이거나 양수
        // 최대 거리는 30000
        @field:PositiveOrZero
        @field:Max(value = 30000, message = "0~30000 이내만 가능 합니다. ")
        var distance : Int?=null,

        @field:StringDateTimeFormat(pattern = "yyyyMMddHHmmss", message = "시간 형식이 맞지 않습니다. ")
        var inspectionDateTime : String?=null
){

        @AssertTrue.List(value = [
                AssertTrue(message = "Number의 값은 1000 이상 입니다"),
                AssertTrue(message = "Number의 값은 5000 이하 입니다")
                ]
        )
        private fun isValidNumber() : Boolean{
                return this.number?:0 in 1001..4999
        }
}