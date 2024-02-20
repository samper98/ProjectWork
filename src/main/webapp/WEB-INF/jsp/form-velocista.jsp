<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<title>Ente Sportivo</title>
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
	</head>

	<body>
	
        <center>
            <h1> Nuovo Velocista </h1>
        </center>
		
		<form action="/ente-sportivo/homepage-staff-gara/velocista" method="post">
		
			<p>Per favore, indicare i dati per inserire un nuovo velocista  !!!.</p>
			<br>
				<label for="nominativo">Nominativo</label>
			<br>
			<input type="text" id="nominativo" name="nominativo"  />
  	<br>
			
  	
       	<label for="eta">Eta</label>
			<br>
			<input type="number" id="eta" name="eta"  />
      	<br>
      	<label for="codice-fiscale">Codice Fiscale</label>
			<br>
			<input type="text" id="codice_fiscale" name="codice_fiscale" minlength="16" maxlength="16" value=""   />
        	<br>
        	<label for="altezza">Altezza</label>
			<br>
			<input type="number" id="altezza" name="altezza"  />
            	<br>
				<label for="peso">Peso</label>
			<br>
			<input type="number" id="peso" name="peso"  />
				<br>
			
			<input type="submit"  style="color:orange;" value="nuovo">
			
		</form>
  
	</body>
</html>