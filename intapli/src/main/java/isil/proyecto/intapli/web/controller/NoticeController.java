package isil.proyecto.intapli.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import isil.proyecto.intapli.domain.Notice;
import isil.proyecto.intapli.domain.service.NoticeService;

import java.util.List;

@RestController
@RequestMapping("/api/notices")
public class NoticeController {
	
	@Autowired
    private NoticeService noticeService;

    @GetMapping("/all")
	@ApiOperation(value = "Get all notice", authorizations = { @Authorization(value = "JWT")})
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Notice>> getAll(){
        return new ResponseEntity<>(noticeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
	@ApiOperation(value = "Search a notice with an ID", authorizations = { @Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Notice not found")
    })
    public ResponseEntity<Notice> getNotice(@ApiParam(value = "The id of the notice", required = true, example = "1")
											@PathVariable int id){

												return noticeService.getNotice(id)
														.map(notice -> new ResponseEntity<>(notice, HttpStatus.OK))
														.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

    @GetMapping("/user/{userId}")
	@ApiOperation(value = "Get notice by id user", authorizations = { @Authorization(value = "JWT")})
    public ResponseEntity<List<Notice>> getByUser(@PathVariable int userId){

        return noticeService.getByNotices(userId)
                .map(notices -> new ResponseEntity<>(notices, HttpStatus.OK) )
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping("/save")
	@ApiOperation(value = "Save notice", authorizations = { @Authorization(value = "JWT")})
    public ResponseEntity<Notice> save(@RequestBody Notice notice){
        return new ResponseEntity<Notice>(noticeService.save(notice), HttpStatus.CREATED);
    }

    @GetMapping("/delete/{id}")
	@ApiOperation(value = "Delete notice", authorizations = { @Authorization(value = "JWT")})
    public ResponseEntity delete(@PathVariable int id){

        if(noticeService.delete(id)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }
	
	
	
}