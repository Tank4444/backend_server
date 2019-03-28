package ru.chuikov.server.service;

import org.springframework.web.multipart.MultipartFile;
import ru.chuikov.server.entity.Music;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface MusicService {
    //add
    void addMusic(MultipartFile file, Long id) throws IOException;

    //stream
    InputStream getMusicStream(Long id) throws IOException;
    InputStream getMusicStream(Music music) throws IOException;

    //get music by id
    Optional<Music> getMusicById(Long id);
    Optional<List<Music>> getUserMusicById(Long id,Long from, Long size);

    //remove
    void removeMusic(Music music);
    void removeMusic(Long id);

    //all
    Optional<List<Music>> getAll();
}
