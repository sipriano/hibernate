package com.rodrigo.principal;

import com.rodrigo.hibernate.Test;

import java.util.Calendar;
import java.util.List;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManageTest {
	private static SessionFactory factory;
	
	public static void main(String[] args) {
		
		try {
			factory = (SessionFactory) new Configuration()
					.configure("/com/rodrigo/principal/hibernate.cfg.xml").addAnnotatedClass(Test.class)
					.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object. " + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
		ManageTest MT = new ManageTest();
		
		Integer empID1 = MT.addTest("alguma", false, null);
		
		MT.listTests();
		
		MT.updateTest(empID1, "nada");
		
		//MT.deleteTest(empID1);
		
		MT.listTests();
	}
	
	public Integer addTest(String descricao, boolean finalizacao, Calendar dataFinalizacao ) {
	
		Session session = factory.openSession();
		Transaction tx = null;
		Integer testID = null;
		
		try{
			tx = session.beginTransaction();
			Test test = new Test();
			test.setDescricao(descricao);
			test.setFinalizacao(finalizacao);
			test.setDataFinalizacao(dataFinalizacao);
			testID = (Integer) session.save(test);
			tx.commit();
		} catch(HibernateException e) {
			if(tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			
			session.close();
		}
		return testID;
	}
	
	
	public void listTests() {
		
		Session session = factory.openSession();
		Transaction tx = null;
		
		try {
			
			tx = session.beginTransaction();
			List <Test> tests = session.createQuery("FROM test").list();
			
			
			for(Iterator<Test> iterator = tests.iterator(); iterator.hasNext();) {
				System.out.println(iterator.next());
			}
			tx.commit();
		} catch (HibernateException e) {
			if(tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			
			session.close();
		}
		
	}
	
	public void updateTest(Integer TestID, String descricao) {
		Session session = factory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Test test = (Test)session.get(Test.class, TestID);
			test.setDescricao(descricao);
			session.update(test);
			tx.commit();
			System.out.println("+++++++++++++ " + TestID);
		} catch(HibernateException e) {
			if(tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void deleteTest(Integer TestID) {
		Session session = factory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Test test = (Test)session.get(Test.class, TestID);
			session.delete(test);
			tx.commit();
		} catch(HibernateException e) {
			if(tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
}
