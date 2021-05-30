package service;

import dao.PageDAO;
import dto.PageDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PageServiceImpl implements PageService {
    private final PageDAO pageDAO;

    @Override
    public PageDto getPageById(Long id) {
        return pageDAO.getPageById(id);
    }

    @Override
    public List<PageDto> getAllPages() {
        return pageDAO.getAllPages();
    }

    @Override
    public void updatePageById(PageDto pageDto) {
        pageDAO.updatePageById(pageDto);
    }

    @Override
    public int deletePageById(Long id) {
        return pageDAO.deletePageById(id);
    }

    @Override
    public int insertPage(PageDto pageDto) {
        return pageDAO.insertPage(pageDto);
    }
}
