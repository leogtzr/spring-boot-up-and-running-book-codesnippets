package com.sbur.controller;

import com.sbur.domain.Droid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/droid")
public class DroidController {

    private final Droid droid;

    public DroidController(final Droid droid) {
        this.droid = droid;
    }

    @GetMapping
    public Droid getDroid() {
        return droid;
    }

}
