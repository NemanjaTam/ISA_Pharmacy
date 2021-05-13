package com.tim40.tim40.model;
import javax.persistence.*;

@Entity
public class Price {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(nullable = false)
	private double price; 
	
	@ManyToOne  //lek moze da ima vise cena ali cena-stavka cenovnika moze imati samo jedan lek
	@JoinColumn(name = "medication_id", nullable = false)  //unidirekciona veza
	private Medication medication; 

	@ManyToOne //vlasnik veze ima i strani kljuc
	@JoinColumn(name = "price_list_id", nullable = false)
	private PriceList priceList;
	
	public Price() {}

	public Price(Long id, double price, Medication medication, PriceList priceList) {
		super();
		this.id = id;
		this.price = price;
		this.medication = medication;
		this.priceList = priceList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Medication getMedication() {
		return medication;
	}

	public void setMedication(Medication medication) {
		this.medication = medication;
	}

	public PriceList getPriceList() {
		return priceList;
	}

	public void setPriceList(PriceList priceList) {
		this.priceList = priceList;
	}

	
	
	
}
