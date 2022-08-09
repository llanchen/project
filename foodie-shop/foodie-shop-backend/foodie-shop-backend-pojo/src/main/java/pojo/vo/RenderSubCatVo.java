package pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @author LEGION
 * @date 2022/05/10 15:03
 **/
@Data
public class RenderSubCatVo {
    private String name;
    private List<SubCatVo> subCatList;
}
