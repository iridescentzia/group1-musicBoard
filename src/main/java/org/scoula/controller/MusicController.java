package org.scoula.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.domain.MusicVO;
import org.scoula.dto.MusicDTO;
import org.scoula.service.MusicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RequestMapping("/api/music")
@RequiredArgsConstructor
public class MusicController {
    final MusicService service;

    @GetMapping("/list")  // GET /api/music/list
    public void list(Model model) {
        log.info("list");
        model.addAttribute("list", service.getList());
    }

    @GetMapping("/create")  // GET /api/music/create
    public void create() {
        log.info("CREATE");
    }

    @GetMapping({"/get", "/update"})  // GET /api/music/get?id={id} 또는 /api/music/update?id={id}
    public void get(@RequestParam("id") int id, Model model) {
        log.info("GET or UPDATE");
        model.addAttribute("music", service.get(id));
    }

    @PostMapping("/create")  // POST /api/music/create
    public String create(MusicVO music) {
        log.info("CREATE : " + music);
        service.create(music);
        return "redirect:/api/music/list";
    }

    @PostMapping("/update")  // POST /api/music/update
    public String update(MusicVO music) {
        log.info("UPDATE : " + music);
        service.update(music);
        return "redirect:/api/music/list";
    }

    @PostMapping("/delete")  // POST /api/music/delete?id={id}
    public String delete(@RequestParam("id") int id) {
        log.info("DELETE : " + id);
        service.delete(id);
        return "redirect:/api/music/list";
    }
}
