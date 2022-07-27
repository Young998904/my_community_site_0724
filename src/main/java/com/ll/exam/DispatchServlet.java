package com.ll.exam;

import com.ll.exam.article.ArticleController;
import com.ll.exam.member.MemberController;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/usr/*")
public class DispatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Rq rq = new Rq(req, resp);

        MemberController memberController = new MemberController();
        ArticleController articleController = new ArticleController();

//        // getRequsetURI 는 paramString 만 가지고 온다
//        String url = req.getRequestURI();

        switch (rq.getMethod()) {
            case "GET":
                switch (rq.getActionPath()) {
                    case "/usr/article/modify":
                        articleController.showModify(rq);
                        break;
                    case "/usr/article/detail" :
                        articleController.showDetail(rq);
                        break;
                    case "/usr/article/delete":
                        articleController.doDelete(rq);
                        break;
                    case "/usr/article/list":
                        articleController.showList(rq);
                        break;
                    case "/usr/article/write":
                        articleController.showWrite(rq);
                        break;
                    case "/usr/member/login":
                        memberController.showLogin(rq);
                        break;
                }
                break;
            case "POST":
                switch (rq.getActionPath()) {
                    case "/usr/article/write":
                        articleController.doWrite(rq);
                        break;
                    case "/usr/article/modify":
                        articleController.doModify(rq);
                        break;
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }
}