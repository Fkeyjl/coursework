<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<t:page-template>
    <jsp:body>

        <div class="jumbotron p-3 p-md-5   rounded bg-light border-info border">
            <h1 class="text-center display-4 pb-5 font-weight-bold">Welcome to the Application Platform!</h1>
            <hr class="mb-5"/>
            Below are the application and enrollment process steps:
            <ol>
                <li> Students apply and choose subject</li>
                <li> We notify each applicant of their marks of subject</li>
                <li> Applicants choose speciality</li>
                <li> Applicants see their status </li>
            </ol>
        </div>


    </jsp:body>
</t:page-template>