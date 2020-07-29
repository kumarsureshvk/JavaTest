package com.test.wesure;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class FileUploadGCP {

	@RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
	public ModelAndView fileUpload() {
		System.out.println("----------");
		return new ModelAndView("fileUpload");
	}
	
	// School Logo Upload Start
		@RequestMapping(value = "fileUpload", method = RequestMethod.POST)
		public String saveDealerDetail(HttpServletRequest request, HttpServletResponse response, HttpSession session,
				@RequestParam("logoUpload") MultipartFile uploadfile) throws IOException {

			// Get the filename and build the local file path (be sure that the
			// application have write permissions on such directory)
			String originaleRilename = uploadfile.getOriginalFilename();
		/*
		 * String directory = context.getRealPath("/") + "schoolLogo";
		 * System.out.println("Upload dir---<>"+directory);
		 */
			String fileExtention = getExtensionByStringHandling(originaleRilename).get();
//			commonFileUpload(uploadfile, , originaleRilename + "");
			
			uploadObject("itlpics", "itlpics_bucket", "VKS", uploadfile.getName());
			return "Success";

		}
		
		// get file Extension start
		public static Optional<String> getExtensionByStringHandling(String filename) {
			return Optional.ofNullable(filename).filter(f -> f.contains("."))
					.map(f -> f.substring(filename.lastIndexOf(".") + 1));
		}
		// get file Extension end
		
//		file Upload GCP bucket
		public static void uploadObject(
			      String projectId, String bucketName, String objectName, String filePath) throws IOException {
			    // The ID of your GCP project
			    // String projectId = "your-project-id";

			    // The ID of your GCS bucket
			    // String bucketName = "your-unique-bucket-name";

			    // The ID of your GCS object
			    // String objectName = "your-object-name";

			    // The path to your file to upload
			    // String filePath = "path/to/your/file"

			    Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
			    BlobId blobId = BlobId.of(bucketName, objectName);
			    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
			    storage.create(blobInfo, Files.readAllBytes(Paths.get(filePath)));

			    System.out.println(
			        "File " + filePath + " uploaded to bucket " + bucketName + " as " + objectName);
			  }

		
		
}
