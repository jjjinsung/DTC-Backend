package hanium.dtc.travel.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record TravelTitleRequest (
        String title
) {
}
