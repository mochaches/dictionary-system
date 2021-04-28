package dao;

import dto.PageDto;

public interface PageDAO {
    PageDto getPageById(Long id);
    void updatePageById(PageDto pageDto);
    void deletePageById(Long id);
}
