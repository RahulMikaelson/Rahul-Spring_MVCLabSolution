package com.greatlearning.debate_event.services;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greatlearning.debate_event.entities.Students_Info;

@Service
public class ServiceImplementation implements Services {
	
	private SessionFactory sessionFactory;

	private Session session;

	@Autowired
	public ServiceImplementation(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException ex) {
			session = sessionFactory.openSession();
		}
	}

	@Override
	public List<Students_Info> findAll() {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		List<Students_Info> students = session.createQuery("from Students_Info").list();
		tx.commit();
		return students;
		
	}
	
	@Transactional
	@Override
	public Students_Info findById(int id) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		Students_Info student = session.get(Students_Info.class, id);
		tx.commit();
		return student;
	}
	
	
	@Override
	public void save(Students_Info student) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(student);
		tx.commit();
		
	}
	
	@Transactional
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		Students_Info student = session.get(Students_Info.class, id);
		session.delete(student);
		tx.commit();

		
	}
	
	
	}