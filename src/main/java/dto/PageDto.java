package dto;

import java.util.UUID;

public class PageDto {
    private Long id;
    private UUID external_id;
    private Long catalog_id;
    private String name;
    private String content;


    public PageDto(Long id, Long catalog_id, String name, String content) {
        this.id = id;
        this.catalog_id = catalog_id;
        this.name = name;
        this.content = content;
    }

    public PageDto(Long catalog_id, String name, String content) {
        this.catalog_id = catalog_id;
        this.name = name;
        this.content = content;
    }

    public PageDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getExternal_id() {
        return external_id;
    }

    public void setExternal_id(UUID external_id) {
        this.external_id = external_id;
    }

    public Long getCatalog_id() {
        return catalog_id;
    }

    public void setCatalog_id(Long catalog_id) {
        this.catalog_id = catalog_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PageDto(Long id, UUID external_id, Long catalog_id, String name, String content) {
        this.id = id;
        this.external_id = external_id;
        this.catalog_id = catalog_id;
        this.name = name;
        this.content = content;
    }
}
