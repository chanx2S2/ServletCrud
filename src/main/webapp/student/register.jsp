<%--
  Created by IntelliJ IDEA.
  User: kimchanyoung
  Date: 24. 5. 7.
  Time: 오후 4:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>학생-등록</title>
    <link rel="stylesheet" href="/style.css" />
    <meta charset="UTF-8" />
</head>

<body>
<!-- action 주소 설정
    //등록
        action = /student/register
    //수정
        action = /student/update
-->
    <c:choose>
        <c:when test="${empty student}">
            <c:set var="action" value="/register.do" />
        </c:when>
        <c:otherwise>
            <c:set var="action" value="/update.do" />
        </c:otherwise>
    </c:choose>

<form method="post" action="${action}">
    <table>
        <tbody>
        <tr>
            <th>ID</th>
            <td><label>
                <input type="text" name="id" value="${student.id}" required />
            </label></td>
        </tr>
        <!-- input 구현 -->
        <tr>
            <th>이름</th>
            <td><label>
                <input type="text" name="name" value="${student.id}" required />
            </label></td>
        </tr>
        <tr>
            <th>성별</th>
            <td><label>
                <input type="radio" name="gender" value="M" ${student.gender eq 'M' ? 'checked' : ''} />남
                <input type="radio" name="gender" value="F" ${student.gender eq 'F' ? 'checked' : ''} />여
            </label></td>
        </tr>
        <tr>
            <th>나이</th>
            <td><label>
                <input type="number" name="age" value="${student.age}" required />
            </label></td>
        </tr>
        </tbody>
    </table>
    <p>
        <button type="submit">
            <c:choose>
                <c:when test="${empty student}">
                    등록
                </c:when>
                <c:otherwise>
                    수정
                </c:otherwise>
            </c:choose>
        </button>
    </p>
</form>
</body>
</html>
