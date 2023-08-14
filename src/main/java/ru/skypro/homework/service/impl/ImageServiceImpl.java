package ru.skypro.homework.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.ImageService;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Value("${images.dir.path}")
    private String imagesDir;

    //добавление картинок для обьявлений и пользователей
    @Override
    public String saveImage(MultipartFile image, String name) {
        String fileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(image.getOriginalFilename());
        Path filePath = Path.of(imagesDir, fileName);
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, image.getBytes());
        }catch (IOException e){
            throw new RuntimeException("Ошибка загрузки файла", e);
        }
        log.trace("Файл загружен", fileName);
        return name + "/image/" + fileName;
    }

    // получение картинок
    @Override
    public byte[] getImage(String name) throws IOException {
        String path = imagesDir + "/" + name;
        File file = new File(path);
        if (file.exists()) {
            return Files.readAllBytes(Path.of(path));
        }
        return null;
    }

    // удаление картинок
    @Override
    public void deleteImage(String path) {
        if (path == null){
            return;
        }
        try {
            String fileName = path.substring(path.lastIndexOf('/'));
            File file = new File(imagesDir, fileName);
            Files.deleteIfExists(file.toPath());
            log.trace("Файл удален");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка удаления файла", e);
        }
    }
}
