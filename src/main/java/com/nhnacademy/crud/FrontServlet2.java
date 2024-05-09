package com.nhnacademy.crud;

import com.nhnacademy.crud.contol.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.nhnacademy.crud.RequestDispatcher.*;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet2 extends HttpServlet {
    private static final String REDIRECT_PREFIX="redirect";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        try {
            // 실제 로직을 처리할 Command(Controller) 결정, String view = command.execute() ...
            Command command = resolveCommand(req.getServletPath(), req.getMethod());
            String view = command.execute(req, resp);

            if (view.startsWith(REDIRECT_PREFIX)) {
                log.error("redirect-url : {}", view.substring(REDIRECT_PREFIX.length() + 1));
                // `redirect:`로 시작하면 redirect 처리.
                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length()+1));
            } else {
                // redirect 아니면 JSP에게 view 처리를 위임하여 그 결과를 include 처리.
                RequestDispatcher rd = req.getRequestDispatcher(view);
                rd.include(req, resp);

            }
        }catch (Exception ex){
            //공통 error 처리
            req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
            req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
            req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
            req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));
            req.setAttribute("request_uri", req.getAttribute(ERROR_REQUEST_URI));
            log.error("status_code:{}", req.getAttribute(ERROR_STATUS_CODE));
            RequestDispatcher rd = (RequestDispatcher) req.getRequestDispatcher("/error.jsp");
            rd.forward(req,resp);
        }

    }

    private Command resolveCommand(String servletPath, String method){
        Command command = null;
        if("/student/list.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new StudentListController();
        }
        // resolveCommand 수정 http-method를 고려
        else if("/student/view.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new StudentViewController();
        }else if("/student/delete.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
            command = new StudentDeleteController();
        }else if("/student/update.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new StudentUpdateFormController();
        }else if("/student/update.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
            command = new StudentUpdateController();
        }else if("/student/register.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new StudentRegisterFormController();
        }else if("/student/register.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
            command = new StudentRegisterController();
        }else if("/error.do".equals(servletPath)){
            command = new ErrorController();
        }

        return command;
    }
}
