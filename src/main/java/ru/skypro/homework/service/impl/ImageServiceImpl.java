package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    @Value("${image.dir.path}")
    private String imagesDir;

    @Override
    public String saveImage(MultipartFile image, String name) {
        return null;
    }

    @Override
    public byte[] getImage(String name) throws IOException {
        return new byte[0];
    }

    @Override
    public void deleteImage(String path) {

    }
}
