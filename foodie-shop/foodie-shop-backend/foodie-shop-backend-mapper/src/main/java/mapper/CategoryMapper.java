package mapper;

import net.seehope.foodie.shop.pojo.Category;
import net.seehope.foodie.shop.pojo.vo.RenderSubCatVo;

import java.util.List;

/**
* 通用 Mapper 代码生成器
*
* @author mapper-generator
*/
public interface CategoryMapper extends tk.mybatis.mapper.common.Mapper<Category> {

     List<RenderSubCatVo> renderSubCatVos(Integer rootCatId);

}




