package com.priyanshu.compressor;

import com.priyanshu.compressor.service.CompressorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class JpgWebpCompressorApplication implements CommandLineRunner {

	@Autowired
	CompressorService compressorService;

	public static void main(String[] args) {
		SpringApplication.run(JpgWebpCompressorApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("Running JPG to Webp Compressor");
		Scanner sc = new Scanner(System.in);
		System.out.println("Please Enter Folder Path Which you want to compress...");
		String path = sc.nextLine();
		System.out.println("Path received as: "+ path);
		compressorService.compress(path);
		System.out.println("Compression Done");
	}
}
