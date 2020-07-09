package com.wesure.dream.ITCPics;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

@RestController

public class Controller {

	@Autowired
	private ServletContext context;

	@RequestMapping("/")
	public String name() {
		return "Welcome to Home Git Test";
	}

	@RequestMapping("/index")
	public String name1() {
		return "Welcome to Index";
	}

	@RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
	public ModelAndView fileUpload() {
		System.out.println("----------");
		return new ModelAndView("fileUpload");
	}

	// School Logo Upload Start
	@RequestMapping(value = "fileUpload", method = RequestMethod.POST)
	public String saveDealerDetail(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam("logoUpload") MultipartFile uploadfile) {

		// Get the filename and build the local file path (be sure that the
		// application have write permissions on such directory)
		String originaleRilename = uploadfile.getOriginalFilename();
		String directory = context.getRealPath("/") + "schoolLogo";
		System.out.println(directory);
		String fileExtention = getExtensionByStringHandling(originaleRilename).get();
		commonFileUpload(uploadfile, directory, originaleRilename + "");

		return "Success";

	}
	// School Logo Upload End

	// File Upload Start
	public static boolean commonFileUpload(MultipartFile uploadfile, String directory, String filename) {
		try {
			String filepath = Paths.get(directory, filename).toString();

			File directoryFile = new File(directory);
			if (!directoryFile.exists()) {
				directoryFile.mkdir();
				// If you require it to make the entire directory path including parents,
				// use directory.mkdirs(); here instead.
			}

			// Save the file locally
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			stream.write(uploadfile.getBytes());
			stream.close();
		} catch (Exception e) {
			return false;
		}
		return true;

	}
	// File Upload End

	// get file Extension start
	public static Optional<String> getExtensionByStringHandling(String filename) {
		return Optional.ofNullable(filename).filter(f -> f.contains("."))
				.map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}
	// get file Extension end
}
