package gdou.laiminghai.ime.dao.mapper;

import gdou.laiminghai.ime.model.entity.ProductPicture;
import java.util.List;

public interface ProductPictureMapper {
    int deleteByPrimaryKey(Long picId);

    int insert(ProductPicture record);

    ProductPicture selectByPrimaryKey(Long picId);

    List<ProductPicture> selectAll();

    int updateByPrimaryKey(ProductPicture record);
}