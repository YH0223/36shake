package com.example.NewWeb.modules;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
public class ImageService {
    public byte[] downloadImage(String imageUrl) throws IOException {
        try (InputStream in = new URL(imageUrl).openStream()) {
            // 이미지 URL 출력
            System.out.println("Image URL: " + imageUrl);

            // 이미지를 바이트 배열로 읽어오기
            return in.readAllBytes();
        } catch (IOException e) {
            // 예외 발생 시 콘솔에 출력
            System.err.println("Failed to download image from URL: " + imageUrl);
            throw e;
        }
    }
}