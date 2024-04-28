package spring_hibernate_xml_mto.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BankAccount {

	@Id
	private int id;
	private String name;
	private int ifsc;
	private double balance;
	@ManyToOne
	private Person person;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIfsc() {
		return ifsc;
	}
	public void setIfsc(int ifsc) {
		this.ifsc = ifsc;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", name=" + name + ", ifsc=" + ifsc + ", balance=" + balance + ", person="
				+ person + "]";
	}
	
	
	
}
