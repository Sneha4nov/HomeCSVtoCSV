package com.example.Demostudent.processor;

import org.springframework.batch.item.ItemProcessor;

import com.example.Demostudent.model.Student;

public class StudentItemprocessor implements ItemProcessor<Student, Student> {

	@Override
	public Student process(Student student) throws Exception {

		float avg, perc, totalMarks = 0;

		totalMarks = (student.getMathsMarks() + student.getScienceMarks() + student.getHindiMarks()
				+ student.getDbmsMarks() + student.getEnglishMarks());
		setNames(new String[] { "totalMarks" });

		avg = (totalMarks / 5);

		if (avg > 80) {
			student.setGrade("A");
		} else if (avg > 60 && avg <= 80) {
			student.setGrade("B");

		} else if (avg > 40 && avg <= 60) {
			student.setGrade("C");
		} else {
			student.setGrade("D");
		}

		avg = totalMarks / 5;
		perc = (totalMarks / 500) * 100;
		student.setPercantage(perc);
		student.setTotalMarks(totalMarks);

		return student;
	}

	private void setNames(String[] strings) {

	}

}
