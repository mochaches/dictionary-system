package controller;

import dao.PageDAOImpl;

public class PageController {
    public static void main(String[] args) {
        /**
         * Проверяю добавлене строки в БД, создаю страничку по конструктору и передаю ее в метод
         */
//        PageDto pageDto = new PageDto(2L,"test2", "content");
//        new PageDAOImpl().insertPage(pageDto);

        /**
         * Возвращаю все существующие страницы
         */
//        List<PageDto> allPages = new PageDAOImpl().getAllPages();

        /**
         * Удаление строк из базы по ID
         */
//        new PageDAOImpl().deletePageById(3L);

        /**
         * Обновление строки. Метод принимает dto, чтобы не передавать id и новую dto.
         * Если для теста создать dto со своим id,
         * то нет гарантии что в БД id будет совпадать, потому что там он автогенерируется.
         * Поэтому мы сперва получаем все страницы(getAllPages), затем начинаем перебирать элементы(stream),
         * и отфильтровываем(filter) по условию: page = одна страница. У каждой страницы получаем Name и сравниваем его
         * с "UpdateTest"(любое имя, которое есть в базе), берем первое совпадающее значение(firstFind) и возвращаем его dto
         * Меняем в итоговой dto контент(new content) и вызываем наш метод
         */
//        PageDAOImpl pageDAO = new PageDAOImpl();
//        var dto = pageDAO.getAllPages().stream().filter(page -> page.getName().equals("UpdateTest")).findFirst().get();
//        dto.setContent("new content");
//        pageDAO.updatePageById(dto);
//        System.out.println(dto);
    }
}
