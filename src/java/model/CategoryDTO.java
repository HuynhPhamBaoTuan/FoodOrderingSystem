/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LENOVO
 */
public class CategoryDTO {
    private Category category;
    private String avtImg;

    public CategoryDTO(Category category, String avtImg) {
        this.category = category;
        this.avtImg = avtImg;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getAvtImg() {
        return avtImg;
    }

    public void setAvtImg(String avtImg) {
        this.avtImg = avtImg;
    }
    
    
}
