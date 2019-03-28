package ru.chuikov.server.service.impl;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.chuikov.server.entity.Music;
import ru.chuikov.server.entity.User;
import ru.chuikov.server.repository.MusicRepository;
import ru.chuikov.server.service.AccountDetailsService;
import ru.chuikov.server.service.MusicService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MusicServiceImpl implements MusicService {


    @Autowired
    private AccountDetailsService accountDetailsService;

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private GridFsTemplate template;

    @Autowired
    private GridFSBucket bucket;

    @Override
    public void addMusic(MultipartFile file, Long id) throws IOException {
        Optional<User> user= accountDetailsService.getById(id);
        if(!user.isPresent()) throw new UsernameNotFoundException("User not found");
        if(file.isEmpty()) throw new FileNotFoundException("File not found");
        Music music=new Music();
        music.setName(file.getOriginalFilename());
        music.setCreated(new Date());
        music.setContentLength(file.getSize());
        music.setContentId(template.store(file.getInputStream(),
                file.getOriginalFilename(),music.getMimeType()).toString());
        music.setMimeType(file.getContentType());
        music.setUser(user.get());
        musicRepository.saveAndFlush(music);

    }

    @Override
    public InputStream getMusicStream(Long id) throws IOException {

        Optional<Music> music=musicRepository.getById(id);
        if(!music.isPresent()) throw new FileNotFoundException("File not found");

        GridFSFile gridFsdbFile = template.findOne(new Query(Criteria.where("_id").
                is(music.get().getContentId())));
        GridFSDownloadStream gridFSDownloadStream = bucket.openDownloadStream(gridFsdbFile.getObjectId());
        GridFsResource gridFsResource = new GridFsResource(gridFsdbFile,gridFSDownloadStream );
        return gridFsResource.getInputStream();

    }

    @Override
    public InputStream getMusicStream(Music music) throws IOException {
        return getMusicStream(music.getId());
    }

    @Override
    public Optional<Music> getMusicById(Long id) {
        return musicRepository.getById(id);
    }

    @Override
    public Optional<List<Music>> getUserMusicById(Long id, Long from, Long size) {
        return musicRepository.getByUserIdAndPagable(id, PageRequest.of(from.intValue(),size.intValue()));
    }

    @Override
    public void removeMusic(Music music) {

    }

    @Override
    public void removeMusic(Long id) {

    }

    @Override
    public Optional<List<Music>> getAll() {
        return musicRepository.getAll();
    }
}
