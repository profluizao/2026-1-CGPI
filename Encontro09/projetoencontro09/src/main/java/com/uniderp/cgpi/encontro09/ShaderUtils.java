package com.uniderp.cgpi.encontro09;

import java.nio.file.*;
import java.io.IOException;

public class ShaderUtils {
    public static String loadShader(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível carregar o arquivo: " + path);
        }
    }
}