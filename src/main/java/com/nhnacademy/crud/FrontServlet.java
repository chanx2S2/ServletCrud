package com.nhnacademy.crud;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.nhnacademy.crud.RequestDispatcher.*;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {
    private static final String REDIRECT_PREFIX="redirect";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 공통 처리 - 응답 content-type, character encoding 지정
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try {
            // 실제 요청 처리할 servlet을 결정
            String servletPath = resolveServlet(req.getServletPath());
            RequestDispatcher rd = req.getRequestDispatcher(servletPath);
            rd.include(req, resp);

            // 실제 요청을 처리한 servlet이 'view'라는 request 속성값으로 view를 전달해 줌.
            String view = (String) req.getAttribute("view");
            if (view.startsWith(REDIRECT_PREFIX)) {
                log.error("redirect-url : {}", view.substring(REDIRECT_PREFIX.length()+1));
                // 'redirect:'로 시작하면 redirect 처리
                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length()+1));

            } else {
                // redirect 아니면 JSP에게 view 처리를 위임하여 그 결과를 include시킴
                rd.include(req, resp);

            }

        } catch (Exception e) {
            // 공통 error 처리 - ErrorServlet 참고해서 처리
            req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
            req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
            req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
            req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));
            req.setAttribute("request_uri", req.getAttribute(ERROR_REQUEST_URI));

            // forward - /error.jsp
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
            dispatcher.forward(req, resp);

        }
    }

    private String resolveServlet(String servletPath) {
        String processingServlet = null;
        if ("/student/list.do".equals(servletPath)) {
            processingServlet = "/student/list";
        }
        // todo 실행할 servlet 결정하기
        return processingServlet;
    }

}
