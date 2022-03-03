package org.javapatil.studproject.dao;

import java.util.List;

import org.javapatil.studproject.model.StudentForm;

public interface StudentDao {
	List<StudentForm> getStudList();
	StudentForm getStudBySno(int sno);
	void insertStud(StudentForm studentForm);
	void updateStud(StudentForm studentForm);
	void deletStud(int sno);
}
