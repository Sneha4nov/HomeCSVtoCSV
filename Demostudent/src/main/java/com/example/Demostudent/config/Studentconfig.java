package com.example.Demostudent.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Demostudent.model.Student;
import com.example.Demostudent.processor.StudentItemprocessor;
import com.example.Demostudent.reader.Reader;
import com.example.Demostudent.writer.Writer;

@Configuration
@EnableBatchProcessing
public class Studentconfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	@StepScope
	public Reader reader() throws Exception {
		return new Reader();
	}

	@Bean
	@StepScope
	public StudentItemprocessor processor() {
		return new StudentItemprocessor();
	}

	@Bean
	@StepScope
	public Writer writer() {
		return new Writer();
	}

	@Bean
	public Step step1() throws Exception {
		return stepBuilderFactory.get("step1").<Student, Student>chunk(10).reader(reader()).processor(processor())
				.writer(writer()).build();
	}

	@Bean
	public Job exportPersonJob() throws Exception {
		return jobBuilderFactory.get("exportStudentJob").incrementer(new RunIdIncrementer()).flow(step1()).end()
				.build();

	}

}
