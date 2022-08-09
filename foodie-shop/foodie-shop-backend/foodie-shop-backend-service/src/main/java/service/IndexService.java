package service;

import pojo.vo.RenderCarouselVo;
import pojo.vo.RenderRootCat;
import pojo.vo.RenderSubCatVo;

import java.util.List;

/**
 * @author LEGION
 * @date 2022/05/09 17:19
 **/
public interface IndexService {
    public List<RenderCarouselVo> renderCarousel();

    public List<RenderRootCat> renderRootCats();

    public List<RenderSubCatVo> renderSubCats(Integer rootCatId);
}
