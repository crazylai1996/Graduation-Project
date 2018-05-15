package gdou.laiminghai.ime.model.entity;

public class ProductPicture {
    private Long picId;

    private Long productId;

    private String pictureUrl;

    public ProductPicture(){
    	
    }
    
    public ProductPicture(Long productId,String pictureUrl) {
    	this.productId = productId;
    	this.pictureUrl = pictureUrl;
    }
    
    public Long getPicId() {
        return picId;
    }

    public void setPicId(Long picId) {
        this.picId = picId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
    }
}