package ServletAcquisto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ClassiComuni.DatiPacchetti;
import GestioneAcquisto.Carrello;



@WebServlet("/JSONCarrello")
public class JSONCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public JSONCarrello() {
	    super();
	    // TODO Auto-generated constructor stub
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i = 1;
		response.setContentType("text/html"); // prende i pacchetti che hanno come stato "c" carrello nel Database in base all'email dell'account
		response.getWriter().append("[");
		Carrello car = new Carrello();
		HttpSession sessione = request.getSession();
		String e = (String) sessione.getAttribute("email"); 
		DatiPacchetti[] pacchetti = new DatiPacchetti[20]; 
		pacchetti = car.PacCarrello(e);
		
		int j = 0;
		List<String> a = new ArrayList<String>();
		while(pacchetti[j]!=null) {
			a.add(pacchetti[j].getImmagine());
			a.add(pacchetti[j].getDescrPacc());
			a.add(pacchetti[j].getPrezzo());
			a.add(pacchetti[j].getDestinazione());
			a.add(pacchetti[j].getCodice());
			a.add(pacchetti[j].getN_acquisti());
			j++;
		}
		
		
		
		
		int n = a.size()/6;
		response.getWriter().append("{\"lunghezza\": \""+n+"\"},");
		Iterator it = a.iterator();
		while(it.hasNext())
		{
			if(i!=n) {
			response.getWriter().append("{\"img\": \""+it.next()+"\",");
			response.getWriter().append("\"descrizione_pacchetto\": \""+it.next()+"\",");
			response.getWriter().append("\"prezzo\": \""+it.next()+"\",");
			response.getWriter().append("\"destinazione\": \""+it.next()+"\",");
			response.getWriter().append("\"codice_pacchetto\": \""+it.next()+"\",");
			response.getWriter().append("\"n_pacchetti_acquistati\": \""+it.next()+"\"},");
			}
			else {
			response.getWriter().append("{\"img\": \""+it.next()+"\",");
			response.getWriter().append("\"descrizione_pacchetto\": \""+it.next()+"\",");
			response.getWriter().append("\"prezzo\": \""+it.next()+"\",");
			response.getWriter().append("\"destinazione\": \""+it.next()+"\",");
			response.getWriter().append("\"codice_pacchetto\": \""+it.next()+"\",");
			response.getWriter().append("\"n_pacchetti_acquistati\": \""+it.next()+"\"}");
			}
			i++;
		}
		response.getWriter().append("]");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
