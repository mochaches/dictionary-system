package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class PageDto {
    private Long id;
    private UUID external_id;
    private Long catalog_id;
    private String name;
    private String content;
}
