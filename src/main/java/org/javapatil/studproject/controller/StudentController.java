package org.javapatil.studproject.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.javapatil.studproject.model.StudentForm;
import org.javapatil.studproject.service.StudentService;
import org.javapatil.studproject.util.fileupload.exception.FileStorageService;
import org.javapatil.studproject.util.fileupload.file.UploadFileResponse;
import org.javapatil.studproject.util.security.admin.BaseResource;
import org.javapatil.studproject.util.security.admin.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.net.HttpHeaders;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "v1/admin/students")
@CrossOrigin(value="*")
public class StudentController extends BaseResource {
    private StudentService studentService;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private FileStorageService fileStorageService;
	
    @GetMapping
	public List<StudentForm> getStudListCtrl(@RequestHeader("access-token") String token, HttpServletRequest request)
	{
    	User user = getUser(request);

	     if(user==null) return null;

    	
		return studentService.getStudListService();
	}
	
    @GetMapping("/{snum}")
    public StudentForm getStudBySnoCtrl(@RequestHeader("access-token") String token, HttpServletRequest request,@PathVariable(value = "snum") int sno)
    {
    	User user = getUser(request);

	     if(user==null) return null;
	     
    	return studentService.getStudBySnoService(sno);
    }
    
    @PostMapping
    public void insertStudCtrl(@RequestHeader("access-token") String token, HttpServletRequest request,@RequestBody StudentForm studentForm)
    {
    	studentService.insertStudService(studentForm);
    }
    
    @PutMapping
    public void updateStudCtrl(@RequestHeader("access-token") String token, HttpServletRequest request,@RequestBody StudentForm studentForm)
    {
    	studentService.updateStudService(studentForm);
    }

    @DeleteMapping("/{snum}")
    public void deleteStudCtrl(@RequestHeader("access-token") String token, HttpServletRequest request,@PathVariable(value = "snum") int sno)
    {
    	studentService.deleteStudService(sno);
    }
    
    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file, @RequestHeader("access-token") String token, HttpServletRequest request) {
    	User user = getUser(request);
    	if(user==null) return null;
    	
    	//String newFileName="MyFile.jpg";
        //return upload(file, newFileName);
        return upload(file);
    }

    //public UploadFileResponse upload(MultipartFile file, String newFileName){
    public UploadFileResponse upload(MultipartFile file){
        System.out.println("inside upload");
        String fileName = fileStorageService.storeFile(file);

        System.out.println("File Name: " + fileName);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        System.out.println("before return upload filr response");

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
        //return  null;
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request, @RequestHeader("access-token") String token) {
    	User user = getUser(request);
    	if(user==null) return null;
    	
    	// Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
