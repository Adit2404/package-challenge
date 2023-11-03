package com.mobiquity;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class PackerApplication {

	private final Packer packer;
	public PackerApplication(Packer packer) {
		this.packer= packer;
	}

	public static void main(String[] args) {
		SpringApplication.run(PackerApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return args -> {
			try {
				// Replace "your_input_file_path" with the actual file path
				String result = packer.pack("src/main/resources/Input");
				// Do something with the result
				System.out.println("Result: " + result);
			} catch (APIException | IOException e) {
				e.printStackTrace();
				// Handle the exception
			}
		};
	}

}
