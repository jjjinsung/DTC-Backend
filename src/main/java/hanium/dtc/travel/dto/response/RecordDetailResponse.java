package hanium.dtc.travel.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record RecordDetailResponse (
        @JsonProperty("title")
        String title,

        @JsonProperty("thema")
        String thema,

        @JsonProperty("detail_address")
        String detailAddress,

        @JsonProperty("start_at")
        LocalDateTime startAt,

        @JsonProperty("end_at")
        LocalDateTime endAt
) {
}
