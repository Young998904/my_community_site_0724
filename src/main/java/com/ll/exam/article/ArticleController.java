package com.ll.exam.article;

import com.ll.exam.Rq;
import com.ll.exam.article.dto.ArticleDto;

import java.util.List;

public class ArticleController {
    private AtricleService articleService;

    public ArticleController () {
        articleService = new AtricleService();
    }
    public void showList (Rq rq) {
        // Dto -> 단순 데이터를 담는곳
        List<ArticleDto> articleDtos = articleService.findAll();

        rq.setAtrr("articles", articleDtos);
        rq.view("usr/article/list");
    }

    public void showWrite(Rq rq) {
        rq.view("usr/article/write");
    }

    public void doWrite(Rq rq) {
        String title = rq.getParam("title", "");
        String body = rq.getParam("body", "");

        long id = articleService.write(title, body);

        rq.appendBody("%d번 게시물이 생성되었습니다.".formatted(id));
    }

    public void showDetail(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);

        if (id == 0) {
            rq.appendBody("번호를 입력해주세요.");
            return;
        }

        ArticleDto articleDto = articleService.findById(id);

        if (articleDto == null) {
            rq.appendBody("해당 글이 존재하지 않습니다.");
            return;
        }

        rq.setAtrr("article", articleDto);
        rq.view("usr/article/detail");
    }

    public void doDelete(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);

        if (id == 0) {
            rq.appendBody("번호를 입력해주세요.");
            return;
        }

        ArticleDto articleDto = articleService.findById(id);

        if (articleDto == null) {
            rq.appendBody("해당 글이 존재하지 않습니다.");
            return;
        }

        articleService.delete(id);

        rq.appendBody("<div>%d번 게시물이 삭제되었습니다.</div>".formatted(id));
        rq.appendBody("<div><a href=\"/usr/article/list/free\">리스트로 이동</a></div>".formatted(id));
    }
}
