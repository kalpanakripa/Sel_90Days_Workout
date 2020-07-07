package week2.seleniumbasics.workout;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Zalando {

	public static void main(String[] args) throws InterruptedException {
		
		//Setting up browser and driver property
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		
		//create an object for chrome driver
		ChromeDriver driver = new ChromeDriver();
		
		//Launch URL
		driver.get("https://www.zalando.com/");
			Thread.sleep(1000);
			
			//Get the Alert text and print it  
				Alert alert = driver.switchTo().alert();
				
				String alertText = alert.getText(); //Get the Alert text
				System.out.println("Alert Message displayed is: "+alertText); //print the alert text
				
				WebDriverWait wait = new WebDriverWait(driver,20);
				
			// Close the Alert box and click on Zalando.uk 
				alert.accept(); //click ok button on alert
				
				driver.manage().window().maximize(); //maximize the window
			
				driver.findElementByXPath("//a[text()='Zalando.uk']").click(); //click on Zalando.uk 
			
			//Click Women--> Clothing and click Coats
				driver.findElementByXPath("(//span[text()='Women'])[1]").click();
				
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'That’s OK')]")));
					driver.findElementByXPath("//button[contains(text(),'That’s OK')]").click();  //click that's ok button at the bottom
				} catch (Exception e) {
					
					e.printStackTrace();
					
				}
				
				// click on Clothing and click Coats
				driver.findElementByXPath("//span[text()='Clothing']").click();
				
				driver.findElementByLinkText("Coats").click();
				
				Thread.sleep(4000);
				
			//Choose Material as cotton (100%) and  
				Actions builder = new Actions(driver);
				
				builder.click(driver.findElementByXPath("//span[text()='Material']")).perform(); //click material 
				
				driver.findElementByXPath("//span[text()='cotton (100%)']").click(); //select cotton (100%)
				
				driver.findElementByXPath("//button[text()='Save']").click(); //click save button
				Thread.sleep(3000);
				
			//Choose Length as thigh-length
				
				builder.click(driver.findElementByXPath("//span[text()='Length']")).perform(); //click Length 
				
				driver.findElementByXPath("//span[text()='thigh-length']").click(); //select thigh-length
				
				driver.findElementByXPath("//button[text()='Save']").click(); //click save button
				Thread.sleep(6000);
				
			//Click on JUNAROSE - by VERO MODA
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='cat_brandName-2XZRz cat_ellipsis-MujnT'])[1]")));
			
				List<WebElement> brandNamesList = driver.findElementsByXPath("//div[@class='cat_brandName-2XZRz cat_ellipsis-MujnT']");
				
				int size = brandNamesList.size();
								
				for (int i = 0; i < size; i++) 
				{
					
					String brandName = brandNamesList.get(i).getText();
					
						if (brandName.equals("JUNAROSE - by VERO MODA")) {
							
							driver.findElementByXPath("//div[text()='JUNAROSE - by VERO MODA']").click();
							break;
						}
						
						if((i==(size-1)) && driver.findElementByXPath("//a[@title='next page']").isEnabled()) 
						{
							
							driver.findElementByXPath("//a[@title='next page']").click();
							Thread.sleep(4000);
							
							List<WebElement> secondPageBrandNamesList = driver.findElementsByXPath("//div[@class='cat_brandName-2XZRz cat_ellipsis-MujnT']");
							
								for (int j = 0; j < secondPageBrandNamesList.size(); j++) 
								{
									String brandNamesInSecondPage = secondPageBrandNamesList.get(j).getText();
									
									if (brandNamesInSecondPage.equals("JUNAROSE - by VERO MODA")) 
									{											
										driver.findElementByXPath("//div[text()='JUNAROSE - by VERO MODA']").click();
										break;
									}
									
								}
						   }
					}
				
				//Click Color as Black and Size as 'M' Under Manufacture Sizes
				
					driver.findElementByXPath("//button[@id='picker-trigger']").click();
					
					driver.findElementByXPath("//span[text()='Manufacturer sizes']").click(); //click Manufacture Sizes
					
					driver.findElementByXPath("//span[text()='M']").click(); //select Size as 'M' 
				
				//Add to bag only if Standard Delivery is free
					
					String deliveryOption = driver.findElementByXPath("(//div[@class='UyCaZm'])[2]").getText();
					
					if (deliveryOption.contains("Free")) 
						
						System.out.println("Product's Standard Delivery is free ");
					 else
						System.out.println("Product's Standard Delivery is not free ");	
						
					//Add product to Bag
					
					driver.findElementByXPath("//span[text()='Add to bag']").click();
					
				//Mouse over on Your Bag and Click on "Go to Bag" 
					
					builder.moveToElement(driver.findElementByXPath("(//span[@class='z-navicat-header_navToolLabel'])[3]")).perform();
					Thread.sleep(2000);
					
					builder.moveToElement(driver.findElementByXPath("//div[text()='Go to bag']")).click().perform();
					
				//Read the Estimated Deliver Date and print 
					
					String estimatedDeliveryDate = driver.findElementByXPath("//div[@data-id='delivery-estimation']/span").getText();
					
					System.out.println("Estimated Delivery Date is:"+estimatedDeliveryDate);					
				
				//Click on 'Go To Checkout'
					
					driver.findElementByXPath("//div[text()='Go to checkout']").click();
				
				//Enter your email 
					
					driver.findElementByXPath("//input[@placeholder='Email address']").sendKeys("kalpana.kripa@gmail.com");
					
				//Click on Login button
					
					driver.findElementByXPath("//span[text()='Login']").click();
					Thread.sleep(2000);
					
				//Print the error message
					
					String errorDisplayed = driver.findElementByXPath("//span[text()='This field is required']").getText();
					
					System.err.println("Error Message Displayed is:"+errorDisplayed);
			
					//Close Browser
					driver.close();
					
	}	

}
