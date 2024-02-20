<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
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
<title>Elenco Gara</title>
</head>

<h2> Lista Velocisti </h2>
<h3> <td><a class="btn btn-success"  style="background-color:orange;" href="/ente-sportivo/homepage-staff-gara/form-velocista" > Nuovo Velocista  </a></td>
</h3>
<div align="center">
		<table class="table" border="1" cellpadding="5">

			<tr>
				<th>Nominativo</th>
				<th>Età</th>
				<th>Codice Fiscale</th>
				<th>Altezza</th>
				<th>Peso<th>
			</tr>

			<c:forEach var="velocista" items="${listaVelocisti}">
					<tr>
						<td><c:out value="${velocista.nominativo}" /></td>
						<td><c:out value="${velocista.eta}" /></td>
						<td><c:out value="${velocista.codiceFiscale}" /></td>
						<td><c:out value="${velocista.altezza}" /></td>
						<td><c:out value="${velocista.peso}" /></td>
						
					</tr>
			</c:forEach>
		</table>
			<td><a class="btn btn-success"  style="background-color:orange;" href=/ente-sportivo/homepage-staff-gara />  TORNA ALLA HOME  </a></td>
	</div>
	
	
	<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
	integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
	crossorigin="anonymous"></script>
	
	<script>
	function confermaEliminazione(event) {
		  // Mostra la finestra di dialogo di conferma
		  var conferma = confirm("Sei sicuro?");
		  // Se l'utente conferma, l'eliminazione viene eseguita
		  if (!conferma) {
		    // Se l'utente annulla, impedisci l'esecuzione dell'azione predefinita del link
		    event.preventDefault(); // Questo impedisce al link di eseguire l'azione predefinita (navigare all'URL)
		  }
		}
</script>

</body>
</html>