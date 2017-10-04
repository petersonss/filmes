package instanciacao;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Artista;
import dominio.Filme;
import dominio.Participacao;


public class Instanciacao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Filme f1 = new Filme(null, "O Aviador", 170, 2005);
			Filme f2 = new Filme(null, "Titanic", 195, 1997);
		

			Artista a1 = new Artista(null, "Leonardo di Caprio", "Eua", new BigDecimal("10000000.00"), sdf.parse("11/11/1974"));
			Artista a2 = new Artista(null, "Cate Blanshed", "Austrália", new BigDecimal("5000000.00"), sdf.parse("14/05/1969"));
			Artista a3 = new Artista(null, "Kate Winslet", "UK", new BigDecimal("8000000.00"), sdf.parse("05/10/1975"));
			
			Participacao p1 = new Participacao(null, "Jack Dawson", new BigDecimal("2000000.00"), f2, a1);
			Participacao p2 = new Participacao(null, "howard Hughes", new BigDecimal("1000000.00"), f1, a1);
			Participacao p3 = new Participacao(null, "Rose Bukater", new BigDecimal("1000000.00"), f2, a3);
			Participacao p4 = new Participacao(null, "Katharine Hepburn", new BigDecimal("500000.00"), f1, a2);
			
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("meujpa");
			EntityManager em = factory.createEntityManager();
			
			em.getTransaction().begin();
			
			em.persist(f1);
			em.persist(f2);
			em.persist(a1);
			em.persist(a2);
			em.persist(a3);
			em.persist(p1);
			em.persist(p2);
			em.persist(p3);
			em.persist(p4);
			
			em.getTransaction().commit();
			
			em.close();
			factory.close();
			
			
			response.getWriter().append("Cache total do filme " + f1.getNome() + "\n");
			response.getWriter().append(f1.cacheTotal() + "\n");


			
		response.getWriter().append("Instanciação do modelo concluída!!!");
		} catch (ParseException e) {
			response.getWriter().append("Erro de parse!!!");
			
		}
	}

}
