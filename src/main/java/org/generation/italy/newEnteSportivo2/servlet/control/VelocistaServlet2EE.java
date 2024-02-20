package org.generation.italy.newEnteSportivo2.servlet.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.generation.italy.newEnteSportivo2.model_jdbc.EnteSportivoModelException;
import org.generation.italy.newEnteSportivo2.model_jdbc.TestJdbcEnteSportivo;
import org.generation.italy.newEnteSportivo2.model_jdbc.entity.Gara;
import org.generation.italy.newEnteSportivo2.model_jdbc.entity.Iscrizione;
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
public class VelocistaServlet2EE extends HttpServlet {

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
		
		System.out.println("POSTOLO");
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
		System.out.println("gettolo ENTRATO");
		System.out.println("path " + request.getServletPath());
		
		System.out.println("path info: " + request.getPathInfo());
		
		
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
		System.out.println("actiolo");
		String actionName;
		if (request.getPathInfo() != null)
		 actionName = request.getServletPath()+request.getPathInfo(); // parte action della URI: gestione della azione applicativa, la
		
		else
			 actionName = request.getServletPath();
			
		System.out.println("Azione: " + actionName.toLowerCase().trim()); // parte della URL dopo il nome della webapp...

		switch (actionName.toLowerCase().trim()) {

		// http://localhost:8080/JDBCente_sportivo/lista-gare
		case "/ente-sportivo/homepage-velocista":
			actionHomePageVelocista(request, response);

			// System.out.println("Azione intereccettata");
			break;
		case "/ente-sportivo/homepage-velocista/visualizza-dettaglio":
			System.out.println("Azione intercettata");
			
			// actionVisualizzaDettaglioGarePartecipate(request, response);
			// actionVisualizzaDettaglioGarePartecipate1(request, response);
			// actionVisualizzaDettaglioGarePartecipate2(request, response);
			actionVisualizzaDettaglioVelocistiPartecipantiGara(request, response);
			break;
		case "/ente-sportivo/homepage-velocista/form-iscrizione":
			System.out.println("azione :" + actionName);
			actionFormIscrizione(request, response);
			break;
		case "/ente-sportivo/homepage-velocista/iscrizione":
			
			System.out.println("azione" + actionName);
			actionIscrizione(request, response);

			break;
		case "/ente-sportivo/homepage-velocista/visualizza-iscritti":
			actionVisualizzaDettaglioVelocistiIscrittiGara(request, response);
			break;
		default:
			;
		}

	}

	private void actionHomePageVelocista(HttpServletRequest request, HttpServletResponse response)
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
	    System.out.println("chiamata pagina jsp");
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/homepage-velocista.jsp");
	    dispatcher.forward(request, response);
	}

	

	/*
	 * private void actionVisualizzaIscritte(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException { String
	 * messageToShow = UserMessages.msgEsitoOkVisualizzazioneLista; String iscritti
	 * = request.getParameter("visualizza-iscritti");
	 * 
	 * List<Iscrizione> elencoIscritti = new ArrayList<>(); try {
	 * TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
	 * elencoIscritti =
	 * testJdbcEnteSportivo.getIscrizioneDao().loadListaIscrittiGara();
	 * 
	 * request.setAttribute("listaIscritti", elencoIscritti);
	 * 
	 * }catch (EnteSportivoModelException e) { messageToShow =
	 * UserMessages.msgErroreVisualizzazioneListaIscritti; } RequestDispatcher
	 * dispatcher = request.getRequestDispatcher("elencoIscritti.jsp"); // ottiene
	 * il riferimento alla pagina JSP dispatcher.forward(request, response);
	 * 
	 * }
	 */

	public void actionIscrizione(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String messageToShow = UserMessages.msgEsitoOkIscrizione;
		Long id = Long.parseLong(request.getParameter("id-gara")); // get0 parameter ti salva la vita , getParameter
																	// lavora
																	// sui tipi di stringhe
		System.out.println("Id gara" + id);
		// String dataOraIscrizioneString = request.getParameter("data_iscrizione") !=
		// null ? request.getParameter("data_iscrizione") : "";
		String codiceFiscaleString = request.getParameter("codice_fiscale") != null
				? request.getParameter("codice_fiscale")
				: "";

		// LocalDateTime dataOraiscrizione =
		// LocalDateTime.parse(dataOraIscrizioneString);
		Iscrizione iscrizione = new Iscrizione(codiceFiscaleString, id);

		try {
			TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
			testJdbcEnteSportivo.getIscrizioneDao().addIscrizione(iscrizione);

			messageToShow = UserMessages.msgEsitoOkIscrizione;

		} catch (EnteSportivoModelException e) {

			messageToShow = UserMessages.msgErroreIscrizione;
			// htmlContentPage = e.getMessage().getBytes();

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("message-to-show", messageToShow);
		// imposta il parametro nominativoUtenteLoggato

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/message.jsp");
		// ottiene il riferimento alla apgina JSP
		dispatcher.forward(request, response);
	}

	private static void actionFormIscrizione(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// throws BancaControlException, BancaModelException {
		Long id = Long.parseLong(request.getParameter("id-gara"));
		request.setAttribute("id-gara", id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/form-iscrizione.jsp");
		// ottiene il riferimento alla pagina JSP
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/visualizza-dettaglio.jsp");
		// ottiene il riferimento alla pagina JSP

		dispatcher.forward(request, response);

	}

//	private void actionVisualizzaDettaglioGarePartecipate2(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String messageToShow = UserMessages.msgEsitoOkVisualizzazioneLista;
//		// String visualizza = request.getParameter("visualizza-dettagli");
//		Long id = Long.parseLong(request.getParameter("id")); // get0 parameter ti salva la vita , getParameter lavora
//																// sui tipi di stringhe
//		request.setAttribute("id-gara", id);
//		System.out.println("Id-gara" + id);
//		List<Gara> elencoVelocisti = new ArrayList<>();
//		try {
//			TestJdbcEnteSportivo testJdbcEnteSportivo = new TestJdbcEnteSportivo();
//			elencoVelocisti = testJdbcEnteSportivo.getGaraDao().loadGarePartecipate(id);
//
//			System.out.println("Numero partecipanti" + elencoVelocisti.size());
//
//		} catch (Exception e) {
//			System.out.println("errore loadGarePartecipate:" + e.getMessage());
//			messageToShow = UserMessages.msgErroreVisualizzazioneListaPartecipanti;
//		}
//		request.setAttribute("listaPartecipanti", elencoVelocisti); // ASSOCIARE E LAVORARE CON JSTL SU L'APPOSITA
//																	// PAGINA JSP
//		RequestDispatcher dispatcher = request.getRequestDispatcher("visualizza-dettaglio.jsp");
//		// ottiene il riferimento alla pagina JSP
//
//		dispatcher.forward(request, response);
//
//	}

	
	
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/visualizza-iscritti.jsp");
		// ottiene il riferimento alla pagina JSP

		dispatcher.forward(request, response);

	}
}
