package services;

import java.util.List;

import javax.ejb.Local;

@Local
public interface Service<T> {

	public T getEntity(long id, Class<T> c);

	public void setEntity(T article);
	
	public void mergeEntity(T article);
	
	public List<T> getAll();

}
