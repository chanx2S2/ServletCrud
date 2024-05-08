package com.nhnacademy.crud;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;

import static com.nhnacademy.crud.RequestDispatcher.*;

@WebServlet(name = "errorServlet", urlPatterns = "/error")
public class ErrorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));

        //exception_type
        req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
        //message
        req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
        //exception
        req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));
        //request_uri
        req.setAttribute("request_uri", req.getAttribute(ERROR_REQUEST_URI));

        ///error.jsp forward 처리
        RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
        dispatcher.forward(req, resp);
    }
}
