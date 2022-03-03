package org.javapatil.studproject.dao.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.javapatil.studproject.model.StudentForm;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

@Component
public class StudentExtractor implements ResultSetExtractor<StudentForm> {

	@Override
	public StudentForm extractData(ResultSet rs) throws SQLException, DataAccessException {
		StudentForm studentForm=new StudentForm();
		
		if(rs.next())
		{
			studentForm.setSno(rs.getInt("sno"));
			studentForm.setSname(rs.getString("sname"));
			studentForm.setAge(rs.getInt("age"));
		}
		
		return studentForm;
	}
 
}
