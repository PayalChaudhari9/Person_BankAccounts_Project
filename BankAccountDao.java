package spring_hibernate_xml_mto.dao;

import java.util.List;
import javax.persistence.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


import spring_hibernate_xml_mto.dto.BankAccount;
import spring_hibernate_xml_mto.dto.Person;

public class BankAccountDao {

	public EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("shivani").createEntityManager();
	}

	public void saveBankAccount(List<BankAccount> list) {
		EntityManager manager = getEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		transaction.begin();
		for (BankAccount bankAccount : list) {
			manager.persist(bankAccount.getPerson());
			manager.persist(bankAccount);
		}
		transaction.commit();

	}

	public BankAccount findBankAccount(int id) {
		EntityManager manager = getEntityManager();

		try {
			BankAccount account = manager.find(BankAccount.class, id);
			return account;
		} catch (Exception e) {
			return null;
		}

	}
	
	public boolean deleteBankAccountById(int id) {
		EntityManager manager = getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		Person person = manager.find(Person.class, id);
		
		transaction.begin();
		if (person!=null) {
			Query query = manager.createQuery("delete from BankAccount where person = :id");
		    query.setParameter("id",person);
		
		    int deletedCount = query.executeUpdate();
		    
		    if (deletedCount>0) {
				
		    	manager.remove(person);
		    	transaction.commit();
		    	return true;
			}
		}
		return false;
	}
	
	public boolean deleteBankAccountByPersonId(int personId) {
		EntityManager manager = getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		Person person = manager.find(Person.class, personId);
		
		transaction.begin();
		if (person!=null) {
			Query query = manager.createQuery("delete from BankAccount where person = :person");
		    query.setParameter("person",person);
		
		    int deletedCount = query.executeUpdate();
		    
		    if (deletedCount>0) {
				
		    	manager.remove(person);
		    	transaction.commit();
		    	return true;
			}
		    else {
				System.out.println("No bank account associated with the person!");
				return false;
			
			}
		}else {
			 System.out.println("Person not found!");
	            
	            return false;
		}
		
	}
	
	public List<BankAccount> getAllBankAccount() {
		EntityManager manager = getEntityManager();
		
		Query query = manager.createQuery("select a from BankAccount a ");
		
		List<BankAccount> list = query.getResultList();
		
		return list;
	}
	
	public void updateBankAccountById(int id, BankAccount b1) {
		EntityManager manager = getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		BankAccount account = manager.find(BankAccount.class, id);
		
		if (account!=null) {
			transaction.begin();
			b1.setId(id);
			manager.merge(b1.getPerson());
			manager.merge(b1);
			transaction.commit();
		}else {
			System.out.println("Account not found!");
		}
	}
}
