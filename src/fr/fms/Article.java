package fr.fms;

public class Article {
	
	private int idArticle;
	private String description;
	private String brand;
	private double unitaryPrice;
	
	

	public Article(int idArticle, String description, String brand, double unitaryPrice) {

		setIdArticle(idArticle);
		setDescription(description);
		setBrand(brand);
		setUnitaryPrice(unitaryPrice);
		
	}
	
	public Article(String description, String brand, double unitaryPrice) {

		setDescription(description);
		setBrand(brand);
		setUnitaryPrice(unitaryPrice);
		
	}
	
	public int getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getUnitaryPrice() {
		return unitaryPrice;
	}

	public void setUnitaryPrice(double unitaryPrice2) {
		this.unitaryPrice = unitaryPrice2;
	}


	@Override
	public String toString() {
		return "Article [idArticle=" + idArticle + ", description=" + description + ", brand=" + brand
				+ ", unitaryPrice=" + unitaryPrice + "]";
	}
	

}
