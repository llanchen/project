package net.seehope.foodie.shop.web.controller;

import common.utils.JSONResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.IndexService;

/**
 * @author LEGION
 * @date 2022/05/09 16:57
 **/
@RestController
@RequestMapping("/index")
@Slf4j
@AllArgsConstructor
public class IndexController {

    private IndexService indexService;

    @GetMapping("/carousel")
    public JSONResult renderCarousel() {
        return JSONResult.ok(indexService.renderCarousel());
    }

    @GetMapping("/cats")
    public JSONResult renderRootCats() {
        return JSONResult.ok(indexService.renderRootCats());
    }

    @GetMapping("/subCat/{rootCatId}")
    public JSONResult renderSubCats(@PathVariable Integer rootCatId) {
        return JSONResult.ok(  indexService.renderSubCats(rootCatId));

    }
}
