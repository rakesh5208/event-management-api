package com.learningwithrakesh.EventManagement.repository;

import java.util.List;

/**
 *
 */
public interface CurdRepository<T> {
	/**
	 * get all as list
	 * 
	 * @return
	 */
	public List<T> findAll();

	/**
	 * save and flush to the db
	 * 
	 * @param event
	 * @return
	 */
	public T save(T event);

	/**
	 * get on by id
	 * 
	 * @param id
	 * @return
	 */
	public T getOne(long id);

	/**
	 * delete the records by id
	 * 
	 * @param id
	 */
	public void deleteById(long id);
}
