package org.javapatil.studproject;

import org.javapatil.studproject.util.fileupload.file.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})

public class PractprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PractprojectApplication.class, args);
	}

}
