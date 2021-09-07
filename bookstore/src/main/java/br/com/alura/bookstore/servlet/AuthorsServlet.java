package br.com.alura.bookstore.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.bookstore.dao.AuthorDao;
import br.com.alura.bookstore.factory.ConnectionFactory;
import br.com.alura.bookstore.model.Author;

@WebServlet("/authors")
public class AuthorsServlet extends HttpServlet {

	private AuthorDao authorDao;

	public AuthorsServlet() {

		this.authorDao = new AuthorDao(new ConnectionFactory().getConnection());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("authors", authorDao.list());

		req.getRequestDispatcher("WEB-INF/jsp/authors.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		String email = req.getParameter("email");
		LocalDate birthdate = LocalDate.parse(req.getParameter("birthdate"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String miniResume = req.getParameter("miniResume");

		Author author = new Author(name, email, birthdate, miniResume);

		authorDao.register(author);

		resp.sendRedirect("authors");
	}
}
