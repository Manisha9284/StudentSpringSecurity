package org.javapatil.studproject.dao.impl;

import java.util.List;

import org.javapatil.studproject.dao.StudentDao;
import org.javapatil.studproject.dao.extractor.StudentExtractor;
import org.javapatil.studproject.dao.extractor.StudentListExtractor;
import org.javapatil.studproject.model.StudentForm;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class StudentDaoImpl implements StudentDao {
  private JdbcTemplate jdbcTemplate;
  private StudentListExtractor studentListExtractor; 
  private StudentExtractor studentExtractor; 

@Override
public List<StudentForm> getStudList() {
	return jdbcTemplate.query("Select * from students order by sno", studentListExtractor::extractData);
}

@Override
public StudentForm getStudBySno(int sno) {
	return jdbcTemplate.query("Select * from students where sno=" + sno, studentExtractor::extractData);
}

@Override
public void insertStud(StudentForm studentForm) {
		jdbcTemplate.update("Insert into students values(" + studentForm.getSno() + ",'" + studentForm.getSname() + "'," + studentForm.getAge() + ")");
}

@Override
public void updateStud(StudentForm studentForm) {
  jdbcTemplate.update("Update students set sname='" + studentForm.getSname() + "', age=" + studentForm.getAge() + " where sno=" + studentForm.getSno());	
}

@Override
public void deletStud(int sno) {
  jdbcTemplate.update("delete from students where sno=" + sno);	
}
    
}
