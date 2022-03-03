package org.javapatil.studproject.service.impl;

import java.util.List;

import org.javapatil.studproject.dao.StudentDao;
import org.javapatil.studproject.model.StudentForm;
import org.javapatil.studproject.service.StudentService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
  private StudentDao studentDao;

@Override
public List<StudentForm> getStudListService() {
	return studentDao.getStudList();
}

@Override
public StudentForm getStudBySnoService(int sno) {
	return studentDao.getStudBySno(sno);
}

@Override
public void insertStudService(StudentForm studentForm) {
  studentDao.insertStud(studentForm);	
}

@Override
public void updateStudService(StudentForm studentForm) {
	studentDao.updateStud(studentForm);	
}

@Override
public void deleteStudService(int sno) {
  studentDao.deletStud(sno);	
};
    
}
