package org.generation.italy.newEnteSportivo2.servlet.control;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.generation.italy.newEnteSportivo2.model_jdbc.EnteSportivoModelException;
import org.generation.italy.newEnteSportivo2.model_jdbc.TestJdbcEnteSportivo;
import org.generation.italy.newEnteSportivo2.model_jdbc.entity.Gara;
import org.generation.italy.newEnteSportivo2.model_jdbc.entity.Iscrizione;
import org.generation.italy.newEnteSportivo2.model_jdbc.entity.Velocista;
import org.generation.italy.newEnteSportivo2.model_jdbc.entity.VelocistaIscrizioneGara;
import org.generation.italy.newEnteSportivo2.model_jdbc.entity.VelocistaPartecipanteGara;
import org.springframework.stereotype.Component;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@WebServlet
public class StaffGaraServlet2EE extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init() { // metodo che viene richiamato dal container al momento della installazione
							// della webapp in esso con mappatura della servlet (l'altro è 'destroy' (al
							// momento della rimozione della servlet dal container), non gestito in questa
							// servlet).
		// utenteDAO = new UtenteDAO();

		System.out.println("init eseguito");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) // metodo chiamato dal container
																					// (GlassFish), a seguito di
																					// ricezione da parte sua del
																					// messaggio HTTP-Request, con
																					// metodo POST inviato dal client
																					// (browser)
			throws ServletException, IOException {
		executeAction(request, response); // re-inoltra al metodo doGet la gestione della action | request e response
											// sono istanze di tipo HttpServletRequest ed HttpServletResponse, create
											// dal container per fornire a e ricevere dalla servlet i dettagli circa i
											// messaggi di HTTP-Request ed HTTP-Response ricevuti da ed inviati al
											// client.
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) // metodo chiamato dal container
																					// (GlassFish), a seguito di
																					// ricezione da parte sua del
																					// messaggio HTTP-Request, con
																					// metodo GET inviato dal client
																					// (browser)
			throws ServletException, IOException {
		System.out.println("DO GET ENTRATO");
		System.out.println(request.getServletPath());
		executeAction(request, response); // re-inoltra al metodo doGet la gestione della action | request e response
											// sono istanze di tipo HttpServletRequest ed HttpServletResponse, create
											// dal container per fornire a e ricevere dalla servlet i dettagli circa i
											// messaggi di HTTP-Request ed HTTP-Response ricevuti da ed inviati al
											// client.

	}

	protected void executeAction(HttpServletRequest request, HttpServletResponse response) // metodo chiamato dal
																							// container (GlassFish), a
																							// seguito di ricezione da
																							// parte sua del messaggio
																							// HTTP-Request, con metodo
																							// GET inviato dal client
																							// (browser)
			throws ServletException, IOException {
		String actionName;
		if (request.getPathInfo() != null)
			 actionName = request.getServletPath()+request.getPathInfo(); // parte action della URI: gestione della azione applicativa, la
			
			else
				 actionName = request.getServletPath();

		
		System.out.println("Action name: " + actionName); // parte della URL dopo il nome della webapp...

		switch (actionName.toLowerCase().trim()) {

		// http://localhost:8080/JDBCente_sportivo/lista-gare
		case "/ente-sportivo/homepage-staff-gara":
			actionHomePageStaffGara(request, response);

			// System.out.println("Azione intereccettata");
			break;
		case "/ente-sportivo/homepage-staff-gara/visualizza-dettaglio-staff":
			System.out.println("Azione intercettata");
		
			actionVisualizzaDettaglioVelocistiPartecipantiGara(request, response);
			break;
		case "/ente-sportivo/homepage-staff-gara/visualizza-iscritti-staff":
			actionVisualizzaDettaglioVelocistiIscrittiGara(request, response);
			break;
		case "/ente-sportivo/homepage-staff-gara/form-gara":
			actionFormNuovaGara(request, response);
			break;
		case "/ente-sportivo/homepage-staff-gara/gara":
		  actionNuovaGara(request, response);
		break;
		case "/ente-sportivo/homepage-staff-gara/form-modifica-gara":
			actionFormModificaDatiGara(request, response);
			break;
		case "/ente-sportivo/homepage-staff-gara/modifica-gara":
			actionModificaDatiGara(request, response);
			break;
		case "/ente-sportivo/homepage-staff-gara/elimina-gara":
			actionEliminazioneGara(request, response);
			break;
		case  "/ente-sportivo/homepage-staff-gara/elimina-iscritto":
			actionEliminaIscritto(request, response);
		break;
		case "/ente-sportivo/homepage-staff-gara/elenco-velocisti":
			actionElencoVelocisti(request, response);
			break;
		case "/ente-sportivo/homepage-staff-gara/velocista":
			System.out.println("post velocista");
			actionVelocista(request, response);
			break;
		case "/ente-sportivo/homepage-staff-gara/form-velocista":
			System.out.println("get velocista");
			actionFormVelocista(request, response);
			break;
		default:
			break;
			
		}

	}

	private void actionHomePageStaffGara(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String messageToShow = UserMessages.msgEsitoOkVisualizzazioneLista;
	    String ordina = request.getParameter("ordinamento");
	    List<Gara> elencoGare = new ArrayList<>();
	    String cerca = request.getParameter("ricerca");
	    try {
	        TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
	        if ("asc".equals(ordina)) {
	            elencoGare = testJdbcEnteSportivo.getGaraDao().loadGaraOrderByLuogo();
	        } else if ("data".equals(ordina)) {
	        	
	        	elencoGare = testJdbcEnteSportivo.getGaraDao().loadGaraByDataAndOra();
	        } else if (cerca != null && !cerca.isEmpty()) { // Aggiunto controllo per il parametro di ricerca
	            elencoGare = testJdbcEnteSportivo.getGaraDao().loadGareLikeLuogo(cerca);
	        } else {
	            elencoGare = testJdbcEnteSportivo.getGaraDao().loadGara();
	        }
	        request.setAttribute("listaGare", elencoGare);
	    } catch (EnteSportivoModelException e) {
	        messageToShow = UserMessages.msgErroreVisualizzazioneLista;
	    }

	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/homepage-staff-gara.jsp");
	    dispatcher.forward(request, response);
	}

	

	

	private void actionVisualizzaDettaglioVelocistiPartecipantiGara(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String messageToShow = UserMessages.msgEsitoOkVisualizzazioneLista;
		// String visualizza = request.getParameter("visualizza-dettagli");
		Long id = Long.parseLong(request.getParameter("id")); // get0 parameter ti salva la vita , getParameter lavora
																// sui tipi di stringhe
		request.setAttribute("id-gara", id);
		System.out.println("Id-gara" + id);
		List<VelocistaPartecipanteGara> elencoVelocistaPartecipantiGara = new ArrayList<>();
		try {
			TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
			elencoVelocistaPartecipantiGara = testJdbcEnteSportivo.getPartecipazioneDao()
					.loadVelocistiPartecipantiGara(id);

			System.out.println("Numero partecipanti" + elencoVelocistaPartecipantiGara.size());

		} catch (Exception e) {
			System.out.println("errore loadGarePartecipate:" + e.getMessage());
			messageToShow = UserMessages.msgErroreVisualizzazioneListaPartecipanti;
		}
		request.setAttribute("listaPartecipanti", elencoVelocistaPartecipantiGara); // ASSOCIARE E LAVORARE CON JSTL SU
																					// L'APPOSITA
		// PAGINA JSP
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/visualizza-dettaglio-staff.jsp");
		// ottiene il riferimento alla pagina JSP

		dispatcher.forward(request, response);

	}


	
	
	private void actionVisualizzaDettaglioVelocistiIscrittiGara(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String messageToShow = UserMessages.msgEsitoOkVisualizzazioneLista;
		// String visualizza = request.getParameter("visualizza-dettagli");
		Long id = Long.parseLong(request.getParameter("id")); // get0 parameter ti salva la vita , getParameter lavora
																// sui tipi di stringhe
		request.setAttribute("id-gara", id);
		System.out.println("Id-gara" + id);
		List<VelocistaIscrizioneGara> elencoVelocistiIscrittiGara = new ArrayList<>();
		try {
			TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
			elencoVelocistiIscrittiGara = testJdbcEnteSportivo.getIscrizioneDao().loadVelocistiIscrittiGara(id);
			System.out.println("Numero partecipanti" + elencoVelocistiIscrittiGara.size());

		} catch (Exception e) {
			System.out.println("errore loadIscrittiGara: " + e.getMessage());
			messageToShow = UserMessages.msgErroreVisualizzazioneListaPartecipanti;
		}
		request.setAttribute("listaIscritti", elencoVelocistiIscrittiGara); // ASSOCIARE E LAVORARE CON JSTL SU
																					// L'APPOSITA
		// PAGINA JSP
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/visualizza-iscritti-staff.jsp");
		// ottiene il riferimento alla pagina JSP

		dispatcher.forward(request, response);

	}
	
	
	
	
	
	public void actionNuovaGara(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String messageToShow = UserMessages.msgEsitoOkIscrizione;

		 String dataOraGaraString = request.getParameter("data-gara") !=
		 null ? request.getParameter("data-gara") : "";
		String luogo = request.getParameter("luogo") != null
				? request.getParameter("luogo")
				: "";

		// LocalDateTime dataOraiscrizione =
		// LocalDateTime.parse(dataOraIscrizioneString);
		LocalDateTime dataGara = LocalDateTime.parse(dataOraGaraString);
		Gara gara = new Gara (dataGara,luogo);

		try {
			TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
			testJdbcEnteSportivo.getGaraDao().addGara(gara);

			messageToShow = UserMessages.msgEsitoOkGara;

		} catch (EnteSportivoModelException e) {

			messageToShow = UserMessages.msgErroreGara;
			// htmlContentPage = e.getMessage().getBytes();

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("message-to-show", messageToShow);
//		// imposta il parametro nominativoUtenteLoggato

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/message-staff.jsp");
		// ottiene il riferimento alla apgina JSP
		dispatcher.forward(request, response);
	}

	private static void actionFormNuovaGara(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// throws BancaControlException, BancaModelException {
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/form-gara.jsp");
		// ottiene il riferimento alla pagina JSP
		dispatcher.forward(request, response);

	}
	
	
	
	
	private static void actionFormModificaDatiGara(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// throws BancaControlException, BancaModelException {
		Long id = Long.parseLong(request.getParameter("id-gara"));
		request.setAttribute("id-gara", id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/form-modifica-gara.jsp");
		// ottiene il riferimento alla pagina JSP
		dispatcher.forward(request, response);

	}
	
	
	public void actionModificaDatiGara(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String messageToShow = UserMessages.msgEsitoOkIscrizione;

		Long id = Long.parseLong(request.getParameter("id-gara")); // get0 parameter ti salva la vita , getParameter
		// lavora
		// sui tipi di stringhe
		System.out.println("Id gara :" + id);
  String dataOraGaraString = request.getParameter("data-gara") !=  null ? request.getParameter("data-gara") : "";
String luogo = request.getParameter("luogo") != null ? request.getParameter("luogo") : "";

 LocalDateTime dataOraiscrizione = LocalDateTime.parse(dataOraGaraString);
 
		Gara gara = new Gara(id,dataOraiscrizione, luogo);

		try {
			TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
			testJdbcEnteSportivo.getGaraDao().updateGara(gara);

			messageToShow = UserMessages.msgEsitoOkModificaDatiGara;

		} catch (EnteSportivoModelException e) {

			messageToShow = UserMessages.msgErroreModificaGara;
// htmlContentPage = e.getMessage().getBytes();

// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("message-to-show", messageToShow);
// imposta il parametro nominativoUtenteLoggato

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/message-staff-modifica.jsp");
// ottiene il riferimento alla apgina JSP
		dispatcher.forward(request, response);
	}		
	 
	
	
	public void actionEliminazioneGara(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException	{
		String messageToShow = UserMessages.msgOkEliminazione ;
		Long id = Long.parseLong(request.getParameter("id")); // get0 parameter ti salva la vita , getParameter lavora
		// sui tipi di stringhe
           request.setAttribute("id-gara", id);
           System.out.println("Id-gara" + id);
		   Gara gara =null;
           try {
   			TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
   			 testJdbcEnteSportivo.getGaraDao().deleteGara(id);


	} catch (Exception e) {
		System.out.println("errore loadGarePartecipate:" + e.getMessage());
		messageToShow = UserMessages.msgErroreEliminazioneGara;
	}
           request.setAttribute("message-to-show", messageToShow);
        // imposta il parametro nominativoUtenteLoggato

        		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/message-staff-elimina.jsp");
        // ottiene il riferimento alla apgina JSP
        		dispatcher.forward(request, response);
	
	}
	
	
	public void actionEliminaIscritto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		List<VelocistaIscrizioneGara> elencoVelocistiIscrittiGara = new ArrayList<>();
		String messageToShow = UserMessages.msgOkEliminazione ;
		Long id = Long.parseLong(request.getParameter("id")); // get0 parameter ti salva la vita , getParameter lavora
		 String nominativo = request.getParameter("nominativo");// sui tipi di stringhe
           request.setAttribute("id-gara", id);
           request.setAttribute("nominativo", nominativo);
           System.out.println("Id-gara " + id);
           System.out.println("nominativo" + nominativo);
		   Iscrizione iscrizione = new Iscrizione(nominativo, id);
           try {
   			TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
   			 testJdbcEnteSportivo.getIscrizioneDao().removeIscrizioneVelocista(nominativo, id);


	} catch (Exception e) {
		System.out.println("errore loadGarePartecipate:" + e.getMessage());
		messageToShow = UserMessages.msgErroreEliminazioneGara;
	}
           request.setAttribute("listaIscritti", elencoVelocistiIscrittiGara);
           
           request.setAttribute("message-to-show", messageToShow);
        // imposta il parametro nominativoUtenteLoggato

        		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/message-staff-elimina-iscritto.jsp");
        // ottiene il riferimento alla apgina JSP
        		dispatcher.forward(request, response);
	
	}
	
	private void actionElencoVelocisti(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String messageToShow = UserMessages.msgEsitoOkVisualizzazioneLista;
	    
	    List<Velocista> elencoVelocisti = new ArrayList<>();
	  
	    try {
	        TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
	        
	            elencoVelocisti = testJdbcEnteSportivo.getVelocistaDao().loadVelocista();
	    } catch (EnteSportivoModelException e) {
	        messageToShow = UserMessages.msgErroreVisualizzazioneLista;
	    }
	    request.setAttribute("listaVelocisti", elencoVelocisti);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/elenco-velocisti.jsp");
	    dispatcher.forward(request, response);
	}
	
	public void actionVelocista(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String messageToShow = UserMessages.msgEsitoOkIscrizione;

		 String nominativo = request.getParameter("nominativo") != null ? request.getParameter("nominativo") : "";
		  String etaString = request.getParameter("eta") != null ? request.getParameter("eta") : "";
        String codiceFiscale = request.getParameter("codice-fiscale")!= null ? request.getParameter("codice-fiscale") : "";
        String altezzaString = request.getParameter("altezza")!= null ? request.getParameter("altezza") : "";
          String pesoString = request.getParameter("peso")!= null ? request.getParameter("peso") : "";
		// LocalDateTime dataOraiscrizione =
		// LocalDateTime.parse(dataOraIscrizioneString);
          System.out.println("codiceFiscale" + codiceFiscale);
		Integer eta = Integer.parseInt(etaString);
		Integer altezza = Integer.parseInt(altezzaString);
		Float peso = Float.parseFloat(pesoString);
		Velocista velocista = new Velocista(codiceFiscale, nominativo, eta, altezza, peso);
		try {
			TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
			testJdbcEnteSportivo.getVelocistaDao().addVelocista(velocista);

			messageToShow = UserMessages.msgOkVelocista;

		} catch (EnteSportivoModelException e) {

			messageToShow = UserMessages.msgErroreVelocista;
			// htmlContentPage = e.getMessage().getBytes();

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("message-to-show", messageToShow);
//		// imposta il parametro nominativoUtenteLoggato
		System.out.println("velocista aggiunto");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/message-staff-velocista.jsp");
		// ottiene il riferimento alla apgina JSP
		dispatcher.forward(request, response);
	}
	
	private static void actionFormVelocista(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// throws BancaControlException, BancaModelException {
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/form-velocista.jsp");
		// ottiene il riferimento alla pagina JSP
		dispatcher.forward(request, response);

	}
}
