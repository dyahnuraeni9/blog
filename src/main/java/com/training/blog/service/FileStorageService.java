package com.training.blog.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.training.blog.configuration.FileStorageProperties;
import com.training.blog.dao.BlogDao;
import com.training.blog.dto.response.ResponseBaseDTO;
import com.training.blog.dto.response.ResponseUploadFileDTO;
import com.training.blog.exception.FileStorageException;
import com.training.blog.exception.MyFileNotFoundException;
import com.training.blog.model.Blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class FileStorageService {

    @Autowired
    private BlogDao blogDao;

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public ResponseEntity storeFile(MultipartFile file, Integer id) {
        ResponseBaseDTO<ResponseUploadFileDTO> responseBaseDTO = new ResponseBaseDTO<>();
        ResponseUploadFileDTO responseUploadFileDTO = new ResponseUploadFileDTO();
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
           Blog blog = blogDao.findById(id).orElse(null);
           if (blog == null){
                responseBaseDTO = new ResponseBaseDTO<ResponseUploadFileDTO>(404, false, "blog Not Found", null);
                return new ResponseEntity<>(responseBaseDTO, HttpStatus.NOT_FOUND);
            }
           
          
           // Copy file to the target location (Replacing existing file with the same name)
           Path targetLocation = this.fileStorageLocation.resolve(fileName);
           Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

           String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
           .path("/api/downloadFile/")
           .path(fileName)
           .toUriString();

           blog.setImageUrl(fileDownloadUri);
           blog.setImage(file.getBytes());
           blogDao.save(blog);

           responseUploadFileDTO.setImageURL(fileDownloadUri);
           responseUploadFileDTO.setType(file.getContentType());
           responseUploadFileDTO.setSize(file.getSize());

           responseBaseDTO = new ResponseBaseDTO<ResponseUploadFileDTO>(200, true, "success", responseUploadFileDTO);
           return ResponseEntity.ok(responseBaseDTO);
        } catch (IOException ex) {
            responseBaseDTO = new ResponseBaseDTO<ResponseUploadFileDTO>(500, false, "Failed upload image", null);
                return new ResponseEntity<>(responseBaseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
       
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}