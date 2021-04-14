package com.example.springsecurity.controller;

import com.example.springsecurity.model.Notice;
import com.example.springsecurity.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoticesController {

    @Autowired
    private NoticeRepository noticeRepository;

    @GetMapping("/notices")
    public List<Notice> getNotices() {
        return noticeRepository.findAllActiveNotices();
    }
}
