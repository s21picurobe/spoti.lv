package lv.spoti.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "spot_table")
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Spot {
	@Column(name = "spot_name")
	private String name;
	@Column(name = "spot_description_eng")
	private String descriptionEng;
	@Column(name = "spot_description_lv")
	private String descriptionLv;
	@Column(name = "spot_city")
	private String city;
	@Column(name = "spot_latitude")
	private float latitude;
	@Column(name = "spot_longitude")
	private float longitude;
	
	@ManyToOne
	 @JsonBackReference
	@JoinColumn(name="idc", nullable=false)
	private Category category;
	
	@Column(name = "spot_date")
	private LocalDate date;
	private String formattedDate;
	@Column(name = "spot_clicks")
	private int clicks;
	@Column(name = "spot_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ids;
	
	@ManyToOne
	@JoinColumn(name="user", nullable=false)
	private User user;
	
	//GETTERS AND SETTERS
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescriptionEng() {
		return descriptionEng;
	}
	public void setDescriptionEng(String descriptionEng) {
		this.descriptionEng = descriptionEng;
	}
	public String getDescriptionLv() {
		return descriptionLv;
	}
	public void setDescriptionLv(String descriptionLv) {
		this.descriptionLv = descriptionLv;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getFormattedDate() {
		return formattedDate;
	}
	public void setFormattedDate() {
		this.formattedDate = date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear();
	}
	public int getClicks() {
		return clicks;
	}
	public void click() {
		this.clicks += 1;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getId() {
		return ids;
	}
	
	//CONSTRUCTORS
	public Spot(String name, String descriptionEng, String descriptionLv, String city, float latitude,
			float longitude, Category category, User user) {
		super();
		this.name = name;
		this.descriptionEng = descriptionEng;
		this.descriptionLv = descriptionLv;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
		this.category = category;
		this.clicks = 0;
		this.date = LocalDate.now();
		this.formattedDate = date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear();
		this.user = user;
	}
	public Spot() {
		super();
		this.name = "Default";
		this.descriptionEng = "Default";
		this.descriptionLv = "Default";
		this.city = "Default";
		this.latitude = 57.415249f;
		this.longitude = 21.634908f;
		this.category = new Category();
		this.clicks = 0;
		this.date = LocalDate.now();
		this.formattedDate = date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear();
		this.user = new User();
	}
	//TO STRING
	@Override
	public String toString() {
		return "Spot [name=" + name + ", descriptionEng=" + descriptionEng + ", descriptionLv=" + descriptionLv
				+ ", city=" + city + ", latitude=" + latitude + ", longitude=" + longitude + ", category=" + category
				+ ", date=" + date + ", formattedDate=" + formattedDate + ", clicks=" + clicks + ", ids=" + ids + "]";
	}
}
