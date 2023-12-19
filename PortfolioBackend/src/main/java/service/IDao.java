package service;

import java.util.List;


public interface IDao<T> {
	public  T getById(int id);
	public  int addNewRecord(T newRecord);
	public  List<T> updateRecord(T newRecord);
	public  int updateRecordSpecific(String newColumnData, int id);
	public  int deleteRecord(int id);
	public  List<T> getAllRecords();

}
