<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
    	<link rel="icon" href="img/icona.png" type="image/png">
    	<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
	integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link th:rel="stylesheet" href="/css/main.css">
        <title>EnteSportivo</title>
    </head>
    <body>
        <center>
            <h1>Messaggio esito azione</h1>
        </center>
		<%=(String) request.getAttribute("message-to-show")%>
	 <br>	
		<td><a class="btn btn-success" style="background-color:orange;" href=/ente-sportivo/homepage-staff-gara>  TORNA ALLA HOME  </a></td>
	</body>
</html>
