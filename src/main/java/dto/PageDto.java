package dto;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PageDto {
    private Long id;
    private UUID external_id;
    private Long catalog_id;
    private String name;
    private String content;
}
