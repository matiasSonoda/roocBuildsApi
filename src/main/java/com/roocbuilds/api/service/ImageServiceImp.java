package com.roocbuilds.api.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.roocbuilds.api.model.dto.ImageUploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImageServiceImp implements IImageService{

    private final Cloudinary cloudinary;

    @Override
    public ImageUploadResponse saveImage(MultipartFile file) {
        try{
            Map<?,?> uploadParams = ObjectUtils.asMap(
                    "use_filename",true,
                    "unique_filename",true,
                    "overwrite",true,
                    "transformation", new Transformation<>().width(1920).height(1080).crop("limit")
                    );

            Map uploadResult = cloudinary.uploader()
                    .upload(
                        file.getBytes(),
                        uploadParams
                        );

            String url = (String) uploadResult.get("url");
            String publicId = (String) uploadResult.get("public_id");
            Integer width = (Integer) uploadResult.get("width");
            Integer height = (Integer) uploadResult.get("height");

            String feedUrl = cloudinary.url()
                    .transformation(new Transformation<>()
                            .width(800)
                            .height(450)
                            .crop("fill")
                            .gravity("auto")
                            .quality("auto")
                            .fetchFormat("auto")
                    )
                    .generate(publicId);

            String originalUrl = cloudinary.url()
                    .transformation(new Transformation<>()
                            .quality("auto")
                            .fetchFormat("auto")
                    ).generate(publicId);

            return new ImageUploadResponse(publicId,feedUrl,originalUrl,width,height);


        }catch(IOException ex){
            throw new RuntimeException("Error al guardar la imagen en el servidor: " + ex.getMessage(), ex);
        }
    }
}
