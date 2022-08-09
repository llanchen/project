package service.impl;

import lombok.AllArgsConstructor;
import net.seehope.foodie.shop.mapper.CarouselMapper;
import net.seehope.foodie.shop.mapper.CategoryMapper;
import net.seehope.foodie.shop.pojo.Carousel;
import net.seehope.foodie.shop.pojo.Category;
import net.seehope.foodie.shop.pojo.vo.RenderCarouselVo;
import net.seehope.foodie.shop.pojo.vo.RenderRootCat;
import net.seehope.foodie.shop.pojo.vo.RenderSubCatVo;
import net.seehope.foodie.shop.service.IndexService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LEGION
 * @date 2022/05/09 17:19
 **/
@Service
@AllArgsConstructor
public class IndexServiceImpl implements IndexService {
    private CarouselMapper carouselMapper;
    private CategoryMapper categoryMapper;


    @Override
    public List<RenderCarouselVo> renderCarousel() {
        Example example = new Example(Carousel.class);
        Example.Criteria criteria = example.createCriteria();
        example.orderBy("sort").asc();
        criteria.andNotEqualTo("isShow", "0");
        List<Carousel> carousels = carouselMapper.selectByExample(example);

        List<RenderCarouselVo> result = new ArrayList<>();
        for (Carousel carousel : carousels) {
            RenderCarouselVo renderCarouselVo = new RenderCarouselVo();
            BeanUtils.copyProperties(carousel, renderCarouselVo);
            result.add(renderCarouselVo);
        }

        return result;
    }

    @Override
    public List<RenderRootCat> renderRootCats() {
        Category category = new Category();
        category.setType(1);
        List<Category> categorys = categoryMapper.select(category);
        List<RenderRootCat> result = new ArrayList<>();
        for (Category cat : categorys) {
            RenderRootCat renderRootCat = new RenderRootCat();
            BeanUtils.copyProperties(cat, renderRootCat);
            result.add(renderRootCat);
        }

        return result;
    }

    @Override
    public List<RenderSubCatVo> renderSubCats(Integer rootCatId) {
        return categoryMapper.renderSubCatVos(rootCatId);
    }
}
