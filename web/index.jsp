<%-- 
    Document   : index
    Created on : 30-déc.-2014, 15:08:01
    Author     : max
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>


<!DOCTYPE html>
    <f:view>
<html xmlns:h="http://xmlns.jcp.org/jsf/html" xmlns:f="http://xmlns.jcp.org/jsf/core">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>

        <h:form>
            <h:commandButton value="Valider 1 " action="login"  /><br>
            <h:commandButton value="Valider 2 " action="#{userMB.doAction}"  /><br>
                
        </h:form>

    </body>
</html>
    </f:view>

