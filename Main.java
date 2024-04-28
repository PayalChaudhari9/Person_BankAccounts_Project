package spring_hibernate_xml_mto.controller;
import java.util.Scanner;

import javax.swing.text.DefaultEditorKit.BeepAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring_hibernate_xml_mto.dao.BankAccountDao;
import spring_hibernate_xml_mto.dto.BankAccount;

public class Main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		ApplicationContext context = new ClassPathXmlApplicationContext("mto.xml");
		
		BankAccount account3 = (BankAccount) context.getBean("account3");
		BankAccount account4 = (BankAccount) context.getBean("account4");
		BankAccount account5 = (BankAccount) context.getBean("account5");
		
		List<BankAccount>list = new ArrayList<BankAccount>();
		list.add(account3);
		list.add(account4);
		list.add(account5);
		
		BankAccountDao dao = context.getBean("dao",BankAccountDao.class);
		
		System.out.println("Enter your choice : \n1.Save Bank Account\n2.Find Bank Account\n3.Delete Bank Account\n4.Update Bank Account By Id\n5.Find All Bank Accounts");
		int choice = scanner.nextInt();
		switch(choice) 
		{
		case 1: dao.saveBankAccount(list);
			break;

		case 2:
		{
			System.out.println("Enter the id to to find bank account : ");
			int id = scanner.nextInt();
			
			BankAccount account = dao.findBankAccount(id);
			
			if (account != null) {
				System.out.println(account);
			}else {
				System.out.println("Bank account not found!");
			}
		}
		break;
		
		case 3 :
		{
			System.out.println("Enter the id to delete bank account : ");
			int id = scanner.nextInt();
			
			boolean res = dao.deleteBankAccountByPersonId(id);
			
			if (res) {
				System.out.println("Account deleted successfully!");
			}
			else {
				System.out.println("Acocunt not found!");
			}
		}
		break;
		
		case 4 : {
			
			System.out.println("Enter bank id to update");
			int id = scanner.nextInt();
			
			BankAccount a1 = (BankAccount) context.getBean("a11");
			
			dao.updateBankAccountById(id, a1);
		}
		break;
		
		case 5 : {
			
			List<BankAccount> list1 = dao.getAllBankAccount();
			
			for (BankAccount bankAccount : list1) {
				System.out.println(bankAccount);
				System.out.println(bankAccount.getPerson());
				System.out.println("\n");
			}
		}
		break;
		default:
			break;
		}
	}
}
