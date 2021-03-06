package com.every.every_server.domain.vo.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/*
 * 일정 수정 VO
 */
@Getter
public class ScheduleModifyVO {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @JsonProperty("start_date")
    @NotNull
    private LocalDate startDate;

    @JsonProperty("end_date")
    @NotNull
    private LocalDate endDate;
}
