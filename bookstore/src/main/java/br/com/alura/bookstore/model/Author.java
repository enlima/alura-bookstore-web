package br.com.alura.bookstore.model;

import java.time.LocalDate;

public class Author {

	private String name;
	private String email;
	private LocalDate birthdate;
	private String miniResume;

	public Author(String name, String email, LocalDate birthdate, String miniResume) {
		this.name = name;
		this.email = email;
		this.birthdate = birthdate;
		this.miniResume = miniResume;
	}

	@Override
	public String toString() {
		return "Author [name=" + name + ", email=" + email + ", birthdate=" + birthdate + ", miniResume=" + miniResume
				+ "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public String getMiniResume() {
		return miniResume;
	}

	public void setMiniResume(String miniResume) {
		this.miniResume = miniResume;
	}
}
