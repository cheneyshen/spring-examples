package com.tutorialspoint;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class StudentJDBCTemplate implements StudentDAO{
	private JdbcTemplate jdbcTemplateObject;
	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(String name, Integer age, Integer marks, Integer year) {
		try {
			String SQL1 = "insert into Student (name, age) values (?, ?)";
			jdbcTemplateObject.update( SQL1, name, age);
			// Get the latest student id to be used in Marks table
			String SQL2 = "select max(id) from Student";
			int sid = jdbcTemplateObject.queryForObject( SQL2, Integer.class );
			String SQL3 = "insert into Marks(sid, marks, year) " + 
	                "values (?, ?, ?)";
			jdbcTemplateObject.update( SQL3, sid, marks, year);
			System.out.println("Created Name = " + name + ", Age = " + age);
			// to simulate the exception.
			/* Issue:	
			 * WARNING: Exception encountered during context initialization -
				cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: 
				Error creating bean with name 'dataSource' defined in class path resource [Beans.xml]:
				BeanPostProcessor before instantiation of bean failed; nested exception is 
				org.springframework.beans.factory.BeanCreationException: 
				Error creating bean with name 'org.springframework.aop.support.DefaultBeanFactoryPointcutAdvisor#0': 
				Cannot resolve reference to bean 'createOperation' while setting bean property 'pointcut'; 
				nested exception is org.springframework.beans.factory.BeanCreationException: 
				Error creating bean with name 'createOperation': Instantiation of bean failed; 
				nested exception is org.springframework.beans.BeanInstantiationException: 
				Failed to instantiate [org.springframework.aop.aspectj.AspectJExpressionPointcut]: 
				No default constructor found; nested exception is java.lang.NoClassDefFoundError: 
				org/aspectj/weaver/reflect/ReflectionWorld$ReflectionWorldException
				Exception in thread "main" org.springframework.beans.factory.BeanCreationException:
				Error creating bean with name 'dataSource' defined in class path resource [Beans.xml]: 
				BeanPostProcessor before instantiation of bean failed; nested exception is
				org.springframework.beans.factory.BeanCreationException: Error creating bean with 
				name 'org.springframework.aop.support.DefaultBeanFactoryPointcutAdvisor#0': 
				Cannot resolve reference to bean 'createOperation' while setting bean property 'pointcut'; 
				nested exception is org.springframework.beans.factory.BeanCreationException: 
				Error creating bean with name 'createOperation': Instantiation of bean failed; 
				nested exception is org.springframework.beans.BeanInstantiationException: 
				Failed to instantiate [org.springframework.aop.aspectj.AspectJExpressionPointcut]: 
				No default constructor found; nested exception is java.lang.NoClassDefFoundError: 
				org/aspectj/weaver/reflect/ReflectionWorld$ReflectionWorldException
				Solution:
				添加aspectjweaver.jar
			*/
			throw new RuntimeException("simulate Error condition") ;
		} catch (DataAccessException e) {
			System.out.println("Error in creating record, rolling back");
			throw e;
		}
	}

	@Override
	public List<StudentMarks> listStudents() {
		String SQL = "select * from Student, Marks where Student.id=Marks.sid";
		List <StudentMarks> studentMarks=jdbcTemplateObject.query(SQL, 
				new StudentMarksMapper());
		return studentMarks;
	}

}
