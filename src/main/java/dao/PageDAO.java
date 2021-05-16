package dao;

import dto.PageDto;

import java.util.List;

public interface PageDAO {
    PageDto getPageById(Long id);
    List<PageDto> getAllPages();
    void updatePageById(PageDto pageDto);
    int deletePageById(Long id);
    int insertPage(PageDto pageDto);

}
