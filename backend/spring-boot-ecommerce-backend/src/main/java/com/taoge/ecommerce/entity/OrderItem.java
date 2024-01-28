package com.taoge.ecommerce.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
public class OrderItem
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "unit_price")
	private BigDecimal unitPrice;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "product_id")
	private long productId;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	// ***** Getters/Setters *****

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getImageUrl()
	{
		return imageUrl;
	}

	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
	}

	public BigDecimal getUnitPrice()
	{
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice)
	{
		this.unitPrice = unitPrice;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public long getProductId()
	{
		return productId;
	}

	public void setProductId(long productId)
	{
		this.productId = productId;
	}

	public Order getOrder()
	{
		return order;
	}

	public void setOrder(Order order)
	{
		this.order = order;
	}
}
