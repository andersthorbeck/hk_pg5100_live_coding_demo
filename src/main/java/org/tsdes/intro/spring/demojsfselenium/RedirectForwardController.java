package org.tsdes.intro.spring.demojsfselenium;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectForwardController {
    @GetMapping("/")
    public String forwardFromRootToIndex() {
        return "forward:index.xhtml";
    }
}
