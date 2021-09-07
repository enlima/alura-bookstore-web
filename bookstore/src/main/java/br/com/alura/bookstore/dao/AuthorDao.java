package br.com.alura.bookstore.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.bookstore.model.Author;

public class AuthorDao {

	private String url = "jdbc:mysql://localhost:3306/bookstore?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "root";
	private Connection connection;

	public AuthorDao(Connection connection) {
		this.connection = connection;
	}

	public void register(Author author) {

		try {

			Connection connection = DriverManager.getConnection(url, user, password);

			String sql = "insert into author(name, email, birthdate, mini_resume) values (?, ?, ?, ?)";

			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, author.getName());
			ps.setString(2, author.getEmail());
			ps.setDate(3, Date.valueOf(author.getBirthdate()));
			ps.setString(4, author.getMiniResume());

			ps.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Author> list() {

		try {

			Connection connection = DriverManager.getConnection(url, user, password);

			String sql = "select * from author";

			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			List<Author> authors = new ArrayList<>();

			while (rs.next()) {

				Author a = new Author(rs.getString("name"), rs.getString("email"),
						rs.getObject("birthdate", LocalDate.class), rs.getString("mini_resume"));

				authors.add(a);
			}

			return authors;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
