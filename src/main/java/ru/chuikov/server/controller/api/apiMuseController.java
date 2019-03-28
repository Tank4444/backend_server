package ru.chuikov.server.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.chuikov.server.entity.Music;
import ru.chuikov.server.entity.UserPrincipal;
import ru.chuikov.server.service.AccountDetailsService;
import ru.chuikov.server.service.MusicService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/music")
public class apiMuseController {

    //services
    @Autowired
    private MusicService musicService;

    @Autowired
    private AccountDetailsService detailsService;


    //add
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addMusic(@RequestParam("music") MultipartFile file, UserPrincipal principal)
    {
        try {
            musicService.addMusic(file,principal.getId());
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get
    @RequestMapping(value = "/getAll",method = RequestMethod.GET )
    @ResponseBody
    public ResponseEntity<?> getAllMusic()
    {
        Optional<List<Music>> result=musicService.getAll();
        if(!result.isPresent())new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Object>(result.get(),HttpStatus.OK);
    }

    //get
    @RequestMapping(value = "/get/private/",method = RequestMethod.GET )
    @ResponseBody
    public ResponseEntity<?> getPravateByPage(UserPrincipal principal, @RequestParam String from, @RequestParam String size)
    {
        Long fromL=Long.valueOf(from);
        Long sizeL=Long.valueOf(size);

        Optional<List<Music>> result=musicService.getUserMusicById(principal.getId(),fromL,sizeL);
        if(!result.isPresent())new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Object>(result.get(),HttpStatus.OK);
    }

    /*

    @RequestMapping(value="/get/stream/{musicId}", method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE })
    @ResponseBody
    public void getMusicStream(@PathVariable("musicId") Long id, HttpServletResponse response) {
        Music music = musicService.getMusic(id);
        if (music != null) {
            InputStream inputStream = null;
            try {

                inputStream = musicService.getMusicStream(music);
                response.setContentType(music.getMimeType());
                byte[] buff= IOUtils.toByteArray(inputStream);
                response.getOutputStream().write(buff);

                /*
                HttpHeaders header = new HttpHeaders();
                header.setContentType(new MediaType("audio", "mp3"));
                header.setContentLength(music.getContentLength());
                response.getOutputStream().write(inputStream.read());
                return new HttpEntity<byte[]>(IOUtils.toByteArray(inputStream), header);

                return ResponseEntity.ok()
                        .contentLength(music.getContentLength())
                        .contentType(new MediaType("audio","*"))
                        .body(new InputStreamResource(inputStream));
                */
/*
} catch (IOException e) {
        }
        }
        }

@RequestMapping(value="/get/{musicId}", method = RequestMethod.GET)
public @ResponseBody Music getMusic(@PathVariable("musicId") Long id) {
        Music music = musicService.getMusic(id);
        if (music != null) { return music;}
        else return null;
        }

     */


    //delete
    @RequestMapping(value = "/delete/{musicId}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMusic(@PathVariable("musicId") Long id)
    {
        musicService.removeMusic(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
