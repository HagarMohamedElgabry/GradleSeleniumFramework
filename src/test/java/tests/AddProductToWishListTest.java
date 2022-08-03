package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.WishlistPage;

public class AddProductToWishListTest extends TestBase
{
	SearchPage searchPage;
	ProductDetailsPage productDetails;
	WishlistPage wishlistObject;
	String productName = "Apple MacBook Pro 13-inch";

	@Test(priority=1)
	public void UserCanSearchForProductsWithAutoSuggest() throws InterruptedException {
		searchPage = new SearchPage(driver);
		searchPage.ProductSearchUsingAutoSuggest("MacB");
		productDetails = new ProductDetailsPage(driver);
		Assert.assertTrue(productDetails.productNamebreadCrumb.getText().contains(productName));
	}

	@Test(priority=2)
	public void UserCanAddProductToWishlist() throws InterruptedException {
		productDetails = new ProductDetailsPage(driver);
		productDetails.AddProductToWishlist();
		driver.navigate().to("http://demo.nopcommerce.com" + "/wishlist");
		wishlistObject = new WishlistPage(driver); 
		Assert.assertTrue(wishlistObject.wishlistHeader.isDisplayed());
		Assert.assertTrue(wishlistObject.productCell.getText().contains(productName));
	}

	@Test(priority=3)
	public void UserCanRemoveProductFromCart() {
		wishlistObject = new WishlistPage(driver); 
		wishlistObject.removeProductFromWishlist();
		Assert.assertTrue(wishlistObject.EmptyCartLbl.getText().contains("The wishlist is empty!"));
	}

}
