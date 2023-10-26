package lv.spoti.models;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "category_table")
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category {
	
	@Column(name = "category_name")
	private String name;
	@Column(name = "category_description_eng")
	private String descriptionEng;
	@Column(name = "category_description_lv")
	private String descriptionLv;
	@Column(name = "category_color_code")
	private String colorCode;
	
	@OneToMany(mappedBy="category")
	@JsonManagedReference
	private Collection<Spot> spots;
	
	@Column(name = "category_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idc;
	
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
	public String getColorCode() {
		return colorCode;
	}
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	public int getId() {
		return idc;
	}
	
	public Collection<Spot> getSpots() {
		return spots;
	}
	
	//CONSTRUCTORS
	public Category(String name, String descriptionEng, String descriptionLv, String colorCode) {
		super();
		this.name = name;
		this.descriptionEng = descriptionEng;
		this.descriptionLv = descriptionLv;
		this.colorCode = colorCode;
	}
	public Category() {
		super();
		this.name = "Default";
		this.descriptionEng = "Default";
		this.descriptionLv = "Default";
		this.colorCode = "#111111";
	}
	
	//TO STRING
	@Override
	public String toString() {
		return "Category [name=" + name + ", descriptionEng=" + descriptionEng + ", descriptionLv=" + descriptionLv
				+ ", colorCode=" + colorCode + ", id=" + idc + "]";
	}
}
