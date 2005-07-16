<% response.setContentType("text/html; charset=UTF-8"); %><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<% try { %><html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><%= RollerRuntimeConfig.getProperty("site.name") %></title>
    <%-- this is included so cached pages can still set contentType --%>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript"
        src="<%= request.getContextPath() %>/theme/scripts/roller.js"></script>
    <link rel="stylesheet" type="text/css" media="all"
         href="<%= request.getContextPath() %>/theme/layout.css" />
    <link rel="stylesheet" type="text/css" media="all"
         href="<%= request.getContextPath() %>/theme/roller.css" />
    <link rel="stylesheet" type="text/css" media="all"
         href="<%= request.getContextPath() %>/theme/menu.css" />
    <link rel="stylesheet" type="text/css" media="all"
         href="<%= request.getContextPath() %>/theme/calendar.css" />
    <script type="text/javascript"
        src="<%= request.getContextPath() %>/tags/calendar.js"></script>
    <script type="text/javascript"
        src="<%= request.getContextPath() %>/theme/scripts/overlib.js"
        ><!-- overLIB (c) Erik Bosrup --></script>
</head>
<body>
    <div id="overDiv" style="position:absolute; visibility:hidden; z-index:1000;"></div>
    <div id="header">
        <roller:NavigationBar />
        <div style="clear:both">
            <roller:Menu model="editor-menu.xml" view="/menu-tabbed.vm" />
        </div>
    </div>

    <%-- <div id="left" class="left">
        <c:if test="${!empty leftPage}">
            <c:import url="${leftPage}" />
        </c:if>
    </div> --%>

    <div id="content">
    <% 
    Roller roller = RollerRequest.getRollerRequest(request).getRoller();
    String userName = roller.getUser().getUserName();
    if (    !userName.equals(UserData.ANONYMOUS_USER.getUserName()) 
         && !userName.equals(UserData.SYSTEM_USER.getUserName())) { %>    
    <fmt:message key="mainPage.loggedInAs" /> [<%= userName %>]<br /><br />    
    <% } %>
    <%@ include file="messages.jsp" %>

